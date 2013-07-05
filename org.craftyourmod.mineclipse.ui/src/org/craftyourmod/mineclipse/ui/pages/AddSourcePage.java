package org.craftyourmod.mineclipse.ui.pages;

import org.craftyourmod.mineclipse.core.filemanager.BinaryFile;
import org.craftyourmod.mineclipse.core.filemanager.FileManager;
import org.craftyourmod.mineclipse.ui.Messages;
import org.craftyourmod.mineclipse.ui.providers.FileManagerLabelProvider;
import org.eclipse.core.runtime.Status;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddSourcePage extends WizardPage {
	private Text txtName;
	private Text txtInputBinary;
	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Button btnJustCopy;

	public FormToolkit getFormToolkit() {
		return formToolkit;
	}

	public Button getBtnUseForge() {
		return btnUseForge;
	}

	public Button getBtnUseDefaultMcp() {
		return btnUseDefaultMcp;
	}

	private Button btnUseForge;
	private Button btnUseDefaultMcp;

	/**
	 * Create the wizard.
	 */
	public AddSourcePage() {
		super("wizardPage");
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(
				"org.craftyourmod.mineclipse.ui", "icons/baner_addsrc01.gif"));
		setTitle("Add Source");
		setDescription("Add a new source from existing binary");
		setPageComplete(false);
	}

	public Text getTxtName() {
		return txtName;
	}

	public Text getTxtInputBinary() {
		return txtInputBinary;
	}

	public Button getBtnJustCopy() {
		return btnJustCopy;
	}

	private void validate() {
		if (txtName.getText().isEmpty()) {

			setPageComplete(false);
			setErrorMessage(Messages.AddSourcePage_errors_emptyName);
			return;
		}
		if (txtInputBinary.getText().isEmpty()) {
			setPageComplete(false);
			setErrorMessage(Messages.AddSourcePage_errors_emptyInput);
			return;
		}
		setErrorMessage(null);
		setPageComplete(true);

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
		container.setLayout(new GridLayout(3, false));

		Label lblName = new Label(container, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblName.setText(Messages.AddSourcePage_lblName_text);

		txtName = new Text(container, SWT.BORDER);
		txtName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				validate();
			}
		});
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				2, 1));

		Label lblInputBinary = new Label(container, SWT.NONE);
		lblInputBinary.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblInputBinary.setText(Messages.AddSourcePage_lblInputBinary_text);

		txtInputBinary = new Text(container, SWT.BORDER);
		txtInputBinary.setEditable(false);
		txtInputBinary.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Button btnSelect = new Button(container, SWT.NONE);
		btnSelect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				ElementListSelectionDialog dialog = new ElementListSelectionDialog(
						getShell(), new FileManagerLabelProvider());
				dialog.setElements(FileManager.INSTANCE.getBins().toArray());
				dialog.setBlockOnOpen(true);
				dialog.setMessage(Messages.AddSourcePage_binariesListSelctionDialog_message);
				dialog.open();
				txtInputBinary.setText(((BinaryFile) dialog.getResult()[0])
						.getName());
				validate();
			}
		});
		btnSelect.setText(Messages.AddSourcePage_btnSelect_text);
		new Label(container, SWT.NONE);

		ExpandableComposite xpndblcmpstExperimentalAndDebug = formToolkit
				.createExpandableComposite(container,
						ExpandableComposite.TWISTIE);
		xpndblcmpstExperimentalAndDebug.setLayoutData(new GridData(SWT.LEFT,
				SWT.FILL, false, true, 2, 1));
		xpndblcmpstExperimentalAndDebug.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		xpndblcmpstExperimentalAndDebug
				.setText(Messages.AddSourcePage_xpndblcmpstExperimentalAndDebug_text);

		Composite composite = new Composite(xpndblcmpstExperimentalAndDebug,
				SWT.NONE);
		xpndblcmpstExperimentalAndDebug.setClient(composite);
		composite.setLayout(new GridLayout(3, false));

		final Button btnDefault = new Button(composite, SWT.CHECK);

		btnDefault.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 3, 1));
		btnDefault.setText(Messages.AddSourcePage_btnDefault_text);
		new Label(composite, SWT.NONE);

		btnJustCopy = new Button(composite, SWT.CHECK);
		btnJustCopy.setEnabled(false);
		btnJustCopy.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 2, 1));
		btnJustCopy.setText(Messages.AddSourcePage_btnJustCopy_text_2);
		new Label(container, SWT.NONE);

		btnUseForge = new Button(container, SWT.RADIO);
		btnUseForge.setSelection(true);
		btnUseForge.setText(Messages.AddSourcePage_btnUseForge_text);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		btnUseDefaultMcp = new Button(container, SWT.RADIO);
		btnUseDefaultMcp.setText(Messages.AddSourcePage_btnUseDefaultMcp_text);
		new Label(container, SWT.NONE);
		btnDefault.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {

				btnJustCopy.setEnabled(btnDefault.getSelection());
				if (!btnDefault.getSelection()) {
					btnJustCopy.setSelection(false);
					setMessage(null);
				} else
					setMessage(
							"You are using experimental or debug features: please be careful",
							Status.WARNING);
				validate();
			}
		});
	}
}
