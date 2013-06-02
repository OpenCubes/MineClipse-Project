package org.craftyourmod.mineclipse.ui.actions;

import org.craftyourmod.mineclipse.ui.Activator;
import org.craftyourmod.mineclipse.ui.Messages;
import org.craftyourmod.mineclipse.ui.wizards.AddBinaryWizard;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.wb.swt.ResourceManager;

public class AddElementAction extends Action {

	public AddElementAction() {
		setToolTipText(Messages.AddElement_Tooltip);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(
				"org.craftyourmod.mineclipse.ui", "icons/newfile_wiz.gif"));
	}

	@Override
	public void run() {
		WizardDialog wiz = new WizardDialog(Activator.getDefault()
				.getWorkbench().getActiveWorkbenchWindow().getShell(),
				new AddBinaryWizard());
		wiz.setBlockOnOpen(true);

		wiz.open();
	}
}
