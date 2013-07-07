package org.craftyourmod.mineclipse.ui.wizards;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.apache.commons.codec.digest.DigestUtils;
import org.craftyourmod.mineclipse.core.ConfigManager;
import org.craftyourmod.mineclipse.core.MineclipseCore;
import org.craftyourmod.mineclipse.core.Util;
import org.craftyourmod.mineclipse.core.filemanager.BinaryFile;
import org.craftyourmod.mineclipse.core.filemanager.FileManager;
import org.craftyourmod.mineclipse.core.filemanager.SourceFile;
import org.craftyourmod.mineclipse.ui.Activator;
import org.craftyourmod.mineclipse.ui.pages.AddSourcePage;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.wizard.Wizard;

public class AddSourceWizard extends Wizard {
	AddSourcePage addSourcePage;

	public AddSourceWizard() {
		setWindowTitle(Messages.AddSourceWizard_Title);
	}

	@Override
	public void addPages() {
		addSourcePage = new AddSourcePage();
		addPage(addSourcePage);
	}

	@Override
	public boolean performFinish() {
		BinaryFile binary2 = null;
		for (BinaryFile bin : FileManager.INSTANCE.getBins())
			if (addSourcePage.getTxtInputBinary().getText()
					.equals(bin.getName()))
				binary2 = bin;
		final BinaryFile binary = binary2;
		final boolean justCopy = addSourcePage.getBtnJustCopy().getSelection();
		final boolean forge = addSourcePage.getBtnUseForge().getSelection();
		final String[] outStrings = addSourcePage.getTxtYesyes().getText()
				.split(","); //$NON-NLS-1$
		Job job = new Job(Messages.AddSourceWizard_JobName) {

			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				monitor.beginTask(Messages.AddSourceWizard_Task_SrcCreation,
						100);
				boolean download = false;
				final File base = new File(Activator.getWorkingDirectory(),
						forge ? "forge" : "mcp"); //$NON-NLS-1$ //$NON-NLS-2$
				base.mkdirs();
				monitor.subTask(Messages.AddSourceWizard_Task_VersionCheck);
				monitor.worked(5);
				try {
					String vServer = DigestUtils.md5Hex(new URL(
							"http://files.minecraftforge.net/minecraftforge" //$NON-NLS-1$
									+ "/minecraftforge-src-recommended.zip") //$NON-NLS-1$
							.openStream());
					String vLocal = ConfigManager.getInstance().getPreference(
							"LocalForgeMd5", ""); //$NON-NLS-1$ //$NON-NLS-2$
					if (!vServer.equals(vLocal))
						download = true;
					monitor.worked(15);
				} catch (IOException e1) {
					Activator
							.getDefault()
							.getLog()
							.log(new Status(Status.ERROR, Activator.PLUGIN_ID,
									Messages.AddSourceWizard_JobError, e1));
					e1.printStackTrace();
					return new Status(Status.ERROR, Activator.PLUGIN_ID,
							Messages.AddSourceWizard_JobError);
				}
				if (download)
					try {
						monitor.subTask(Messages.AddSourceWizard_Task_Download);
						monitor.worked(25);
						if (forge) {
							Util.get(
									new URL(
											"http://files.minecraftforge.net/minecraftforge" //$NON-NLS-1$
													+ "/minecraftforge-src-recommended.zip"), //$NON-NLS-1$
									new File(base, "forge.zip")); //$NON-NLS-1$
							ConfigManager.getInstance().putPreference(
									"LocalForgeMd5", //$NON-NLS-1$
									DigestUtils.md5Hex(new FileInputStream(
											new File(base, "forge.zip")))); //$NON-NLS-1$

							monitor.worked(30);
							Util.unZipIt(new File(base, "forge.zip") //$NON-NLS-1$
									.getAbsolutePath(), base.getAbsolutePath());

						} else {
							Util.get(
									new URL(
											"http://mcp.ocean-labs.de/files/mcp751.zip"), //$NON-NLS-1$
									new File(base, "mcp.zip")); //$NON-NLS-1$

							monitor.worked(30);
							Util.unZipIt(
									new File(base, "mcp.zip").getAbsolutePath(), //$NON-NLS-1$
									base.getAbsolutePath());
						}

						monitor.worked(35);
					} catch (IOException e) {
						Activator
								.getDefault()
								.getLog()
								.log(new Status(Status.ERROR,
										Activator.PLUGIN_ID,
										Messages.AddSourceWizard_JobError, e));
						e.printStackTrace();
						return new Status(Status.ERROR, Activator.PLUGIN_ID,
								Messages.AddSourceWizard_JobError);
					}

				monitor.worked(45);
				monitor.subTask(Messages.AddSourceWizard_Task_CleaningDir);
				if (!justCopy) {
					String[] dirs = new String[] { "jars", "lib", "src", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							"reobf", "logs", "bin" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					for (String string : dirs) {
						Util.deleteFolder(new File(Activator
								.getWorkingDirectory(), "/mcp/" //$NON-NLS-1$ //$NON-NLS-2$
								+ string));

						new File(base, string).mkdirs();
					}
					MineclipseCore.INSTANCE.performCopy(
							new File(System.getenv("APPDATA"), //$NON-NLS-1$
									"/.minecraft/bin"), new File(base, //$NON-NLS-1$
									"/jars/bin"), "", monitor); //$NON-NLS-1$ //$NON-NLS-2$
					File exec = (new File(base,
							forge ? "/forge/fml/install.bat" : "decompile.bat")); //$NON-NLS-1$ //$NON-NLS-2$

					MineclipseCore.INSTANCE.setDirectory(exec.getParentFile());
					Util.removePause(exec);
					MineclipseCore.INSTANCE.setExec(exec);
				}
				if (!justCopy)
					try {
						MineclipseCore.INSTANCE.run(monitor, outStrings);

						monitor.worked(85);
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
						new File(Activator.getWorkingDirectory(),
								"/files/srcs/bin_" + binary.getId()) //$NON-NLS-1$
								.mkdirs();

					} catch (InvocationTargetException | InterruptedException e) {
						Activator
								.getDefault()
								.getLog()
								.log(new Status(Status.ERROR,
										Activator.PLUGIN_ID,
										Messages.AddSourceWizard_JobError, e));
						e.printStackTrace();
						return new Status(Status.ERROR, Activator.PLUGIN_ID,
								Messages.AddSourceWizard_JobError, e);
					}
				monitor.subTask(Messages.AddSourceWizard_Task_Copy);
				FileManager.INSTANCE.addSrc((SourceFile) SourceFile.create(
						new File(base, "forge/fml/mcp"), //$NON-NLS-1$
						binary, monitor,
						new File(Activator.getWorkingDirectory(), //$NON-NLS-1$
								"/files/srcs/bin_" //$NON-NLS-1$
										+ binary.getId()), binary.getName()));

				monitor.worked(100);
				monitor.done();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
		return true;
	}
}
