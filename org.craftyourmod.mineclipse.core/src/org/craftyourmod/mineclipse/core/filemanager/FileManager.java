package org.craftyourmod.mineclipse.core.filemanager;

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
import org.eclipse.jface.preference.IPreferenceStore;

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
		support.firePropertyChange("FileManager.BinAdded", old, bins);

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
		support.firePropertyChange("FileManager.BinRemoved", old, bins);

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
		support.firePropertyChange("FileManager.SrcAdded", old, srcs);

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
		support.firePropertyChange("FileManager.SrcRemoved", old, srcs);

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
		int id = (file.getName() + "$" + file.getInput().getAbsolutePath())
				.hashCode();
		/*
		 * Activator .getDefault() .getLog() .log(new Status(Status.INFO,
		 * Activator.PLUGIN_ID, "Registrering file id #" + id));
		 */
		idsMap.put(new String().valueOf(id), file);

	}

	private void unregisterFile(final String id) {
		Assert.isNotNull(id);

		Activator
				.getDefault()
				.getLog()
				.log(new Status(Status.INFO, Activator.PLUGIN_ID,
						"Unregistrering file id #" + id));
		idsMap.remove(id);

	}

	public void load(final IPreferenceStore preferenceStore) {
		Assert.isNotNull(preferenceStore);
		for (String s : preferenceStore.getString("FileManager.IDs").split(";")) {
			String state = preferenceStore.getString("FileManager.IDs." + s
					+ ".state");
			String input = preferenceStore.getString("FileManager.IDs." + s
					+ ".input");
			String name = preferenceStore.getString("FileManager.IDs." + s
					+ ".name");
			// Assert.isTrue(new File(input).exists());
			if (state.equals("BIN"))
				addBin(BinaryFile.load(new File(input), name));
			else
				addSrc(SourceFile.load(new File(input), name));
		}
	}

	public void store(final IPreferenceStore preferenceStore) {
		Assert.isNotNull(preferenceStore);
		String ids = "";
		for (String id : idsMap.keySet())
			ids += id + ";";

		preferenceStore.setValue("FileManager.IDs", ids);
		for (String key : idsMap.keySet()) {
			System.out.println(key);
			System.out.println(idsMap.get(key).getName());
			preferenceStore.setValue("FileManager.IDs." + key + ".name", idsMap
					.get(key).getName());
			preferenceStore.setValue("FileManager.IDs." + key + ".input",
					idsMap.get(key).getInput().getAbsolutePath());
			preferenceStore.setValue("FileManager.IDs." + key + ".state",
					idsMap.get(key).getState().toString());
		}

	}

	@Override
	public String toString() {
		return "FileManager [bins=" + bins + ", srcs=" + srcs + ", idsMap="
				+ idsMap + ", support=" + support + "]";
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
		} else if (!idsMap.equals(other.idsMap))
			return false;
		return true;
	}
}
