package org.eclipse.mineclipse.draft.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class GlimpseView extends ViewPart {

	public static final String ID = "org.eclipse.mineclipse.draft.views.GlimpseView"; //$NON-NLS-1$
	private Action action;
	private Action action_1;
	private Action action_2;
	private Action action_3;

	public GlimpseView() {
		setTitleImage(ResourceManager.getPluginImage(
				"org.eclipse.mineclipse.draft", "icons/preview.gif"));
	}

	/**
	 * Create contents of the view part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(final Composite parent) {
		{
			Label label = new Label(parent, SWT.NONE);
			label.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
			label.setImage(ResourceManager.getPluginImage(
					"org.eclipse.mineclipse.draft", "icons/draft_preview.gif"));
		}

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		{
			action = new Action("") {

			};
			action.setImageDescriptor(ResourceManager.getPluginImageDescriptor(
					"org.eclipse.mineclipse.draft", "icons/move.gif"));
		}
		{
			action_1 = new Action("") {

			};
			action_1.setImageDescriptor(ResourceManager
					.getPluginImageDescriptor("org.eclipse.jdt.ui",
							"/icons/full/elcl16/refresh.gif"));
		}
		{
			action_2 = new Action("") {

			};
		}
		{
			action_3 = new Action("Pick Color") {

			};
		}
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager toolbarManager = getViewSite().getActionBars()
				.getToolBarManager();
		toolbarManager.add(action);
		toolbarManager.add(action_1);
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager menuManager = getViewSite().getActionBars()
				.getMenuManager();
		menuManager.add(action_3);
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
