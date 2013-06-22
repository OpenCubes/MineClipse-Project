package org.craftyourmod.mineclipse.ui.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;

public class MineClipseGeneralPreferencePage extends PreferencePage {

	/**
	 * Create the preference page.
	 */
	public MineClipseGeneralPreferencePage() {
		setTitle("MineClipse ");
	}

	/**
	 * Create contents of the preference page.
	 * 
	 * @param parent
	 */
	@Override
	public Control createContents(final Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		return container;
	}

	/**
	 * Initialize the preference page.
	 */
	public void init(final IWorkbench workbench) {
		// Initialize the preference page
	}

}
