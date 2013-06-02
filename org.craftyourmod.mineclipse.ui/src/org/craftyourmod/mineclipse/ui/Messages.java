package org.craftyourmod.mineclipse.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.craftyourmod.mineclipse.ui.messages"; //$NON-NLS-1$
	public static final String AddBinaryConfigWizardPage_error_nameEmpty = null;
	public static final String AddBinaryWizard_Title = null;
	public static String FileManagerView_this_titleToolTip;
	public static String FileManager_Tooltip;
	public static String FileManager_PartName;
	public static String AddElement_Tooltip;
	public static String RemoveElement_Tooltip;
	public static String AddBinaryWizardConfigPage_Title;
	public static String AddBinaryWizardConfigPage_Description;
	public static String AddBinaryConfigWizardPage_this_message;
	public static String AddBinaryConfigWizardPage_lblName_text;
	public static String AddBinaryConfigWizardPage_text_text;
	public static String AddBinaryWizardConfigPage_Name_Msg;
	public static String AddBinaryConfigWizardPage_btnGetBinariesFrom_text;
	public static String AddBinaryConfigWizardPage_lblPath_text;
	public static String AddBinaryConfigWizardPage_text_1_text;
	public static String AddBinaryConfigWizardPage_button_text;
	public static String AddBinaryConfigWizardPage_btnDowload_text;
	public static String AddBinaryConfigWizardPage_lblServerPath_text;
	public static String AddBinaryConfigWizardPage_txtServerpath_text;
	public static String AddBinaryConfigWizardPage_btnCheck_text;
	public static String AddBinaryConfigWizardPage_lblMinecraftversion_text;
	public static String AddBinaryConfigWizardPage_lblMinecraftversion_text_1;
	public static String AddBinaryConfigWizardPage_lblNewLabel_text;
	public static String AddBinaryConfigWizardPage_lblMinecraftversion_text_2;
	public static String AddBinaryConfigWizardPage_mghprlnkChecker_text;
	public static String AddBinaryConfigWizardPage_ctrlDecServerURL_notChecked;;

	// //////////////////////////////////////////////////////////////////////////
	//
	// Constructor
	//
	// //////////////////////////////////////////////////////////////////////////
	private Messages() {
		// do not instantiate
	}

	// //////////////////////////////////////////////////////////////////////////
	//
	// Class initialization
	//
	// //////////////////////////////////////////////////////////////////////////
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
