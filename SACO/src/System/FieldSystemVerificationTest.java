package System;

import junit.framework.TestCase;

public class FieldSystemVerificationTest extends TestCase {

	private String email;
	private FieldSystemVerification system;

	public void setUp() {
		system = new FieldSystemVerification();
	}

	public void testValidatEmail() {
		String name = "";
		assertFalse(system.validateName(name));
		name = null;
		assertFalse(system.validateName(name));
		email = "ramon@gmail.com";
		assertTrue(system.validateEmail(email));
		email = "";
		assertFalse(system.validateEmail(email));
		email = null;
		assertFalse(system.validateEmail(email));
	}

}
