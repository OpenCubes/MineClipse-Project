package org.craftyourmod.mineclipse.installer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.craftyourmod.mineclipse.installer.pages.ConfigPage;
import org.craftyourmod.mineclipse.installer.pages.InstallPage;
import org.craftyourmod.mineclipse.installer.pages.LicencePage;
import org.craftyourmod.mineclipse.installer.pages.SystemTestPage;
import org.craftyourmod.mineclipse.installer.pages.WelcomePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.mihalis.opal.obutton.AbstractButtonRenderer;
import org.mihalis.opal.obutton.DefaultButtonRenderer;
import org.mihalis.opal.obutton.OButton;
import org.mihalis.opal.utils.SWTGraphicUtil;
import org.mihalis.opal.flatButton.FlatButton;

import com.richclientgui.toolbox.progressIndicator.CoolProgressBar;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.StackLayout;

public class Installer {
	private static final StackLayout LAYOUT = new StackLayout();
	List<Composite> pages = new ArrayList<>();
	public HashMap<String, String> config = new HashMap<>();
	int i = 0;
	private boolean hasNext = true;

	/**
	 * This is the grey theme button renderer
	 */
	public static class GreyButtonRenderer extends AbstractButtonRenderer {

		private static GreyButtonRenderer instance;
		private Color FIRST_BACKGROUND_COLOR = SWTGraphicUtil
				.createDisposableColor(87, 87, 87);
		private Color SECOND_BACKGROUND_COLOR = SWTGraphicUtil
				.createDisposableColor(48, 48, 48);

		private GreyButtonRenderer() {
			super();

		}

		@Override
		protected Color getFontColor() {
			return Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
		}

		@Override
		protected Color getFirstBackgroundColor() {
			if (FIRST_BACKGROUND_COLOR == null)
				FIRST_BACKGROUND_COLOR = SWTGraphicUtil.createDisposableColor(
						87, 87, 87);
			return FIRST_BACKGROUND_COLOR;
		}

		@Override
		protected Color getSecondBackgroundColor() {
			if (SECOND_BACKGROUND_COLOR == null)
				SECOND_BACKGROUND_COLOR = SWTGraphicUtil.createDisposableColor(
						48, 48, 48);
			return SECOND_BACKGROUND_COLOR;
		}

		public static GreyButtonRenderer getInstance() {
			if (instance == null) {
				instance = new GreyButtonRenderer();
			}
			return instance;
		}
	}

	protected Object result;
	protected Shell shell;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public Installer() {
	}

	private Composite current, previous;
	private boolean toPrevious;
	private OButton btnNext;
	private OButton btnPrev;
	protected boolean hasPrev;

	public void showPage(Composite c) {
		if (c == null || c == current) {
			return;
		}
		if (!toPrevious) {
			previous = current;
			btnPrev.setEnabled(true);
		} else {
			toPrevious = false;
			btnPrev.setEnabled(false);
		}
		LAYOUT.topControl = c;
		c.update();
	}

	/**
	 * Open the dialog.
	 * 
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	public void setNextEnabled(boolean b) {

			btnNext.setEnabled(b);
			if(!hasNext)
				btnNext.setEnabled(false);
			btnNext.redraw();

	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(SWT.CLOSE | SWT.APPLICATION_MODAL);
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setBackgroundImage(SWTResourceManager.getImage(Installer.class,
				"/icons/bg.gif"));
		shell.setSize(600, 500);
		shell.setLayout(new GridLayout(1, false));

		Composite header = new Composite(shell, SWT.NONE);
		header.setLayout(new GridLayout(2, false));
		header.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

		Label lblIcn = new Label(header, SWT.NONE);
		lblIcn.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false,
				1, 3));
		lblIcn.setImage(SWTResourceManager.getImage(Installer.class,
				"/icons/logo_install.png"));

		Label lblMineclipseInstallation = new Label(header, SWT.NONE);
		lblMineclipseInstallation.setLayoutData(new GridData(SWT.LEFT,
				SWT.CENTER, true, false, 1, 1));
		lblMineclipseInstallation.setForeground(SWTResourceManager
				.getColor(SWT.COLOR_WHITE));
		lblMineclipseInstallation.setFont(SWTResourceManager.getFont(
				"Segoe UI", 10, SWT.BOLD));
		lblMineclipseInstallation.setText("MineClipse Installation");

		Label lblWelcome = new Label(header, SWT.NONE);
		lblWelcome.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblWelcome.setText("1. Welcome");

		final CoolProgressBar coolProgressBar = new CoolProgressBar(header,
				SWT.NONE, SWTResourceManager.getImage(Installer.class,
						"/icons/pgb_border.png"), SWTResourceManager.getImage(
						Installer.class, "/icons/pgb_filled.png"),
				SWTResourceManager.getImage(Installer.class,
						"/icons/pgb_empty.png"), SWTResourceManager.getImage(
						Installer.class, "/icons/pgb_border_r.png"));
		coolProgressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		coolProgressBar.updateProgress(0);
		coolProgressBar.update();
		System.out.println(coolProgressBar.getCurrentProgress());
		final Composite content = new Composite(shell, SWT.NONE);
		content.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		content.setLayout(LAYOUT);

		content.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		Composite welcomePage = new WelcomePage(content, SWT.NONE);
		Composite licencePage = new LicencePage(content, SWT.NONE, this);
		Composite systestPage = new SystemTestPage(content, SWT.NONE, this);
		Composite configuPage = new ConfigPage(content, SWT.NONE);
		Composite installPage = new InstallPage(content, 0, this);
		pages.add(welcomePage);
		pages.add(licencePage);
		pages.add(systestPage);
		pages.add(configuPage);
		pages.add(installPage);
		Composite footer = new Composite(shell, SWT.NONE);
		footer.setLayout(new GridLayout(2, false));
		footer.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false,
				1, 1));

		btnPrev = new OButton(footer, SWT.NONE);
		btnPrev.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				i--;
				final double percentage = ((double)i / (pages.size()-1));
				System.out.println(percentage);
				coolProgressBar.updateProgress(percentage);
				showPage(pages.get(i));
				hasNext = i < pages.size()-1;
				btnNext.setEnabled(hasNext);
				content.layout();
			}
		});
		btnPrev.setEnabled(false);
		btnPrev.setText("< Previous");
		btnPrev.setButtonRenderer(GreyButtonRenderer.getInstance());

		btnNext = new OButton(footer, SWT.NONE);
		btnNext.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (hasNext) {
					i++;
					final double percentage = ((double)i / (pages.size()-1));
					System.out.println(percentage);
					coolProgressBar.updateProgress(percentage);
					showPage(pages.get(i));
					hasNext = i < pages.size()-1;
					btnNext.setEnabled(hasNext);
					hasPrev = i >= 0;
					content.layout();
				}
			}
		});
		btnNext.setText("Next >");
		btnNext.setButtonRenderer(GreyButtonRenderer.getInstance());

		showPage(pages.get(i));
	}

	public static void main(String[] args) {
		new Installer().open();
		
	}
	
}
