package org.craftyourmod.mineclipse.ui.actions;

import org.craftyourmod.mineclipse.ui.Messages;
import org.eclipse.jface.action.Action;
import org.eclipse.wb.swt.ResourceManager;

public class AddElementAction extends Action {

	public AddElementAction() {
		setToolTipText(Messages.AddElement_Tooltip);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(
				"org.craftyourmod.mineclipse.ui", "icons/newfile_wiz.gif"));
	}

}
