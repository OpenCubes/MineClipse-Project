package org.craftyourmod.mineclipse.core.filemanager;

import java.io.File;

import org.craftyourmod.mineclipse.core.MineclipseCore;
import org.eclipse.core.runtime.IProgressMonitor;

public class SourceFile extends BaseFile {

	private SourceFile(final java.io.File input, final String name) {
		super(State.SOURCE);
		setName(name);
		setInput(input);
	}

	public static BaseFile create(final File mcpPath, final BinaryFile bin, final IProgressMonitor monitor, final File target, final String name) {
		MineclipseCore.INSTANCE.performCopy(new File(mcpPath, "/src/minecraft"), new File(target, "/src"), "", monitor);

		MineclipseCore.INSTANCE.performCopy(new File(mcpPath, "/temp/bin/minecraft"), new File(target, "/bin"), "", monitor);
		return new SourceFile(target, name);

	}

	public static SourceFile load(final File input, final String name) {
		return new SourceFile(input, name);

	}
}
