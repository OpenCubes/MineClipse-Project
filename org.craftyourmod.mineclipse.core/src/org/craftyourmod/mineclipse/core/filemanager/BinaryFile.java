package org.craftyourmod.mineclipse.core.filemanager;

import org.eclipse.core.runtime.Assert;

public class BinaryFile extends BaseFile {

	private BinaryFile(final java.io.File input, final String name) {
		super(State.BINARY);
		setInput(input);
	}

	public static BinaryFile create(final java.io.File input, final String name) {
		Assert.isNotNull(input, "Input is null");

		return new BinaryFile(input, name);
	}
}
