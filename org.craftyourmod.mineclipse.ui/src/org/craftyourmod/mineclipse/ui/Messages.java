package org.craftyourmod.mineclipse.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.craftyourmod.mineclipse.ui.messages"; //$NON-NLS-1$
	public static String AddSourcePage_errors_emptyInput;
	public static String AddBinaryConfigWizardPage_error_nameEmpty;
	public static String AddBinaryWizard_State_BinCreation;
	public static String AddBinaryWizard_State_GettingJar;
	public static String AddBinaryWizard_Title;
	public static String AddSourcePage_binariesListSelctionDialog_message;
	public static String FileManager_Tooltip;
	public static String FileManager_PartName;
	public static String AddElement_Tooltip;
	public static String AddElementAction_Text;
	public static String RemoveElement_Tooltip;
	public static String RemoveElementAction_Text;
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
	public static String AddBinaryConfigWizardPage_ctrlDecServerURL_notChecked;
	public static String AddBinaryConfigWizardPage_textPath_text;
	public static String AddBinaryPage_Error_InvalidURL;
	public static String FileManagerView_remElementAction_text;
	public static String FileManagerView_remElementAction_toolTipText;
	public static String AddSourcePage_lblName_text;
	public static String AddSourcePage_text_text;
	public static String AddSourcePage_lblInputBinary_text;
	public static String AddSourcePage_btnSelect_text;
	public static String AddSourcePage_label_text;
	public static String AddSourcePage_label_1_text;
	public static String FileManagerView_action_text;
	public static String AddSourcePage_btnJustCopy_text;
	public static String AddSourcePage_xpndblcmpstExperimentalAndDebug_text;
	public static String AddSourcePage_btnJustCopy_text_1;
	public static String AddSourcePage_btnDefault_text;
	public static String AddSourcePage_lblJustCopy_text;
	public static String AddSourcePage_btnJustCopy_text_2;
	public static String AddSourcePage_btnUseForge_text;
	public static String AddSourcePage_btnUseDefaultMcp_text;
	public static String AddSourcePage_Description;
	public static String FileManagerView_trclmnName_text;
	public static String FileManagerView_trclmnId_text;
	public static String FileManagerView_trclmnName_text_1;
	public static String AddSourcePage_lblNewLabel_text;
	public static String AddSourcePage_lblNewLabel_1_text;
	public static String AddSourcePage_lblSeparatedWithCommas_text;
	public static String AddSourcePage_Title;
	public static String AddSourcePage_txtYesyes_text;
	public static String AddSourcePage_Warning_Debug;;

	public static String NewMineClipseProjectPageOne_Description;
	public static String NewMineClipseProjectPageOne_Error_DuplicateName;
	public static String NewMineClipseProjectPageOne_Error_EmptyName;
	public static String NewMineClipseProjectPageOne_Error_NoImput;
	public static String NewMineClipseProjectPageOne_Input;
	public static String NewMineClipseProjectPageOne_ProjectName;
	public static String NewMineClipseProjectPageOne_Select;
	public static String NewMineClipseProjectPageOne_Title;

	public static String OverrideFilePage_Desc;
	public static String OverrideFilePage_Error_NoFile;
	public static String OverrideFilePage_File;
	public static String OverrideFilePage_Select;
	public static String OverrideFilePage_Title;

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
