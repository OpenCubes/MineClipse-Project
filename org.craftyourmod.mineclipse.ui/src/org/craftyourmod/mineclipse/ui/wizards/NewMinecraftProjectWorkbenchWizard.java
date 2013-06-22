package org.craftyourmod.mineclipse.ui.wizards;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

import org.craftyourmod.mineclipse.core.Executor;
import org.craftyourmod.mineclipse.core.Util;
import org.craftyourmod.mineclipse.core.filemanager.FileManager;
import org.craftyourmod.mineclipse.core.filemanager.SourceFile;
import org.craftyourmod.mineclipse.ui.Activator;
import org.craftyourmod.mineclipse.ui.pages.NewMineClipseProjectPageOne;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

public class NewMinecraftProjectWorkbenchWizard extends Wizard implements
		IWorkbenchWizard {
	NewMineClipseProjectPageOne pageOne;
	private IWorkbench workbench;
	private IStatus fStatus;

	@Override
	public void init(final IWorkbench workbench,
			final IStructuredSelection selection) {
		this.workbench = workbench;
		/*
		 * WizardDialog wiz = new WizardDialog(workbench
		 * .getActiveWorkbenchWindow().getShell(), this); wiz.open();
		 */

		setNeedsProgressMonitor(true);

	}

	@Override
	public void addPages() {
		pageOne = new NewMineClipseProjectPageOne(this);
		addPage(pageOne);
	}

	@Override
	public boolean performFinish() {
		final String name = pageOne.getTxtName().getText();
		SourceFile tmp = null;
		for (SourceFile src : FileManager.INSTANCE.getSrcs())
			if (src.getName().equals(pageOne.getTxtInput().getText())) {
				tmp = src;
				break;
			}
		if (tmp == null) {
			setStatus(org.craftyourmod.mineclipse.ui.Activator
					.error("No SourceFile found"));

			return false;
		}
		final SourceFile source = tmp;
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(final IProgressMonitor monitor)
						throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Project creation", -1);
					IProject project = ResourcesPlugin.getWorkspace().getRoot()
							.getProject(name);
					if (project.exists())
						throw new InvocationTargetException(
								new RuntimeException("Project already exists"));
					try {

						// Beginning
						project.create(new SubProgressMonitor(monitor, -1));
						IJavaProject javaProject = JavaCore.create(project);
						project.open(new SubProgressMonitor(monitor, -1));

						if (monitor.isCanceled())
							throw new InterruptedException();

						// Folders
						IPath srcPath = new Path("src");
						IFolder srcFolder = project.getFolder(srcPath);
						srcFolder.create(true, true, null);
						IPath mcPath = new Path("minecraft");
						IFolder mcFolder = project.getFolder(mcPath);
						mcFolder.create(true, true, new SubProgressMonitor(
								monitor, -1));
						File f = new File(mcFolder.getLocationURI().toURL()
								.getFile().substring(1));
						final File input = new File(Activator
								.getWorkingDirectory(), "/files/srcs/bin_"
								+ source.getId() + "/src");
						Executor.INSTANCE.performCopy(input, f, "",
								new SubProgressMonitor(monitor, -1));

						final Path rsPath = new Path(
								"resources");
						IFolder rscolder = project.getFolder(rsPath);
						rscolder.create(true, true, new SubProgressMonitor(
								monitor, -1));
						File rs = new File(rscolder.getLocationURI().toURL()
								.getFile().substring(1));
						final File inRs = new File(Activator
								.getWorkingDirectory(), "/files/srcs/bin_"
								+ source.getId() + "/bin");
						Executor.INSTANCE.performCopy(inRs, rs,
								"[a-zA-Z0-9\\s]+(.(png|gif|txt|lang))?",
								new SubProgressMonitor(monitor, -1));

						IProjectDescription projectDescription = project
								.getDescription();
						if (monitor.isCanceled())
							throw new InterruptedException();

						// Nature
						String[] newNatures = new String[projectDescription
								.getNatureIds().length + 1];
						System.arraycopy(projectDescription.getNatureIds(), 0,
								newNatures, 0,
								projectDescription.getNatureIds().length);
						newNatures[projectDescription.getNatureIds().length] = JavaCore.NATURE_ID;
						projectDescription.setNatureIds(newNatures);
						project.setDescription(projectDescription,
								new SubProgressMonitor(monitor, -1));

						if (monitor.isCanceled())
							throw new InterruptedException();

						// Classpath
						IPath containerPath = new Path(
								JavaRuntime.JRE_CONTAINER);
						IPath nsrcPath = project.getFullPath().append(srcPath)
								.makeAbsolute();
						IPath nmcPath = project.getFullPath().append(mcPath)
								.makeAbsolute();
						IClasspathEntry[] newCp = new IClasspathEntry[7];
						newCp[0] = JavaCore.newContainerEntry(containerPath);
						final IClasspathAttribute attr = JavaCore
								.newClasspathAttribute(
										JavaRuntime.CLASSPATH_ATTR_LIBRARY_PATH_ENTRY,
										new File(Util
												.getMinecraftWokingDirectory(),
												"/bin/natives")
												.getAbsolutePath());
						newCp[1] = JavaCore.newSourceEntry(nmcPath, null,
								new IPath[] {},
								project.getFullPath().append("minecraftbin")
										.makeAbsolute(),
								new IClasspathAttribute[] { attr });
						newCp[2] = JavaCore.newSourceEntry(nsrcPath, null,
								new IPath[] {},
								project.getFullPath().append("srcbin")
										.makeAbsolute(),
								new IClasspathAttribute[] { attr });
						newCp[3] = JavaCore.newLibraryEntry(new Path(new File(
								Util.getMinecraftWokingDirectory(),
								"/bin/lwjgl.jar").getAbsolutePath()), null,
								null);
						newCp[4] = JavaCore.newLibraryEntry(new Path(new File(
								Util.getMinecraftWokingDirectory(),
								"/bin/lwjgl_util.jar").getAbsolutePath()),
								null, null);
						newCp[5] = JavaCore.newLibraryEntry(new Path(new File(
								Util.getMinecraftWokingDirectory(),
								"/bin/jinput.jar").getAbsolutePath()), null,
								null);
						newCp[6] = JavaCore.newSourceEntry(project
								.getFullPath().append(rsPath)
								.makeAbsolute(), null, new IPath[] {}, project
								.getFullPath().append("rsbin").makeAbsolute(),
								new IClasspathAttribute[] {});

						File libDir = new File(Activator.getWorkingDirectory(),
								"/files/libs/");

						File[] libs = libDir.listFiles(new FileFilter() {

							@Override
							public boolean accept(final File pathname) {
								return pathname.getName().endsWith(".jar");
							}
						});
						// Libs
						IClasspathEntry[] cpLibs = new IClasspathEntry[libs.length];
						for (int i = 0; i < libs.length; i++) {
							File lib = libs[i];
							cpLibs[i] = JavaCore.newLibraryEntry(
									new Path(lib.getAbsolutePath()), null, null);
						}
						IClasspathEntry[] newCp2 = new IClasspathEntry[newCp.length
								+ cpLibs.length];
						System.arraycopy(newCp, 0, newCp2, 0, newCp.length);
						System.arraycopy(cpLibs, 0, newCp2, newCp.length,
								cpLibs.length);
						javaProject.setRawClasspath(newCp2,
								new SubProgressMonitor(monitor, -1));

						if (monitor.isCanceled())
							throw new InterruptedException();

						// End
						project.refreshLocal(IResource.DEPTH_INFINITE,
								new SubProgressMonitor(monitor, -1));

					} catch (CoreException e) {
						throw new InvocationTargetException(e);
					} catch (MalformedURLException e) {
						throw new InvocationTargetException(e);
					}
				}

			});
		} catch (InvocationTargetException | InterruptedException e) {

			setStatus(Activator
					.error("Error while creating project: "
							+ e.getClass().getName()
							+ ((e.getLocalizedMessage() != null) ? (": " + e
									.getLocalizedMessage())
									: (e.getCause() != null) ? e.getCause()
											.getLocalizedMessage() != null ? ": "
											+ e.getCause()
													.getLocalizedMessage()
											: ""
											: ""), e));
			return false;
		}
		return true;
	}

	public void setStatus(final IStatus status) {

		this.fStatus = status;
		pageOne.updateMessage();
	}

	/**
	 * @return the fStatus
	 */
	public IStatus getStatus() {
		return fStatus;
	}

}
