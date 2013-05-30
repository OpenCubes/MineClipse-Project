package org.eclipse.mineclipse.draft.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.mineclipse.draft.Activator;

public class RemoveElementAction extends Action {

	public RemoveElementAction() {
		super("Remove Element", Activator
				.getImageDescriptor("/icons/file-delete-16x16.png"));
	}

}
