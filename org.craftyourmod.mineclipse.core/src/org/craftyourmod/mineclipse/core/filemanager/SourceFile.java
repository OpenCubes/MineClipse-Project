package org.craftyourmod.mineclipse.core.filemanager;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.craftyourmod.mineclipse.core.Executor;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;

public class SourceFile extends BaseFile {

	private SourceFile(final java.io.File input, final String name) {
		super(State.SOURCE);
		setInput(input);
	}

	/**
	 * Create the file
	 * 
	 * @param mcpPath
	 *            the path to MCP files
	 * @param bin
	 *            the binary file
	 * @param monitor
	 * @param target
	 *            where to copy
	 * @param exclude
	 * @param name
	 * @return
	 * @throws InvocationTargetException
	 * @throws InterruptedException
	 */
	public static BaseFile create(final File mcpPath, final BinaryFile bin,
			final IProgressMonitor monitor, final File target,
			final String exclude, final String name)
			throws InvocationTargetException, InterruptedException {

		Assert.isNotNull(mcpPath, "mcpPath is null");
		Assert.isNotNull(bin, "bin is null");

		monitor.subTask("Copying...");
		Executor.INSTANCE.performCopy(bin.getInput(), new File(mcpPath,
				"src/minecraft"), "", monitor);
		Executor.INSTANCE.setDirectory(mcpPath);
		Executor.INSTANCE.setExec(new File(mcpPath, "/decompile.bat"));
		Executor.INSTANCE.run(monitor);
		monitor.subTask("Copying...");
		Executor.INSTANCE.performCopy(new File(mcpPath, "src/minecraft"),
				target, exclude, monitor);
		return new SourceFile(target, name);
	}

	public static SourceFile load(final File input, final String name) {
		return new SourceFile(input, name);

	}
}
