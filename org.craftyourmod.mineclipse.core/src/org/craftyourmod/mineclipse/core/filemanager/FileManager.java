package org.craftyourmod.mineclipse.core.filemanager;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

public class FileManager {
	List<BinaryFile> bins;
	List<SourceFile> srcs;
	// TODO add APIs
	/**
	 * The instance
	 */
	public static FileManager INSTANCE = new FileManager();

	public FileManager() {
		bins = new ArrayList<BinaryFile>();
		srcs = new ArrayList<SourceFile>();
	}

	public List<BinaryFile> getBins() {
		return bins;
	}

	public void setBins(final List<BinaryFile> bins) {
		this.bins = bins;
	}

	public List<SourceFile> getSrcs() {
		return srcs;
	}

	public void setSrcs(final List<SourceFile> srcs) {
		this.srcs = srcs;
	}

	public void store(final IPreferenceStore preferenceStore) {
		// TODO Auto-generated method stub

	}

}
