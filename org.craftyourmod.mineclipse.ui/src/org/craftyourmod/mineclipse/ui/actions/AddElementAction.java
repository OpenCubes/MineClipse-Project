package org.craftyourmod.mineclipse.ui.actions;

import org.craftyourmod.mineclipse.core.filemanager.BinaryFile;
import org.craftyourmod.mineclipse.ui.Activator;
import org.craftyourmod.mineclipse.ui.Messages;
import org.craftyourmod.mineclipse.ui.views.FileManagerView;
import org.craftyourmod.mineclipse.ui.wizards.AddBinaryWizard;
import org.craftyourmod.mineclipse.ui.wizards.AddSourceWizard;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.wb.swt.ResourceManager;

public class AddElementAction extends Action {

	private final FileManagerView view;

	public AddElementAction(final FileManagerView view) {
		setToolTipText(Messages.AddElement_Tooltip);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(
				"org.craftyourmod.mineclipse.ui", "icons/newfile_wiz.gif"));
		this.view = view;
	}

	@Override
	public void run() {
		Object s = view.getSelection().getPaths()[0].getLastSegment();
		if (s.equals("BINS") || (s instanceof BinaryFile)) {

			WizardDialog wiz = new WizardDialog(Activator.getDefault()
					.getWorkbench().getActiveWorkbenchWindow().getShell(),
					new AddBinaryWizard());
			wiz.setBlockOnOpen(true);
			wiz.open();
		} else {

			WizardDialog wiz = new WizardDialog(Activator.getDefault()
					.getWorkbench().getActiveWorkbenchWindow().getShell(),
					new AddSourceWizard());
			wiz.setBlockOnOpen(true);
			wiz.open();
		}
	}
}
