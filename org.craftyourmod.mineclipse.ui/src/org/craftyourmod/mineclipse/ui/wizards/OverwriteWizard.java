package org.craftyourmod.mineclipse.ui.wizards;

import java.io.File;

import org.craftyourmod.mineclipse.core.MineclipseCore;
import org.craftyourmod.mineclipse.core.Util;
import org.craftyourmod.mineclipse.ui.Activator;
import org.craftyourmod.mineclipse.ui.pages.OverrideFilePage;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWizard;

public class OverwriteWizard extends Wizard implements IWorkbenchWizard {

	private IStructuredSelection selection;
	OverrideFilePage page;

	public OverwriteWizard() {
		setWindowTitle("Overwrite");
	}

	@Override
	public void addPages() {
		page = new OverrideFilePage(selection);
		addPage(page);
	}

	@Override
	public boolean performFinish() {
		try {
			IProject pj = getSelectedProject(selection);
			File oldFile = page.getSelectedFile();
			File newFile = new File(pj.getFolder("src").getLocation().toFile(),
					"net/minecraft/" + oldFile.getParentFile().getName());
			newFile.mkdirs();
			MineclipseCore.INSTANCE.overwrite(oldFile, new File(newFile,
					oldFile.getName()), null);
			IClasspathEntry[] entries = JavaCore.create(pj).getRawClasspath();
			for (int i = 0; i < entries.length; i++) {
				IClasspathEntry entry = entries[i];
				if ((entry.getEntryKind() == IClasspathEntry.CPE_SOURCE)
						&& entry.getPath().toFile().getName()
								.equals("minecraft")) {
					IPath[] exc = new IPath[] { new Path("net/minecraft/"
							+ oldFile.getParentFile().getName() + "/"
							+ oldFile.getName()) };
					IPath[] newExc = new IPath[exc.length
							+ entry.getExclusionPatterns().length];
					System.arraycopy(exc, 0, newExc, 0, exc.length);
					System.arraycopy(entry.getExclusionPatterns(), 0, newExc,
							exc.length, entry.getExclusionPatterns().length);
					entries[i] = JavaCore.newSourceEntry(entry.getPath(),
							entry.getInclusionPatterns(), newExc,
							entry.getOutputLocation(),
							entry.getExtraAttributes());

				}
			}
			JavaCore.create(pj).setRawClasspath(entries, null);

			// End
			pj.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (Exception e) {
			page.setStatus(Activator.error("Error while creating project: "
					+ Util.computeDescription(e), e));
			return false;
		}
		return true;
	}

	@Override
	public void init(final IWorkbench workbench,
			final IStructuredSelection selection) {
		this.selection = selection;
	}

	public IProject getSelectedProject(final IStructuredSelection selection) {
		ISelection sel = selection;
		Object selectedObject = sel;
		if (sel instanceof IStructuredSelection)
			selectedObject = ((IStructuredSelection) sel).getFirstElement();
		if (selectedObject instanceof IAdaptable) {
			IResource res = (IResource) ((IAdaptable) selectedObject)
					.getAdapter(IResource.class);
			IProject project = res.getProject();
			return project;
		}
		return null;

	}

}
