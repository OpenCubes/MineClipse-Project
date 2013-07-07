package org.craftyourmod.mineclipse.ui.pages;

import java.io.File;

import org.craftyourmod.mineclipse.ui.Messages;
import org.craftyourmod.mineclipse.ui.dialogs.MinecraftFileSelectionDialog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class OverrideFilePage extends WizardPage {
	private Text text;
	private final IStructuredSelection selection;
	private IStatus status;
	private File selectedFile;

	/**
	 * Create the wizard.
	 * 
	 * @param selection2
	 */
	public OverrideFilePage(final IStructuredSelection selection) {
		super("wizardPage"); //$NON-NLS-1$
		setTitle(Messages.OverrideFilePage_Title);
		setDescription(Messages.OverrideFilePage_Desc);
		this.selection = selection;
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

		Label lblFile = new Label(container, SWT.NONE);
		lblFile.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false,
				1, 1));
		lblFile.setText(Messages.OverrideFilePage_File);

		text = new Text(container, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Button btnSelect = new Button(container, SWT.NONE);
		btnSelect.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				MinecraftFileSelectionDialog d = new MinecraftFileSelectionDialog(
						getShell(), selection);

				d.create();
				d.open();
				File r = (File) d.getResult()[0];
				text.setText(r.getName());
				setSelectedFile(r);
				validate();
			}
		});
		btnSelect.setText(Messages.OverrideFilePage_Select);
	}

	/**
	 * @return the status
	 */
	public IStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final IStatus status) {
		this.status = status;
		updateMessage();
	}

	private void updateMessage() {

		if (!status.isOK()) {
			int kind = NONE;
			setPageComplete(true);
			switch (status.getSeverity()) {
			case IStatus.ERROR:
				setPageComplete(false);
				kind = ERROR;
				break;
			case IStatus.CANCEL:
				kind = NONE;
				break;
			case IStatus.WARNING:
				kind = WARNING;
			default:
				break;
			}
			setMessage(status.getMessage(), kind);
		} else {
			setErrorMessage(null);
			setPageComplete(true);
		}

	}

	protected void validate() {
		if (text.getText().isEmpty())
			setStatus(new Status(IStatus.ERROR,
					org.craftyourmod.mineclipse.ui.Activator.PLUGIN_ID,
					Messages.OverrideFilePage_Error_NoFile));
		else
			setStatus(Status.OK_STATUS);

	}

	/**
	 * @return the selectedFile
	 */
	public File getSelectedFile() {
		return selectedFile;
	}

	/**
	 * @param selectedFile
	 *            the selectedFile to set
	 */
	public void setSelectedFile(final File selectedFile) {
		this.selectedFile = selectedFile;
	}
}
