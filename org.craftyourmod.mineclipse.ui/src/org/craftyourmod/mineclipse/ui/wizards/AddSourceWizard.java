package org.craftyourmod.mineclipse.ui.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

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
			if (addSourcePage.getTxtInputBinary().getText().equals(bin.getName()))
				binary2 = bin;
		final BinaryFile binary = binary2;
		final boolean justCopy = addSourcePage.getBtnJustCopy().getSelection();

		Job job = new Job("Source creation (looong task !)") {

			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				monitor.beginTask("Creating source", IProgressMonitor.UNKNOWN);
				monitor.subTask("Clearing dirs...");
				if (!justCopy) {
					String[] dirs = new String[] { "jars", "lib", "src", "reobf", "logs", "bin" };
					for (String string : dirs) {
						Util.deleteFolder(new File(System.getProperty("user.home"), "/.mineclipse/mcp/" + string));

						new File(System.getProperty("user.home"), "/.mineclipse/mcp/" + string).mkdirs();
					}
					MineclipseCore.INSTANCE.performCopy(new File(System.getenv("APPDATA"), "/.minecraft/bin"), new File(new File(System.getProperty("user.home")), "/.mineclipse/mcp/jars/bin"), "", monitor);
					MineclipseCore.INSTANCE.setDirectory(new File(System.getProperty("user.home"), "/.mineclipse/mcp/"));
					MineclipseCore.INSTANCE.setExec((new File(System.getProperty("user.home"), "/.mineclipse/mcp/decompile.bat")));
				}
				if (!justCopy)
					try {
						MineclipseCore.INSTANCE.run(monitor);
						new File(System.getProperty("user.home"), "/.mineclipse/files/srcs/bin_" + binary.getId()).mkdirs();

					} catch (InvocationTargetException | InterruptedException e) {
						Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error while running file", e));
						e.printStackTrace();
						return new Status(Status.ERROR, Activator.PLUGIN_ID, "Error while running file", e);
					}
				monitor.subTask("Copying files");
				FileManager.INSTANCE.addSrc((SourceFile) SourceFile.create(new File(System.getProperty("user.home"), "/.mineclipse/mcp/"), binary, monitor, new File(System.getProperty("user.home"), "/.mineclipse/files/srcs/bin_" + binary.getId()), binary.getName()));
				monitor.done();
				return Status.OK_STATUS;
			}
		};
		job.schedule();
		return true;
	}
}
