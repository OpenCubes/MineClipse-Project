package org.eclipse.mineclipse.draft.views;

import java.util.Arrays;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.mineclipse.draft.Activator;
import org.eclipse.mineclipse.draft.actions.AddElementAction;
import org.eclipse.mineclipse.draft.actions.CollapseAllAction;
import org.eclipse.mineclipse.draft.actions.ExpandAllAction;
import org.eclipse.mineclipse.draft.actions.RemoveElementAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class FileManagerView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.mineclipse.draft.views.FileManagerView";
	private final Action addElementAction = new AddElementAction();
	private final Action removeElementAction = new RemoveElementAction();
	private final Action collapseAllAction = new CollapseAllAction();
	private final Action expandAllAction = new ExpandAllAction();

	/*
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */

	class ViewContentProvider implements ITreeContentProvider {
		String[] bins = new String[] { "Minecraft 1.5.1", "Minecraft 1.4.7",
				"Minecraft 1.0.0" };
		String binsTitle = "Minecraft Binaries";
		String[] srcs = new String[] { "Minecraft 1.5.1", "Minecraft 1.4.7",
				"Minecraft 1.0.0" };
		String srcsTitle = "Minecraft Sources";
		String[] mcp = new String[] { "MCP 7.51", "MCP 7.10" };
		String mcpTitle = "Minecraft Coder Pack Versions";

		@Override
		public void inputChanged(final Viewer v, final Object oldInput,
				final Object newInput) {
		}

		@Override
		public void dispose() {
		}

		@Override
		public Object[] getElements(final Object parent) {
			Activator
					.getDefault()
					.getLog()
					.log(new Status(Status.WARNING, Activator.PLUGIN_ID,
							"Getting Element  : " + parent));
			if (parent.equals("parent"))
				return new String[] { "Minecraft Binaries",
						"Minecraft Sources", "Minecraft Coder Pack" };
			if (parent.equals(binsTitle))
				return bins;
			if (parent.equals(srcsTitle))
				return srcs;
			if (parent.equals(mcpTitle))
				return mcp;

			return new String[] { "" };
		}

		@Override
		public Object[] getChildren(final Object parent) {
			Activator
					.getDefault()
					.getLog()
					.log(new Status(Status.WARNING, Activator.PLUGIN_ID,
							"Getting Children : " + parent));
			if (parent.equals("parent"))
				return new String[] { "Minecraft Binaries",
						"Minecraft Sources", "Minecraft Coder Pack" };
			if (parent.equals(binsTitle))
				return bins;
			if (parent.equals(srcsTitle))
				return srcs;
			if (parent.equals(mcpTitle))
				return mcp;

			return new String[] { "" };
		}

		@Override
		public Object getParent(final Object element) {

			Activator
					.getDefault()
					.getLog()
					.log(new Status(Status.WARNING, Activator.PLUGIN_ID,
							"Getting parent : " + element));
			if (Arrays.binarySearch(bins, element) != -1)
				return binsTitle;
			if (Arrays.binarySearch(srcs, element) != -1)
				return srcsTitle;
			if (Arrays.binarySearch(mcp, element) != -1)
				return mcpTitle;
			return null;
		}

		@Override
		public boolean hasChildren(final Object element) {
			return !element.equals("");
		}
	}

	/**
	 * The constructor.
	 */
	public FileManagerView() {
		setTitleToolTip("Manage Files");
		setTitleImage(SWTResourceManager.getImage(FileManagerView.class,
				"/icons/full/etool16/copy_edit.gif"));
		setPartName("MineClipse File Manager");
		setContentDescription("Manage MineClipse Files");
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(final Composite parent) {

		Activator.getDefault().getLog()
				.log(new Status(Status.WARNING, Activator.PLUGIN_ID, "hello"));
		TreeViewer treeViewer = new TreeViewer(parent, SWT.BORDER);
		Tree tree = treeViewer.getTree();
		/*
		 * TreeViewerColumn treeViewerColumn = new TreeViewerColumn(treeViewer,
		 * SWT.NONE);
		 * 
		 * TreeColumn trclmnName = treeViewerColumn.getColumn();
		 * trclmnName.setWidth(100); trclmnName.setText("Name");
		 */
		treeViewer.setContentProvider(new ViewContentProvider());
		treeViewer.setInput("parent");
		treeViewer.refresh();
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(final IMenuManager manager) {
				FileManagerView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars() {

		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());

	}

	private void fillLocalPullDown(final IMenuManager manager) {
		manager.add(addElementAction);
		manager.add(removeElementAction);
		manager.add(new Separator());
		manager.add(collapseAllAction);
		manager.add(expandAllAction);
	}

	private void fillContextMenu(final IMenuManager manager) {
		/*
		 * manager.add(action1); manager.add(action2); // Other plug-ins can
		 * contribute there actions here manager.add(new
		 * Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		 */
	}

	private void fillLocalToolBar(final IToolBarManager manager) {
		manager.add(addElementAction);
		manager.add(removeElementAction);
	}

	private void makeActions() {

	}

	private void hookDoubleClickAction() {
	}

	private void showMessage(final String message) {
		/*
		 * MessageDialog.openInformation(viewer.getControl().getShell(),
		 * "Sample View", message);
		 */
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		// viewer.getControl().setFocus();
	}
}