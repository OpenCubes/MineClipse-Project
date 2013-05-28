package org.craftyourmod.mineclipse.core.filemanager;

import java.util.ArrayList;
import java.util.List;

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

}
