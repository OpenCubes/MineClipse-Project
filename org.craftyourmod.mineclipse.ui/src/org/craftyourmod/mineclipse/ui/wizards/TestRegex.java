package org.craftyourmod.mineclipse.ui.wizards;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestRegex {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		System.out.println("file.class".matches("[a-zA-Z0-9\\s]+(.(png|gif|txt|lang))?"));
	}

}
