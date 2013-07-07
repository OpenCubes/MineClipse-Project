package org.craftyourmod.mineclipse.ui.actions;

import org.craftyourmod.mineclipse.core.filemanager.BinaryFile;
import org.craftyourmod.mineclipse.ui.Activator;
import org.craftyourmod.mineclipse.ui.Messages;
import org.craftyourmod.mineclipse.ui.views.FileManagerView;
import org.craftyourmod.mineclipse.ui.wizards.AddBinaryWizard;
import org.craftyourmod.mineclipse.ui.wizards.AddSourceWizard;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.wb.swt.ResourceManager;

public class AddElementAction extends Action {

	private final FileManagerView view;

	public AddElementAction(final FileManagerView view) {
		setToolTipText(Messages.AddElement_Tooltip);
		setText(Messages.AddElementAction_Text);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(
				"org.craftyourmod.mineclipse.ui", "icons/newfile_wiz.gif")); //$NON-NLS-1$ //$NON-NLS-2$
		this.view = view;

	}

	@Override
	public void run() {
		Object s = view.getSelection().getPaths()[0].getLastSegment();

		if (s.equals("BINS") || (s instanceof BinaryFile)) { //$NON-NLS-1$

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

	public boolean isAvailable(final ActionContext context) {

		if (context.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection s = (IStructuredSelection) context
					.getSelection();/*
									 * if (s.equals("BINS") || (s instanceof
									 * BinaryFile) || (s instanceof SourceFile)
									 * || s.equals("SRCS"))
									 */
			return true;
		}
		return context != null;

	}
}
