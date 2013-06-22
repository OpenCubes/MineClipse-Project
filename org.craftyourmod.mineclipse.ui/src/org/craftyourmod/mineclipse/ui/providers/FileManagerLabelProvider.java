package org.craftyourmod.mineclipse.ui.providers;

import java.io.File;

import org.craftyourmod.mineclipse.core.filemanager.BaseFile;
import org.craftyourmod.mineclipse.core.filemanager.BinaryFile;
import org.craftyourmod.mineclipse.core.filemanager.SourceFile;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

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
		if ((element instanceof BinaryFile) || element.equals("BINS"))
			return ResourceManager.getPluginImage(
					"org.craftyourmod.mineclipse.ui", "icons/jar.gif");

		if ((element instanceof SourceFile) || element.equals("SRCS"))
			return ResourceManager.getPluginImage(
					"org.craftyourmod.mineclipse.ui", "icons/source_java.gif");
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
