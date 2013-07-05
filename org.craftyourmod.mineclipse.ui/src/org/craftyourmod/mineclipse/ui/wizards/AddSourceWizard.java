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
		setWindowTitle("Add Source");
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
		Job job = new Job("Source creation") {

			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				monitor.beginTask("Creating source", 100);
				boolean download = false;
				final File base = new File(Activator.getWorkingDirectory(),
						forge ? "forge" : "mcp");
				base.mkdirs();
				monitor.subTask("Checking version...");
				monitor.worked(5);
				try {
					String vServer = DigestUtils.md5Hex(new URL(
							"http://files.minecraftforge.net/minecraftforge"
									+ "/minecraftforge-src-recommended.zip")
							.openStream());
					String vLocal = ConfigManager.getInstance().getPreference(
							"LocalForgeMd5", "");
					if (!vServer.equals(vLocal))
						download = true;
					monitor.worked(15);
				} catch (IOException e1) {
					Activator
							.getDefault()
							.getLog()
							.log(new Status(Status.ERROR, Activator.PLUGIN_ID,
									"Error while running file", e1));
					e1.printStackTrace();
					return new Status(Status.ERROR, Activator.PLUGIN_ID,
							"Error while running file");
				}
				if (download)
					try {
						monitor.subTask("Downloading...");
						monitor.worked(25);
						if (forge) {
							Util.get(
									new URL(
											"http://files.minecraftforge.net/minecraftforge"
													+ "/minecraftforge-src-recommended.zip"),
									new File(base, "forge.zip"));
							ConfigManager.getInstance().putPreference(
									"LocalForgeMd5",
									DigestUtils.md5Hex(new FileInputStream(
											new File(base, "forge.zip"))));

							monitor.worked(30);
							Util.unZipIt(new File(base, "forge.zip")
									.getAbsolutePath(), base.getAbsolutePath());

						} else {
							Util.get(
									new URL(
											"http://mcp.ocean-labs.de/files/mcp751.zip"),
									new File(base, "mcp.zip"));

							monitor.worked(30);
							Util.unZipIt(
									new File(base, "mcp.zip").getAbsolutePath(),
									base.getAbsolutePath());
						}

						monitor.worked(35);
					} catch (IOException e) {
						Activator
								.getDefault()
								.getLog()
								.log(new Status(Status.ERROR,
										Activator.PLUGIN_ID,
										"Error while running file", e));
						e.printStackTrace();
						return new Status(Status.ERROR, Activator.PLUGIN_ID,
								"Error while running file");
					}

				monitor.worked(45);
				monitor.subTask("Clearing dirs...");
				if (!justCopy) {
					String[] dirs = new String[] { "jars", "lib", "src",
							"reobf", "logs", "bin" };
					for (String string : dirs) {
						Util.deleteFolder(new File(System
								.getProperty("user.home"), "/.mineclipse/mcp/"
								+ string));

						new File(base, string).mkdirs();
					}
					MineclipseCore.INSTANCE.performCopy(
							new File(System.getenv("APPDATA"),
									"/.minecraft/bin"), new File(base,
									"/jars/bin"), "", monitor);
					File exec = (new File(base,
							forge ? "/forge/fml/install.bat" : "decompile.bat"));

					MineclipseCore.INSTANCE.setDirectory(exec.getParentFile());
					Util.removePause(exec);
					MineclipseCore.INSTANCE.setExec(exec);
				}
				if (!justCopy)
					try {
						MineclipseCore.INSTANCE.run(monitor);

						monitor.worked(85);
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
						new File(Activator.getWorkingDirectory(),
								"/.mineclipse/files/srcs/bin_" + binary.getId())
								.mkdirs();

					} catch (InvocationTargetException | InterruptedException e) {
						Activator
								.getDefault()
								.getLog()
								.log(new Status(Status.ERROR,
										Activator.PLUGIN_ID,
										"Error while running file", e));
						e.printStackTrace();
						return new Status(Status.ERROR, Activator.PLUGIN_ID,
								"Error while running file", e);
					}
				monitor.subTask("Copying files");
				FileManager.INSTANCE
						.addSrc((SourceFile) SourceFile.create(
								base,
								binary,
								monitor,
								new File(System.getProperty("user.home"),
										"/.mineclipse/files/srcs/bin_"
												+ binary.getId()), binary
										.getName()));

				monitor.worked(100);
				monitor.done();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
		return true;
	}
}
