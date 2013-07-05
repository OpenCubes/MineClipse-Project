package org.craftyourmod.mineclipse.ui.actions;

import org.craftyourmod.mineclipse.ui.views.FileManagerView;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionGroup;

public class FileManagerPopupActionGroup extends ActionGroup {
	private final AddElementAction addElementAction;
	private final RemoveElementAction remElementAction;
	private final FileManagerView fFileManagerView;

	public FileManagerPopupActionGroup(final FileManagerView view) {
		super();
		fFileManagerView = view;
		remElementAction = new RemoveElementAction(fFileManagerView);
		addElementAction = new AddElementAction(fFileManagerView);

	}

	@Override
	public void fillContextMenu(final IMenuManager menu) {
		menu.removeAll();
		if (addElementAction.isAvailable(getContext()))
			menu.add(addElementAction);
		if (remElementAction.isAvailable(getContext()))
			menu.add(remElementAction);
	}

	@Override
	public void fillActionBars(final IActionBars actionBars) {
		if (addElementAction.isAvailable(getContext()))
			actionBars.getToolBarManager().add(addElementAction);
		if (remElementAction.isAvailable(getContext()))
			actionBars.getToolBarManager().add(remElementAction);
		super.fillActionBars(actionBars);
	}

}
