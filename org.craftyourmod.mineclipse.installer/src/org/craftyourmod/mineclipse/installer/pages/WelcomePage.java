package org.craftyourmod.mineclipse.installer.pages;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

public class WelcomePage extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public WelcomePage(Composite parent, int style) {
		super(parent, style);
		setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		setLayout(new GridLayout(1, false));
		
		Label lblWelcome = new Label(this, SWT.NONE);
		lblWelcome.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWelcome.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblWelcome.setText("Welcome !");
		
		Label lblIt = new Label(this, SWT.NONE);
		lblIt.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblIt.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblIt.setText("You are going to install MineClipse.\r\nIt will first detect tour configuration,\r\nsee what is needed and install it.\r\nNo adware will be installed.\r\nIt will be shown what will be done.\r\nIt is recommenended to close other apps. ");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
