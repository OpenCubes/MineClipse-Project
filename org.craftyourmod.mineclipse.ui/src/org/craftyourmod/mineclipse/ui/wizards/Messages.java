package org.craftyourmod.mineclipse.ui.wizards;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.craftyourmod.mineclipse.ui.wizards.messages"; //$NON-NLS-1$
	public static String AddSourceWizard_JobError;
	public static String AddSourceWizard_JobName;
	public static String AddSourceWizard_Task_CleaningDir;
	public static String AddSourceWizard_Task_Copy;
	public static String AddSourceWizard_Task_Download;
	public static String AddSourceWizard_Task_SrcCreation;
	public static String AddSourceWizard_Task_VersionCheck;
	public static String AddSourceWizard_Title;
	public static String NewMinecraftProjectWorkbenchWizard_Error_DuplicateProject;
	public static String NewMinecraftProjectWorkbenchWizard_Error_SrcMissing;
	public static String NewMinecraftProjectWorkbenchWizard_RunError;
	public static String NewMinecraftProjectWorkbenchWizard_Task_ProjectCreation;
	public static String OverwriteWizard_RunError;
	public static String OverwriteWizard_Title;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
