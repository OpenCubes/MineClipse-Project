package org.craftyourmod.mineclipse.ui.pages;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.craftyourmod.mineclipse.ui.Messages;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddBinaryPage extends WizardPage {
	private Text textName;
	private Text textPath;
	private Text txtServerpath;
	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private boolean checked;
	private ImageHyperlink mghprlnkChecker;
	private ControlDecoration ctrlDecServerURL;
	private Button btnGetBinariesFromFile;
	private Button btnDowload;

	/**
	 * Create the wizard.
	 */
	public AddBinaryPage() {
		super("wizardPage");
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(
				"org.craftyourmod.mineclipse.ui", "icons/baner_addbin01.gif"));
		setTitle(Messages.AddBinaryWizardConfigPage_Title);
		setDescription(Messages.AddBinaryWizardConfigPage_Description);
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	@Override
	public void createControl(final Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(5, false));

		Label lblName = new Label(container, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblName.setText(Messages.AddBinaryConfigWizardPage_lblName_text);

		textName = new Text(container, SWT.BORDER);
		textName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				validate();
			}
		});
		textName.setMessage(Messages.AddBinaryWizardConfigPage_Name_Msg);
		textName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				3, 1));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		btnGetBinariesFromFile = new Button(container, SWT.RADIO);
		btnGetBinariesFromFile.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER,
				false, false, 3, 1));
		btnGetBinariesFromFile
				.setText(Messages.AddBinaryConfigWizardPage_btnGetBinariesFrom_text);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		final Composite composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false,
				2, 1));

		Label lblPath = new Label(composite, SWT.NONE);
		lblPath.setEnabled(false);
		lblPath.setText(Messages.AddBinaryConfigWizardPage_lblPath_text);

		textPath = new Text(composite, SWT.BORDER);
		textPath.setText(new File(System.getenv("APPDATA"),
				"/.minecraft/bin/minecraft.jar").getAbsolutePath());
		textPath.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				validate();
			}
		});
		textPath.setEnabled(false);
		// textPath.setText("");
		textPath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Button button = new Button(composite, SWT.NONE);
		button.setEnabled(false);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				FileDialog dialog = new FileDialog(Display.getCurrent()
						.getActiveShell(), SWT.OPEN);
				dialog.setFilterExtensions(new String[] { "*.jar" });
				String result = dialog.open();
				textPath.setText(result);
				validate();
			}
		});
		button.setText(Messages.AddBinaryConfigWizardPage_button_text);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		btnDowload = new Button(container, SWT.RADIO);

		btnDowload.setSelection(true);
		btnDowload.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 3, 1));
		btnDowload.setText(Messages.AddBinaryConfigWizardPage_btnDowload_text);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		final Composite compositeDl = new Composite(container, SWT.NONE);
		GridLayout gl_compositeDl = new GridLayout(3, false);
		gl_compositeDl.horizontalSpacing = 10;
		compositeDl.setLayout(gl_compositeDl);
		compositeDl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
				false, 2, 1));

		Label lblServerPath = new Label(compositeDl, SWT.NONE);
		lblServerPath
				.setText(Messages.AddBinaryConfigWizardPage_lblServerPath_text);

		txtServerpath = new Text(compositeDl, SWT.BORDER);

		ctrlDecServerURL = new ControlDecoration(txtServerpath, SWT.LEFT
				| SWT.TOP);
		ctrlDecServerURL.setImage(SWTResourceManager.getImage(
				AddBinaryPage.class,
				"/org/eclipse/jface/fieldassist/images/info_ovr.gif"));
		txtServerpath.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				checked = false;
				validate();
			}
		});
		txtServerpath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		txtServerpath
				.setText("http://s3.amazonaws.com/MinecraftDownload/minecraft.jar");

		Button btnCheck = new Button(compositeDl, SWT.NONE);
		btnCheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				check();
			}
		});
		btnCheck.setText(Messages.AddBinaryConfigWizardPage_btnCheck_text);
		new Label(compositeDl, SWT.NONE);

		mghprlnkChecker = formToolkit.createImageHyperlink(compositeDl,
				SWT.NONE);
		mghprlnkChecker.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1));
		mghprlnkChecker.setImage(ResourceManager.getPluginImage(
				"org.craftyourmod.mineclipse.ui", "icons/ok.gif"));
		mghprlnkChecker.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		formToolkit.paintBordersFor(mghprlnkChecker);
		mghprlnkChecker
				.setText(Messages.AddBinaryConfigWizardPage_mghprlnkChecker_text);
		new Label(compositeDl, SWT.NONE);
		new Label(container, SWT.NONE);
		btnDowload.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				activate(compositeDl, true);
				activate(composite, false);
				validate();
			}
		});
		btnGetBinariesFromFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				activate(compositeDl, false);
				activate(composite, true);
				validate();
			}
		});
	}

	public Text getTextName() {
		return textName;
	}

	public void setTextName(final Text textName) {
		this.textName = textName;
	}

	public Text getTextPath() {
		return textPath;
	}

	public void setTextPath(final Text textPath) {
		this.textPath = textPath;
	}

	public Text getTxtServerpath() {
		return txtServerpath;
	}

	public void setTxtServerpath(final Text txtServerpath) {
		this.txtServerpath = txtServerpath;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(final boolean checked) {
		this.checked = checked;
	}

	public ImageHyperlink getMghprlnkChecker() {
		return mghprlnkChecker;
	}

	public void setMghprlnkChecker(final ImageHyperlink mghprlnkChecker) {
		this.mghprlnkChecker = mghprlnkChecker;
	}

	public ControlDecoration getCtrlDecServerURL() {
		return ctrlDecServerURL;
	}

	public void setCtrlDecServerURL(final ControlDecoration ctrlDecServerURL) {
		this.ctrlDecServerURL = ctrlDecServerURL;
	}

	public Button getBtnGetBinariesFromFile() {
		return btnGetBinariesFromFile;
	}

	public void setBtnGetBinariesFromFile(final Button btnGetBinariesFromFile) {
		this.btnGetBinariesFromFile = btnGetBinariesFromFile;
	}

	public Button getBtnDowload() {
		return btnDowload;
	}

	public void setBtnDowload(final Button btnDowload) {
		this.btnDowload = btnDowload;
	}

	public FormToolkit getFormToolkit() {
		return formToolkit;
	}

	protected void check() {
		try {
			URL url = new URL(txtServerpath.getText());
			url.openConnection().getContent();
			mghprlnkChecker
					.setText(Messages.AddBinaryConfigWizardPage_mghprlnkChecker_text);
			setErrorMessage(null);
			mghprlnkChecker.setImage(ResourceManager.getPluginImage(
					"org.craftyourmod.mineclipse.ui", "icons/ok.gif"));
			checked = true;

		} catch (MalformedURLException e) {
			mghprlnkChecker.setText("URL is not valid !");
			setErrorMessage("URL is not valid !");
			mghprlnkChecker.setImage(ResourceManager.getPluginImage(
					"org.craftyourmod.mineclipse.ui", "icons/error.gif"));

		} catch (IOException e) {
			mghprlnkChecker.setText("URL is not valid !");
			setErrorMessage("URL is not valid !");
			mghprlnkChecker.setImage(ResourceManager.getPluginImage(
					"org.craftyourmod.mineclipse.ui", "icons/error.gif"));
		}
		validate();

	}

	private void validate() {
		if (!checked && btnDowload.getSelection()) {
			ctrlDecServerURL
					.setDescriptionText(Messages.AddBinaryConfigWizardPage_ctrlDecServerURL_notChecked);
			ctrlDecServerURL.setImage(SWTResourceManager.getImage(
					AddBinaryPage.class,
					"/org/eclipse/jface/fieldassist/images/info_ovr.gif"));

			setPageComplete(false);
			return;

		}
		if (textPath.getText().isEmpty()
				&& btnGetBinariesFromFile.getSelection()) {
			setErrorMessage(Messages.AddBinaryConfigWizardPage_error_nameEmpty);

			setPageComplete(false);
			return;

		}
		if (textName.getText().isEmpty()) {
			setErrorMessage(Messages.AddBinaryConfigWizardPage_error_nameEmpty);
			setPageComplete(false);
			return;
		}

		setPageComplete(true);
	}

	protected void activate(final Composite c, final boolean b) {
		for (Control child : c.getChildren())
			child.setEnabled(b);

	}
}
