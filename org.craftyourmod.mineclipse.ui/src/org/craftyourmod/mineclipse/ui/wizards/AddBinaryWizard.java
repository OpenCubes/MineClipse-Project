package org.craftyourmod.mineclipse.ui.wizards;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

import org.craftyourmod.mineclipse.core.MineclipseCore;
import org.craftyourmod.mineclipse.core.filemanager.BinaryFile;
import org.craftyourmod.mineclipse.core.filemanager.FileManager;
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
		final boolean file = confPage.getBtnGetBinariesFromFile()
				.getSelection();
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(final IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {

					monitor.beginTask("Creating Binary",
							IProgressMonitor.UNKNOWN);
					File targetDir = new File(new File(System
							.getProperty("user.home")),
							"/.mineclipse/files/bins/");
					targetDir.mkdirs();

					monitor.subTask("Getting jar");
					BinaryFile bin = BinaryFile.create(new File("FAKE"), name);
					bin.setInput(new File(targetDir, "bin_" + bin.getId()
							+ ".jar"));
					if (dl)
						try {
							org.craftyourmod.mineclipse.core.Util.get(new URL(
									url),
									new File(targetDir, "bin_" + bin.getId()
											+ ".jar"));
						} catch (MalformedURLException e) {
							throw new RuntimeException(e);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					else if (file) {
						MineclipseCore.INSTANCE.performCopy(
								new File(path).getParentFile(), targetDir,
								"minecraft\\.jar", monitor);
						new File(targetDir, new File(path).getName())
								.renameTo(new File(targetDir, "bin_"
										+ bin.getId() + ".jar"));
					}
					FileManager.INSTANCE.addBin(BinaryFile.create(new File(
							targetDir, "bin_" + bin.getId() + ".jar"), name));
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
