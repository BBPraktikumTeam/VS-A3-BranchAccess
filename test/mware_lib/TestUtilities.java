package mware_lib;

import static org.junit.Assert.*;

import org.junit.Test;

import branch_access.Manager;
import branch_access.ManagerDummy;

public class TestUtilities {

	@Test
	public void createProxyManager() {
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

	@Test
	public void testCreateSkeleton() {
		assertNull(SkeletonBindings.getSkeleton("ba_TestCreateSkeleton_01"));
		Manager m = new ManagerDummy();
		Skeleton skel = Utilities.createSkeleton("ba_TestCreateSkeleton_01", m);
		SkeletonBindings.addSkeleton(skel);
		assertSame(skel,
				SkeletonBindings.getSkeleton("ba_TestCreateSkeleton_01"));
	}

}
