package org.craftyourmod.mineclipse.ui.providers;

import java.io.File;

import org.craftyourmod.mineclipse.core.filemanager.BaseFile;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class FileManagerLabelProvider implements ILabelProvider {

	@Override
	public void addListener(final ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(final Object element, final String property) {
		return false;
	}

	@Override
	public void removeListener(final ILabelProviderListener listener) {
	}

	@Override
	public Image getImage(final Object element) {
		return null;
	}

	@Override
	public String getText(final Object element) {
		if (element.equals("ROOT"))
			return "";
		if (element.equals("BINS"))
			return "Minecraft Binaries";
		if (element.equals("SRCS"))
			return "Minecraft Sources";
		if (element instanceof BaseFile)
			return ((BaseFile) element).getName();
		if (element instanceof File)
			return ((File) element).getName();
		return "Unknow";
	}

}
