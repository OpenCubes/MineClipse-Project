package org.craftyourmod.mineclipse.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.craftyourmod.mineclipse.ui.messages"; //$NON-NLS-1$
	public static String FileManagerView_this_titleToolTip;
	public static String FileManager_Tooltip;
	public static String FileManager_PartName;
	public static String AddElement_Tooltip;
	public static String RemoveElement_Tooltip;
	////////////////////////////////////////////////////////////////////////////
	//
	// Constructor
	//
	////////////////////////////////////////////////////////////////////////////
	private Messages() {
		// do not instantiate
	}
	////////////////////////////////////////////////////////////////////////////
	//
	// Class initialization
	//
	////////////////////////////////////////////////////////////////////////////
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
