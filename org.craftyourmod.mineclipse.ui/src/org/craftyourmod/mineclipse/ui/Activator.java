package org.craftyourmod.mineclipse.ui;

import java.io.File;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.craftyourmod.mineclipse.ui"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext
	 * )
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Log as an error
	 * 
	 * @param message
	 */
	public static IStatus error(final String message) {
		IStatus status = new Status(Status.ERROR, PLUGIN_ID, message);
		getDefault().getLog().log(status);
		return status;

	}

	/**
	 * Log as an error with {@link Throwable}
	 * 
	 * @param message
	 * @param t
	 * @return
	 */
	public static IStatus error(final String message, final Throwable t) {
		IStatus status = new Status(Status.ERROR, PLUGIN_ID, message, t);
		getDefault().getLog().log(status);
		return status;

	}

	/**
	 * Log as an warning
	 * 
	 * @param message
	 * @return
	 */
	public static IStatus warn(final String message) {
		IStatus status = new Status(Status.WARNING, PLUGIN_ID, message);
		getDefault().getLog().log(status);
		return status;

	}

	/**
	 * Log as an warning with {@link Throwable}
	 * 
	 * @param message
	 * @param t
	 * @return
	 */
	public static IStatus warn(final String message, final Throwable t) {
		IStatus status = new Status(Status.WARNING, PLUGIN_ID, message);
		getDefault().getLog().log(status);
		return status;

	}

	/**
	 * Log as an info
	 * 
	 * @param message
	 * @return
	 */
	public static IStatus info(final String message) {
		IStatus status = new Status(Status.INFO, PLUGIN_ID, message);
		getDefault().getLog().log(status);
		return status;

	}

	/**
	 * Log as an info with {@link Throwable}
	 * 
	 * @param message
	 * @param t
	 * @return
	 */
	public static IStatus info(final String message, final Throwable t) {
		IStatus status = new Status(Status.INFO, PLUGIN_ID, message);
		getDefault().getLog().log(status);
		return status;

	}

	public static File getWorkingDirectory() {
		return new File(System.getProperty("user.home"), "/.mineclipse");
	}

}
