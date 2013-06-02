package org.craftyourmod.mineclipse.ui;

import org.eclipse.jface.action.Action;
import org.eclipse.wb.swt.ResourceManager;

public class RemoveElementAction extends Action {
	public RemoveElementAction() {
		setImageDescriptor(ResourceManager
				.getImageDescriptor("/icons/file-delete-16x16.png"));
		setToolTipText(Messages.RemoveElement_Tooltip);

	}
}
