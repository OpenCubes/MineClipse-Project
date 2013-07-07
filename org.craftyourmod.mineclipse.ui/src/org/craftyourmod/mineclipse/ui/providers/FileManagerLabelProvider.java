package org.craftyourmod.mineclipse.ui.providers;

import java.io.File;

import org.craftyourmod.mineclipse.core.filemanager.BaseFile;
import org.craftyourmod.mineclipse.core.filemanager.BinaryFile;
import org.craftyourmod.mineclipse.core.filemanager.FileManager;
import org.craftyourmod.mineclipse.core.filemanager.SourceFile;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

public class FileManagerLabelProvider extends StyledCellLabelProvider implements
		ILabelProvider {

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

	// @Override
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

	/*
	 * @Override public void update(final ViewerCell cell) {
	 * cell.setText(getText(cell.getElement()));
	 * cell.setImage(getImage(cell.getElement())); }
	 * 
	 * @Override public Color getForeground(final Object element, final int
	 * columnIndex) { // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public Color getBackground(final Object element, final int
	 * columnIndex) { // TODO Auto-generated method stub return null; }
	 */

	public StyledString getStyledText(final Object element) {
		if (element instanceof BaseFile) {
			BaseFile f = (BaseFile) element;

			StyledString s = new StyledString(f.getName());
			s.append(" [" + f.getId() + "]", StyledString.DECORATIONS_STYLER);
			s.append(" - " + f.getInput() + "", StyledString.QUALIFIER_STYLER);
			return s;
		}
		StyledString s = new StyledString(getText(element));
		if (element.equals("BINS")) {
			int i = FileManager.INSTANCE.getBins().size();
			s.append(" (" + i + ")", StyledString.COUNTER_STYLER);
		}
		if (element.equals("SRCS")) {
			int i = FileManager.INSTANCE.getSrcs().size();
			s.append(" (" + i + ")", StyledString.COUNTER_STYLER);
		}
		return s;
	}

	@Override
	public void update(final ViewerCell cell) {
		Object element = cell.getElement();
		StyledString text = getStyledText(element);
		cell.setText(text.toString());
		cell.setStyleRanges(text.getStyleRanges());
		cell.setImage(getImage(element));
		super.update(cell);
	}
}
