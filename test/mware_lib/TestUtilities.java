package mware_lib;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUtilities {

	@Test
	public void createProxyWithManager() {
		assertTrue(mware_lib.Utilities.createProxy("name",
				"branch_access.Manager", "localhost", 12345) instanceof branch_access.Manager);
	}

	@Test(expected = RuntimeException.class)
	public void createProxyWithAccount() {
		mware_lib.Utilities.createProxy("name", "cash_access.Account",
				"localhost", 12345);
	}

	@Test
	public void createProxyWithSomethingElse() {
		assertNull(mware_lib.Utilities.createProxy("name", "java.lang.String",
				"localhost", 12345));
	}

	@Test
	public void testGetTypeForObject() {
		assertEquals("branch_access.Manager",
				Utilities.getTypeForObject(new branch_access.ManagerProxy()));
	}

}
