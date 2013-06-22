package org.craftyourmod.mineclipse.ui.wizards;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import org.craftyourmod.mineclipse.core.Executor;
import org.craftyourmod.mineclipse.core.filemanager.BinaryFile;
import org.craftyourmod.mineclipse.core.filemanager.FileManager;
import org.craftyourmod.mineclipse.core.filemanager.SourceFile;
import org.craftyourmod.mineclipse.ui.Messages;
import org.craftyourmod.mineclipse.ui.pages.AddBinaryPage;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

public class AddBinaryWizard extends Wizard {

	private AddBinaryPage confPage;

	public AddBinaryWizard() {
		setWindowTitle(Messages.AddBinaryWizard_Title);

	}

	@Override
	public void addPages() {
		confPage = new AddBinaryPage();
		addPage(confPage);
		setNeedsProgressMonitor(true);
	}

	@Override
	public boolean performFinish() {
		final String url = confPage.getTxtServerpath().getText();
		final String name = confPage.getTextName().getText();
		final String path = confPage.getTextPath().getText();
		final boolean dl = confPage.getBtnDowload().getSelection();
		final boolean file = confPage.getBtnGetBinariesFromFile().getSelection();
		SourceFile tmp = null;
		for (SourceFile src : FileManager.INSTANCE.getSrcs())
			if (src.getName().equals(name)) {
				tmp = src;
				break;
			}
		if (tmp == null) {
			org.craftyourmod.mineclipse.ui.Activator.error("No SourceFile found");
			return false;
		}
		final SourceFile source = tmp;
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

					monitor.beginTask("Creating Binary", IProgressMonitor.UNKNOWN);
					File targetDir = new File(new File(System.getProperty("user.home")), "/.mineclipse/files/bins/");
					targetDir.mkdirs();

					monitor.subTask("Getting jar");
					if (dl)
						try {
							org.craftyourmod.mineclipse.core.Util.get(new URL(url), new File(targetDir, "bin_" + source.getId() + ".jar"));
						} catch (MalformedURLException e) {
							throw new RuntimeException(e);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					else
						if (file) {
							Executor.INSTANCE.performCopy(new File(path).getParentFile(), targetDir, "minecraft\\.jar", monitor);
							new File(targetDir, new File(path).getName()).renameTo(new File(targetDir, "bin_" + source.getId() + ".jar"));
						}
					FileManager.INSTANCE.addBin(BinaryFile.create(new File(targetDir, "bin_" + source.getId() + ".jar"), name));
					monitor.done();
				}

			});
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return true;
	}
}