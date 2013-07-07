package org.craftyourmod.mineclipse.ui.pages;

import org.craftyourmod.mineclipse.core.filemanager.FileManager;
import org.craftyourmod.mineclipse.core.filemanager.SourceFile;
import org.craftyourmod.mineclipse.ui.Activator;
import org.craftyourmod.mineclipse.ui.Messages;
import org.craftyourmod.mineclipse.ui.providers.FileManagerLabelProvider;
import org.craftyourmod.mineclipse.ui.wizards.NewMinecraftProjectWorkbenchWizard;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IMessageProvider;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

public class NewMineClipseProjectPageOne extends WizardPage {

	private Text txtName;
	private Text txtInput;
	private final NewMinecraftProjectWorkbenchWizard fWizard;

	/**
	 * Create the wizard.
	 * 
	 * @param wizard
	 */
	public NewMineClipseProjectPageOne(final NewMinecraftProjectWorkbenchWizard wizard) {
		super("pageOne"); //$NON-NLS-1$
		setTitle(Messages.NewMineClipseProjectPageOne_Title);
		setDescription(Messages.NewMineClipseProjectPageOne_Description);
		this.fWizard = wizard;
	}

	public Text getTxtInput() {
		return txtInput;
	}

	public Text getTxtName() {
		return txtName;
	}

	@Override
	public void createControl(final Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		container.setLayout(new GridLayout(3, false));

		Label lblProjectName = new Label(container, SWT.NONE);
		lblProjectName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProjectName.setText(Messages.NewMineClipseProjectPageOne_ProjectName);

		txtName = new Text(container, SWT.BORDER);
		txtName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				validateFields();
			}
		});
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));

		Label lblInputSource = new Label(container, SWT.NONE);
		lblInputSource.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblInputSource.setText(Messages.NewMineClipseProjectPageOne_Input);

		txtInput = new Text(container, SWT.BORDER);
		txtInput.setEditable(false);
		txtInput.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnSelect = new Button(container, SWT.NONE);
		btnSelect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), new FileManagerLabelProvider());
				dialog.setElements(FileManager.INSTANCE.getSrcs().toArray());
				dialog.setBlockOnOpen(true);
				dialog.setMessage(Messages.AddSourcePage_binariesListSelctionDialog_message);
				dialog.open();
				txtInput.setText(((SourceFile) dialog.getResult()[0]).getName());
				validateFields();
			}
		});
		btnSelect.setText(Messages.NewMineClipseProjectPageOne_Select);

	}

	public void validateFields() {
		if (txtName.getText().isEmpty())
			fWizard.setStatus(new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.NewMineClipseProjectPageOne_Error_EmptyName));
		else
			if (ResourcesPlugin.getWorkspace().getRoot().getProject(txtName.getText()).exists())
				fWizard.setStatus(new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.NewMineClipseProjectPageOne_Error_DuplicateName));
			else
				if (txtInput.getText().isEmpty())
					fWizard.setStatus(new Status(Status.ERROR, Activator.PLUGIN_ID, Messages.NewMineClipseProjectPageOne_Error_NoImput));
				else fWizard.setStatus(new Status(Status.OK, Activator.PLUGIN_ID, null));
		updateMessage();

	}

	public void updateMessage() {
		if (!fWizard.getStatus().isOK()) {

			setMessage(fWizard.getStatus().getMessage(), IMessageProvider.ERROR);
			setPageComplete(false);
		} else {
			setMessage(null);
			setPageComplete(true);
		}

	}

}
