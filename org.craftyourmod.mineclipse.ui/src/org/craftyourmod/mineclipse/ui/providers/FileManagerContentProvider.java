package org.craftyourmod.mineclipse.ui.providers;

import java.io.File;

import org.craftyourmod.mineclipse.core.filemanager.BaseFile;
import org.craftyourmod.mineclipse.core.filemanager.BinaryFile;
import org.craftyourmod.mineclipse.core.filemanager.FileManager;
import org.craftyourmod.mineclipse.core.filemanager.SourceFile;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FileManagerContentProvider implements ITreeContentProvider {

	private final FileManager manager = FileManager.INSTANCE;

	@Override
	public void dispose() {

	}

	@Override
	public void inputChanged(final Viewer viewer, final Object oldInput,
			final Object newInput) {
	}

	@Override
	public Object[] getElements(final Object inputElement) {
		if (inputElement.equals("ROOT"))
			return new String[] { "BINS", "SRCS" };
		if (inputElement.equals("BINS"))
			return manager.getBins().toArray();
		if (inputElement.equals("SRCS"))
			return manager.getSrcs().toArray();
		if (inputElement instanceof BaseFile)
			return null;
		if (inputElement instanceof File)
			return null;
		return null;
	}

	@Override
	public Object[] getChildren(final Object parentElement) {
		return getElements(parentElement);
	}

	@Override
	public Object getParent(final Object element) {
		if (element.equals("ROOT"))
			return null;
		if (element.equals("BINS") || element.equals("SRCS"))
			return "ROOT";
		if (element instanceof BinaryFile)
			return "BINS";
		if (element instanceof SourceFile)
			return "SRCS";
		if (element instanceof File)
			return null;
		return null;
	}

	@Override
	public boolean hasChildren(final Object element) {
		if (element.equals("ROOT"))
			return true;
		if (element.equals("BINS") || element.equals("SRCS"))
			return true;
		if (element instanceof BinaryFile)
			return false;
		if (element instanceof SourceFile)
			return false;
		if (element instanceof File)
			return false;
		return false;
	}

}
