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

					monitor.beginTask(Messages.AddBinaryWizard_State_BinCreation,
							IProgressMonitor.UNKNOWN);
					File targetDir = new File(new File(System
							.getProperty("user.home")), //$NON-NLS-1$
							"/.mineclipse/files/bins/"); //$NON-NLS-1$
					targetDir.mkdirs();

					monitor.subTask(Messages.AddBinaryWizard_State_GettingJar);
					BinaryFile bin = BinaryFile.create(new File("FAKE"), name); //$NON-NLS-1$
					bin.setInput(new File(targetDir, "bin_" + bin.getId() //$NON-NLS-1$
							+ ".jar")); //$NON-NLS-1$
					if (dl)
						try {
							org.craftyourmod.mineclipse.core.Util.get(new URL(
									url),
									new File(targetDir, "bin_" + bin.getId() //$NON-NLS-1$
											+ ".jar")); //$NON-NLS-1$
						} catch (MalformedURLException e) {
							throw new RuntimeException(e);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
					else if (file) {
						MineclipseCore.INSTANCE.performCopy(
								new File(path).getParentFile(), targetDir,
								"minecraft\\.jar", monitor); //$NON-NLS-1$
						new File(targetDir, new File(path).getName())
								.renameTo(new File(targetDir, "bin_" //$NON-NLS-1$
										+ bin.getId() + ".jar")); //$NON-NLS-1$
					}
					FileManager.INSTANCE.addBin(BinaryFile.create(new File(
							targetDir, "bin_" + bin.getId() + ".jar"), name)); //$NON-NLS-1$ //$NON-NLS-2$
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
