package org.craftyourmod.mineclipse.ui.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.craftyourmod.mineclipse.core.filemanager.FileManager;
import org.craftyourmod.mineclipse.ui.Messages;
import org.craftyourmod.mineclipse.ui.actions.AddElementAction;
import org.craftyourmod.mineclipse.ui.actions.RemoveElementAction;
import org.craftyourmod.mineclipse.ui.providers.FileManagerContentProvider;
import org.craftyourmod.mineclipse.ui.providers.FileManagerLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;

public class FileManagerView extends ViewPart {

	public static final String ID = "org.craftyourmod.mineclipse.ui.views.FileManagerView"; //$NON-NLS-1$
	private Action addElementAction;
	private RemoveElementAction remElementAction;
	private TreeSelection selection;
	private Action refreshViewAction;
	private TreeViewer treeViewer;

	public FileManagerView() {

		setPartName(Messages.FileManager_PartName);
		setTitleToolTip(Messages.FileManagerView_this_titleToolTip);
		setTitleImage(ResourceManager.getPluginImage(
				"org.craftyourmod.mineclipse.ui", "icons/file_manager.png"));
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
		treeViewer.setInput("ROOT");
		treeViewer.refresh();
		createActions();
		initializeToolBar();
		initializeMenu();
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
					.getPluginImageDescriptor("org.craftyourmod.mineclipse.ui",
							"icons/refresh_nav.gif"));
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

}
