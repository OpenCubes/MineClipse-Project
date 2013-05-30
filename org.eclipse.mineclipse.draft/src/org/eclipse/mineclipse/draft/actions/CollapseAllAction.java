package org.eclipse.mineclipse.draft.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.mineclipse.draft.Activator;

public class CollapseAllAction extends Action {
	public CollapseAllAction() {
		super("Collapse All", Activator
				.getImageDescriptor("/icons/collapseall.gif"));
	}
}
