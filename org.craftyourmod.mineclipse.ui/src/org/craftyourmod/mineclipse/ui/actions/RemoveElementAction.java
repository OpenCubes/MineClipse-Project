package org.craftyourmod.mineclipse.ui.actions;

import org.craftyourmod.mineclipse.core.filemanager.BinaryFile;
import org.craftyourmod.mineclipse.core.filemanager.FileManager;
import org.craftyourmod.mineclipse.core.filemanager.SourceFile;
import org.craftyourmod.mineclipse.ui.Messages;
import org.craftyourmod.mineclipse.ui.views.FileManagerView;
import org.eclipse.jface.action.Action;
import org.eclipse.wb.swt.ResourceManager;

public class RemoveElementAction extends Action {
	private final FileManagerView fileManagerView;

	public RemoveElementAction(final FileManagerView fileManagerView) {
		this.fileManagerView = fileManagerView;
		setToolTipText(Messages.FileManagerView_remElementAction_toolTipText);
		setImageDescriptor(ResourceManager
				.getPluginImageDescriptor("org.craftyourmod.mineclipse.ui",
						"icons/file-delete-16x16.png"));
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

}
