package org.craftyourmod.mineclipse.core.filemanager;

import org.craftyourmod.mineclipse.core.Activator;

public abstract class BaseFile {
	/**
	 * The state
	 * 
	 * @see State
	 */
	private final State state;
	/**
	 * The file in system
	 */
	private java.io.File input;
	private String name;

	protected BaseFile(final State state) {
		this.state = state;
	}

	/**
	 * 
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @return the inputs
	 */
	public java.io.File getInput() {
		return input;
	}

	/**
	 * set the inputs
	 * 
	 * @param inputs
	 */
	protected void setInput(final java.io.File input) {
		java.io.File old = this.input;
		this.input = input;
		Activator.getSupport().firePropertyChange("BaseFile.input", old, input);

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		String old = this.name;
		this.name = name;
		Activator.getSupport().firePropertyChange("BaseFile.name", old, name);
	}

	public static enum State {
		/**
		 * This stage correspond to binarie files, such as jar file
		 */
		BINARY,
		/**
		 * When it is decompiled using MCP like java file
		 */
		SOURCE;

	}
}
