package org.craftyourmod.mineclipse.core.filemanager;

import java.beans.PropertyChangeSupport;

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
	private final PropertyChangeSupport support;

	protected BaseFile(final State state) {
		this.state = state;
		support = new PropertyChangeSupport(this);
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
		getSupport().firePropertyChange("BaseFile.input", old, input);

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
		getSupport().firePropertyChange("BaseFile.name", old, name);
	}

	/**
	 * @return the support
	 */
	public PropertyChangeSupport getSupport() {
		return support;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((input == null) ? 0 : input.hashCode());
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		result = (prime * result) + ((state == null) ? 0 : state.hashCode());
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
		BaseFile other = (BaseFile) obj;
		if (input == null) {
			if (other.input != null)
				return false;
		} else if (!input.equals(other.input))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	public static enum State {
		/**
		 * This stage correspond to binarie files, such as jar file
		 */
		BINARY("BIN"),
		/**
		 * When it is decompiled using MCP like java file
		 */
		SOURCE("SRC");
		private String name;

		State(final String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

	}
}
