package org.craftyourmod.mineclipse.core.filemanager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.craftyourmod.mineclipse.core.Activator;
import org.craftyourmod.mineclipse.core.Util;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.osgi.service.prefs.BackingStoreException;

public class FileManager {
	List<BinaryFile> bins;
	List<SourceFile> srcs;
	Map<String, BaseFile> idsMap;
	PropertyChangeSupport support;
	// TODO add APIs
	/**
	 * The instance
	 */
	public static FileManager INSTANCE = new FileManager();

	protected FileManager() {
		bins = new ArrayList<BinaryFile>();
		srcs = new ArrayList<SourceFile>();
		idsMap = new HashMap<String, BaseFile>();
		support = new PropertyChangeSupport(this);
		load();
		support.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(final PropertyChangeEvent evt) {
				store();

			}
		});
	}

	/**
	 * 
	 * @return an unmodifiable list
	 */
	public List<BinaryFile> getBins() {
		return Collections.unmodifiableList(bins);
	}

	/**
	 * Add a binary file
	 * 
	 * @param bin
	 *            the {@link BinaryFile}
	 */
	public void addBin(final BinaryFile bin) {

		Assert.isNotNull(bin);
		Object old = this.bins;
		bins.add(bin);
		registerFile(bin);
		System.out.println(support.getPropertyChangeListeners());
		// Fake bcs if not, no call
		support.firePropertyChange("FileManager.BinAdded", "FAKE", bins);
	}

	/**
	 * Remove a binary file
	 * 
	 * @param bin
	 *            the {@link BinaryFile}
	 */
	public void removeBin(final BinaryFile bin) {
		Assert.isNotNull(bin);
		Object old = this.bins;
		bins.remove(bin);
		unregisterFile((String) Util.getKeyFromObject(idsMap, bin));
		support.firePropertyChange("FileManager.BinRemoved", "FAKE", bins);
	}

	/**
	 * Add a source file
	 * 
	 * @param bin
	 *            the {@link BinaryFile}
	 */
	public void addSrc(final SourceFile src) {
		Assert.isNotNull(src);
		Object old = this.srcs;
		srcs.add(src);
		registerFile(src);
		support.firePropertyChange("FileManager.SrcAdded", "FAKE", srcs);

	}

	/**
	 * Remove a source file
	 * 
	 * @param bin
	 *            the {@link BinaryFile}
	 */
	public void removeSrc(final SourceFile src) {
		Assert.isNotNull(src);
		Object old = this.srcs;
		srcs.remove(src);
		unregisterFile((String) Util.getKeyFromObject(idsMap, src));
		support.firePropertyChange("FileManager.SrcRemoved", "FAKE", srcs);

	}

	/**
	 * 
	 * @return an unmod. list
	 */
	public List<SourceFile> getSrcs() {
		return Collections.unmodifiableList(srcs);
	}

	public PropertyChangeSupport getSupport() {
		return support;
	}

	private void registerFile(final BaseFile file) {
		Assert.isNotNull(file);
		String id = file.getId();

		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.PLUGIN_ID, "Registering file id #" + id));

		idsMap.put(String.valueOf(id), file);

	}

	private void unregisterFile(final String id) {
		Assert.isNotNull(id);

		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.PLUGIN_ID, "Unregistrering file id #" + id));
		idsMap.remove(id);

	}

	public void load() {
		// saves plugin preferences at the workspace level
		IEclipsePreferences mineclipsePrefs =
		// Platform.getPreferencesService().getRootNode().node(Plugin.PLUGIN_PREFEERENCES_SCOPE).node(MY_PLUGIN_ID);
		InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID); // does
																// all
																// the
		// above
		// behind the scenes
		IEclipsePreferences p = (IEclipsePreferences) mineclipsePrefs.node("FileManager.Bins");
		for (String s : p.get("FileManager.IDs", "").split(";")) {
			if (s.isEmpty())
				continue;
			String state = p.get("FileManager.IDs." + s + ".state", "BIN");
			String input = p.get("FileManager.IDs." + s + ".input", "ERROR");
			String name = p.get("FileManager.IDs." + s + ".name", "UNKNOW");
			// Assert.isTrue(new File(input).exists());
			if (state.equals("BIN"))
				addBin(BinaryFile.load(new File(input), name));
			else addSrc(SourceFile.load(new File(input), name));
		}
	}

	public void store() {
		// saves plugin preferences at the workspace level
		IEclipsePreferences mineclipsePrefs =
		// Platform.getPreferencesService().getRootNode().node(Plugin.PLUGIN_PREFEERENCES_SCOPE).node(MY_PLUGIN_ID);
		InstanceScope.INSTANCE.getNode(Activator.PLUGIN_ID); // does
																// all
																// the
		// above
		// behind the scenes
		IEclipsePreferences p = (IEclipsePreferences) mineclipsePrefs.node("FileManager.Bins");

		String ids = "";
		for (String id : idsMap.keySet())
			ids += id + ";";
		p.put("FileManager.IDs", ids);
		for (String key : idsMap.keySet()) {
			p.put("FileManager.IDs." + key + ".name", idsMap.get(key).getName());
			p.put("FileManager.IDs." + key + ".input", idsMap.get(key).getInput().getAbsolutePath());
			p.put("FileManager.IDs." + key + ".state", idsMap.get(key).getState().toString());
		}
		p = (IEclipsePreferences) mineclipsePrefs.node("FileManager.Srcs");

		ids = "";
		for (String id : idsMap.keySet())
			ids += id + ";";
		p.put("FileManager.IDs", ids);
		for (String key : idsMap.keySet()) {
			p.put("FileManager.IDs." + key + ".name", idsMap.get(key).getName());
			p.put("FileManager.IDs." + key + ".input", idsMap.get(key).getInput().getAbsolutePath());
			p.put("FileManager.IDs." + key + ".state", idsMap.get(key).getState().toString());
		}
		// prefs are automatically flushed during a plugin's "super.stop()".
		try {
			p.flush();
		} catch (BackingStoreException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.PLUGIN_ID, "Error while storing preferences", e));
		}

	}

	@Override
	public String toString() {
		return "FileManager [bins=" + bins + ", srcs=" + srcs + ", idsMap=" + idsMap + ", support=" + support + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((idsMap == null) ? 0 : idsMap.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileManager other = (FileManager) obj;
		if (idsMap == null) {
			if (other.idsMap != null)
				return false;
		} else
			if (!idsMap.equals(other.idsMap))
				return false;
		return true;
	}
}
