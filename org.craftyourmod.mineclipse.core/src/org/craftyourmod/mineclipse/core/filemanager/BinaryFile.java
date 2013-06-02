package org.craftyourmod.mineclipse.core.filemanager;

import org.eclipse.core.runtime.Assert;

public class BinaryFile extends BaseFile {

	private BinaryFile(final java.io.File input, final String name) {
		super(State.BINARY);
		setInput(input);
		setName(name);
	}

	public static BinaryFile create(final java.io.File input, final String name) {
		Assert.isNotNull(input, "Input is null");

		return new BinaryFile(input, name);
	}

	public static BinaryFile load(final java.io.File input, final String name) {
		Assert.isNotNull(input, "Input is null");

		return new BinaryFile(input, name);
	}

	@Override
	public String toString() {
		return "BinaryFile [getState()=" + getState() + ", getInput()="
				+ getInput() + ", getName()=" + getName() + ", getSupport()="
				+ getSupport() + ", hashCode()=" + hashCode() + ", getClass()="
				+ getClass() + ", toString()=" + super.toString() + "]";
	}
}
