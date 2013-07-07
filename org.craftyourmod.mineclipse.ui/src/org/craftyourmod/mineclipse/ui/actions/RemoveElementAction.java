package org.craftyourmod.mineclipse.ui.actions;

import org.craftyourmod.mineclipse.core.filemanager.BinaryFile;
import org.craftyourmod.mineclipse.core.filemanager.FileManager;
import org.craftyourmod.mineclipse.core.filemanager.SourceFile;
import org.craftyourmod.mineclipse.ui.Messages;
import org.craftyourmod.mineclipse.ui.views.FileManagerView;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.wb.swt.ResourceManager;

public class RemoveElementAction extends Action {
	private final FileManagerView fileManagerView;

	public RemoveElementAction(final FileManagerView fileManagerView) {
		this.fileManagerView = fileManagerView;
		setText(Messages.RemoveElementAction_Text);
		setToolTipText(Messages.FileManagerView_remElementAction_toolTipText);
		setImageDescriptor(ResourceManager
				.getPluginImageDescriptor("org.craftyourmod.mineclipse.ui", //$NON-NLS-1$
						"icons/file-delete-16x16.png")); //$NON-NLS-1$
	}

	@Override
	public void run() {
		Object o = fileManagerView.getSelection().getPaths()[0]
				.getLastSegment();
		try {
			if (o instanceof BinaryFile) {
				BinaryFile bin = (BinaryFile) o;
				FileManager.INSTANCE.removeBin(bin);
			} else if (o instanceof SourceFile) {
				SourceFile src = (SourceFile) o;
				FileManager.INSTANCE.removeSrc(src);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isAvailable(final ActionContext context) {
		if (context.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection s = (IStructuredSelection) context
					.getSelection();
			Object o = s.getFirstElement();
			try {
				if (o instanceof BinaryFile)
					return true;
				else if (o instanceof SourceFile)
					return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}
