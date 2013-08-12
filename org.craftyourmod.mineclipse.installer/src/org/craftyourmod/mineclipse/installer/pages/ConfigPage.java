package org.craftyourmod.mineclipse.installer.pages;

import java.io.File;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.Section;

public class ConfigPage extends Composite {
	private Text txtPath;
	private Text text;
	private Text text_1;
	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public ConfigPage(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));

		final Button btnIHavaInstalled = new Button(this, SWT.CHECK);

		btnIHavaInstalled.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));

		Label lblIHaveInstalled = new Label(this, SWT.NONE);
		lblIHaveInstalled.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				btnIHavaInstalled.setSelection(!btnIHavaInstalled
						.getSelection());
				if (!btnIHavaInstalled.getSelection())
					txtPath.setText("");
			}
		});
		lblIHaveInstalled.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblIHaveInstalled.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 2, 1));
		lblIHaveInstalled
				.setText("I have installed eclipse and the path to eclipse.exe is :");
		new Label(this, SWT.NONE);

		txtPath = new Text(this, SWT.BORDER);
		txtPath.setEditable(false);
		txtPath.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_BACKGROUND));
		txtPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Button btnSelect = new Button(this, SWT.NONE);
		btnSelect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog d = new FileDialog(getShell());
				d.setFilterExtensions(new String[] { "eclipse.exe" });
				File f = new File(d.open());
				if (f.exists()) {
					btnIHavaInstalled.setSelection(true);
					txtPath.setText(f.getAbsolutePath());
				}
			}
		});
		btnSelect.setText("Select");

		final Button btnroxy = new Button(this, SWT.CHECK);
		btnroxy.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		Label lblIUseA = new Label(this, SWT.NONE);

		lblIUseA.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false,
				2, 1));
		lblIUseA.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblIUseA.setText("I use a Proxy");
		new Label(this, SWT.NONE);

		final Section sctnAuthInfos = formToolkit.createSection(this,
				Section.CLIENT_INDENT | Section.COMPACT | Section.TREE_NODE
						| Section.SHORT_TITLE_BAR);
		sctnAuthInfos.setBackground(SWTResourceManager.getColor(51, 51, 51));
		sctnAuthInfos.setTitleBarGradientBackground(SWTResourceManager
				.getColor(51, 51, 51));
		sctnAuthInfos.setTitleBarForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		sctnAuthInfos.setTitleBarBorderColor(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BORDER));
		sctnAuthInfos.setTitleBarBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		sctnAuthInfos.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		sctnAuthInfos.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		sctnAuthInfos.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1));
		sctnAuthInfos.setText("Auth infos");

		Composite composite = new Composite(sctnAuthInfos, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Label lblUser = new Label(composite, SWT.NONE);
		lblUser.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblUser.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblUser.setText("Login");

		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1,
				1));
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				btnroxy.setSelection(!text.getText().isEmpty());
			}
		});
		text.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_BACKGROUND));

		Label lblPassword = new Label(composite, SWT.NONE);
		lblPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblPassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPassword.setText("Password");

		text_1 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false,
				1, 1));
		text_1.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_LIST_BACKGROUND));
		text_1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				btnroxy.setSelection(!text_1.getText().isEmpty());
			}
		});
		new Label(this, SWT.NONE);
		sctnAuthInfos.setClient(composite);

		final Section sctnUpdateCycle = formToolkit.createSection(this,
				Section.TREE_NODE | Section.SHORT_TITLE_BAR);
		sctnUpdateCycle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 3, 1));
		sctnUpdateCycle.setTitleBarGradientBackground(SWTResourceManager
				.getColor(51, 51, 51));
		sctnUpdateCycle.setTitleBarForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		sctnUpdateCycle.setTitleBarBorderColor(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BORDER));
		sctnUpdateCycle.setTitleBarBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		sctnUpdateCycle.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		sctnUpdateCycle.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		sctnUpdateCycle.setBackground(SWTResourceManager.getColor(51, 51, 51));
		sctnUpdateCycle.setText("Update cycle");

		Composite composite_1 = new Composite(sctnUpdateCycle, SWT.NONE);
		composite_1
				.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		composite_1.setLayout(new GridLayout(3, false));

		final Button btnRelease = new Button(composite_1, SWT.RADIO);
		btnRelease.setSelection(true);

		Label lblRelease = new Label(composite_1, SWT.NONE);
		lblRelease.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				btnRelease.setSelection(!btnRelease.getSelection());
			}
		});
		lblRelease.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblRelease.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblRelease.setText("Release :");

		Label lblOnlyStableAnd = new Label(composite_1, SWT.WRAP);
		lblOnlyStableAnd.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblOnlyStableAnd.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 2));
		lblOnlyStableAnd
				.setText("Only stable and completly tested releases\r\nwill be downloaded (e. g. 1.0, 1.1,1.2...)\r\nRecommended");
		new Label(composite_1, SWT.NONE);

		Label label = new Label(composite_1, SWT.NONE);

		final Button btnSnapshots = new Button(composite_1, SWT.RADIO);

		Label lblSnapshots = new Label(composite_1, SWT.NONE);
		
		lblSnapshots
				.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblSnapshots.setFont(SWTResourceManager
				.getFont("Segoe UI", 9, SWT.BOLD));
		lblSnapshots.setText("Snapshot :");

		Label lblTheVersionsWill = new Label(composite_1, SWT.NONE);
		lblTheVersionsWill.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblTheVersionsWill.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 1, 2));
		lblTheVersionsWill
				.setText("The versions will be partially tested,\r\nbut not still officially released");
		new Label(composite_1, SWT.NONE);

		Label label_1 = new Label(composite_1, SWT.NONE);

		final Button btnDev = new Button(composite_1, SWT.RADIO);

		Label lblDev = new Label(composite_1, SWT.NONE);
		/*lblDev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (!btnDev.getSelection()) {
					btnDev.setSelection(true);
					btnSnapshots.setSelection(false);
					btnRelease.setSelection(false);
				} else {
				}
			}
		});
		lblSnapshots.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (!btnSnapshots.getSelection()) {
					btnDev.setSelection(false);
					btnSnapshots.setSelection(true);
					btnRelease.setSelection(false);
				} else {
				}
			}
		});
		lblRelease.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				if (!btnRelease.getSelection()) {
					btnDev.setSelection(false);
					btnSnapshots.setSelection(false);
					btnRelease.setSelection(true);
				} else {
				}
			}
		});*/
		lblDev.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblDev.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblDev.setText("Dev :");

		Label lblNitghlyBuildsNot = new Label(composite_1, SWT.NONE);
		lblNitghlyBuildsNot.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblNitghlyBuildsNot
				.setText("Nitghly builds, not tested, in work versions.\r\nOnly for testers. Very unstable.");
		sctnUpdateCycle.setClient(composite_1);
		sctnUpdateCycle.setExpanded(true);
		lblIUseA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				btnroxy.setSelection(!btnroxy.getSelection());
				sctnAuthInfos.setExpanded(btnroxy.getSelection());
				sctnUpdateCycle.setExpanded(!btnroxy.getSelection());
			}
		});
		btnIHavaInstalled.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtPath.setText("");
				sctnAuthInfos.setExpanded(btnroxy.getSelection());
				sctnUpdateCycle.setExpanded(!btnroxy.getSelection());
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
