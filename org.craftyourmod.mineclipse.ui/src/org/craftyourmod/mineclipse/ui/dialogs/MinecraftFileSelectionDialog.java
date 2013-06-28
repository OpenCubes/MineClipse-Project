package org.craftyourmod.mineclipse.ui.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import org.craftyourmod.mineclipse.ui.Activator;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

public class MinecraftFileSelectionDialog extends FilteredItemsSelectionDialog {
	private static ArrayList<File> resources = new ArrayList<File>();

	public MinecraftFileSelectionDialog(final Shell shell,
			final IStructuredSelection selection) {
		super(shell);
		IProject pj = getSelectedProject(selection);
		IFolder fClient = pj.getFolder("minecraft/net/minecraft/client");
		for (File f : fClient.getRawLocation().toFile().listFiles())
			resources.add(f);
		IFolder fServer = pj.getFolder("minecraft/net/minecraft/server");
		for (File f : fServer.getRawLocation().toFile().listFiles())
			resources.add(f);
		IFolder fSrc = pj.getFolder("minecraft/net/minecraft/src");
		for (File f : fSrc.getRawLocation().toFile().listFiles())
			if (f.getName().endsWith(".java"))
				resources.add(f);
		setInitialPattern("*.*");
		setListLabelProvider(new ILabelProvider() {

			@Override
			public void removeListener(final ILabelProviderListener listener) {
			}

			@Override
			public boolean isLabelProperty(final Object element,
					final String property) {
				return false;
			}

			@Override
			public void dispose() {
			}

			@Override
			public void addListener(final ILabelProviderListener listener) {
			}

			@Override
			public String getText(final Object element) {
				if (element instanceof File) {
					File f = (File) element;
					return f.getName();
				}
				return "UNKNOWN";
			}

			@Override
			public Image getImage(final Object element) {
				if (element instanceof File) {
					File f = (File) element;
					Image img = PlatformUI.getWorkbench().getEditorRegistry()
							.getImageDescriptor(f.getAbsolutePath())
							.createImage();
					return img;
				}
				return null;
			}
		});
	}

	@Override
	protected Control createExtendedContentArea(final Composite parent) {
		return null;
	}

	private static final String DIALOG_SETTINGS = "MinecraftFileSelectionDialog";

	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings = Activator.getDefault().getDialogSettings()
				.getSection(DIALOG_SETTINGS);
		if (settings == null)
			settings = Activator.getDefault().getDialogSettings()
					.addNewSection(DIALOG_SETTINGS);
		return settings;
	}

	@Override
	protected IStatus validateItem(final Object item) {
		return Status.OK_STATUS;
	}

	@Override
	protected ItemsFilter createFilter() {
		return new ItemsFilter() {
			@Override
			public boolean matchItem(final Object item) {
				return matches(((File) item).getName());
			}

			@Override
			public boolean isConsistentItem(final Object item) {
				return true;
			}
		};
	}

	@Override
	protected Comparator getItemsComparator() {
		return new Comparator() {
			@Override
			public int compare(final Object arg0, final Object arg1) {
				return arg0.toString().compareTo(arg1.toString());
			}
		};
	}

	@Override
	protected void fillContentProvider(
			final AbstractContentProvider contentProvider,
			final ItemsFilter itemsFilter,
			final IProgressMonitor progressMonitor) throws CoreException {
		progressMonitor.beginTask("Searching", resources.size()); //$NON-NLS-1$
		for (Object element : resources) {
			contentProvider.add(element, itemsFilter);
			progressMonitor.worked(1);
		}
		progressMonitor.done();

	}

	@Override
	public String getElementName(final Object item) {
		return ((File) item).getName();
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
