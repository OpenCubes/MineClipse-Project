package org.craftyourmod.mineclipse.core;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;

public class ConfigManager {
	private static ConfigManager instance;
	private final IEclipsePreferences preferences;

	private ConfigManager() {
		this.preferences = InstanceScope.INSTANCE
				.getNode(Activator.PLUGIN_PREFERENCE_SCOPE);
	}

	public void putPreference(final String key, final String value) {
		preferences.put(key, value);
	}

	public String getPreference(final String key, final String defaultValue) {
		return preferences.get(key, defaultValue);
	}

	/**
	 * @return the instance
	 */
	public static ConfigManager getInstance() {
		if (instance == null)
			instance = new ConfigManager();
		return instance;
	}

}
