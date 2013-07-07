package org.craftyourmod.mineclipse.ui.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.craftyourmod.mineclipse.core.filemanager.FileManager;
import org.craftyourmod.mineclipse.ui.Messages;
import org.craftyourmod.mineclipse.ui.actions.AddElementAction;
import org.craftyourmod.mineclipse.ui.actions.FileManagerPopupActionGroup;
import org.craftyourmod.mineclipse.ui.actions.RemoveElementAction;
import org.craftyourmod.mineclipse.ui.providers.FileManagerContentProvider;
import org.craftyourmod.mineclipse.ui.providers.FileManagerLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

public class FileManagerView extends ViewPart implements IMenuListener {

	public static final String ID = "org.craftyourmod.mineclipse.ui.views.FileManagerView"; //$NON-NLS-1$
	private Action addElementAction;
	private RemoveElementAction remElementAction;
	private TreeSelection selection;
	private Action refreshViewAction;
	private TreeViewer treeViewer;
	private Menu fContextMenu;
	private FileManagerPopupActionGroup fActionSet;

	public FileManagerView() {

		setPartName(Messages.FileManager_PartName);
		setTitleToolTip(""); //$NON-NLS-1$
		setTitleImage(ResourceManager.getPluginImage(
				"org.craftyourmod.mineclipse.ui", "icons/file_manager.png")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(final Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout gl_container = new GridLayout(1, false);
		gl_container.verticalSpacing = 0;
		gl_container.marginHeight = 0;
		gl_container.marginWidth = 0;
		container.setLayout(gl_container);

		treeViewer = new TreeViewer(container, SWT.BORDER);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				setSelection((TreeSelection) event.getSelection());
			}
		});
		final Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		treeViewer.setLabelProvider(new FileManagerLabelProvider());
		/*
		 * { TreeViewerColumn treeViewerColumn = new TreeViewerColumn(
		 * treeViewer, SWT.NONE); TreeColumn trclmnName =
		 * treeViewerColumn.getColumn(); trclmnName.setWidth(100);
		 * trclmnName.setText(Messages.FileManagerView_trclmnName_text_1);
		 * treeViewerColumn }
		 * 
		 * { TreeViewerColumn treeViewerColumn = new TreeViewerColumn(
		 * treeViewer, SWT.NONE); TreeColumn trclmnId =
		 * treeViewerColumn.getColumn(); trclmnId.setWidth(100);
		 * treeViewerColumn.setLabelProvider(new ColumnLabelProvider() {
		 * 
		 * @Override public String getText(final Object element) { return
		 * element instanceof BaseFile ? ((BaseFile) element) .getId() : ""; }
		 * 
		 * @Override public Color getBackground(final Object element) { return
		 * SWTResourceManager.getColor(21, 92, 17); } });
		 * trclmnId.setText(Messages.FileManagerView_trclmnId_text); }
		 */
		FileManager.INSTANCE.getSupport().addPropertyChangeListener(
				new PropertyChangeListener() {

					@Override
					public void propertyChange(final PropertyChangeEvent arg0) {
						Display.getDefault().syncExec(new Runnable() {

							@Override
							public void run() {
								treeViewer.refresh();

							}
						});
					}

				});
		treeViewer.setContentProvider(new FileManagerContentProvider());
		treeViewer.setLabelProvider(new FileManagerLabelProvider());
		treeViewer.setInput("ROOT"); //$NON-NLS-1$
		treeViewer.refresh();
		createActions();
		initializeToolBar();
		initializeMenu();
		initializePopup();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		remElementAction = new RemoveElementAction(this);
		addElementAction = new AddElementAction(this);
		{
			refreshViewAction = new Action(Messages.FileManagerView_action_text) {

				@Override
				public void run() {
					treeViewer.refresh();
				}

			};
			refreshViewAction.setImageDescriptor(ResourceManager
					.getPluginImageDescriptor("org.craftyourmod.mineclipse.ui", //$NON-NLS-1$
							"icons/refresh_nav.gif")); //$NON-NLS-1$
		}
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(addElementAction);
		toolbarManager.add(remElementAction);
		toolbarManager.add(refreshViewAction);
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
		menuManager.add(addElementAction);
		menuManager.add(remElementAction);
		menuManager.add(refreshViewAction);
	}

	private void initializePopup() {
		MenuManager mgr = new MenuManager("#Popup");//$NON-NLS-1$
		fActionSet = new FileManagerPopupActionGroup(this);
		/*
		 * fActionSet .setContext(new ActionContext(getSelection()));
		 */
		// fActionSet.fillContextMenu(mgr);
		mgr.addMenuListener(this);
		fContextMenu = mgr.createContextMenu(treeViewer.getControl());
		getSite().registerContextMenu(mgr, treeViewer);
		getSite().setSelectionProvider(treeViewer);
		treeViewer.getTree().setMenu(fContextMenu);
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	/**
	 * @return the selection
	 */
	public TreeSelection getSelection() {
		return selection;
	}

	/**
	 * @param selection
	 *            the selection to set
	 */
	public void setSelection(final TreeSelection selection) {
		this.selection = selection;
	}

	@Override
	public void menuAboutToShow(final IMenuManager manager) {
		fActionSet.setContext(new ActionContext(getSelection()));
		fActionSet.fillContextMenu(manager);
		fActionSet.setContext(null);
	}

}
