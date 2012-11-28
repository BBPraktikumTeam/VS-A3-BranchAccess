package mware_lib;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import branch_access.ManagerDummy;
import branch_access.ManagerProxy;

public class TestBranchAccess {

	private static final String NS_HOST = "localhost";
	private static final int NS_PORT = 6666;

	private static ObjectBroker broker;
	private static NameService ns;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		broker = ObjectBroker.getBroker(NS_HOST, NS_PORT);
		ns = broker.getNameService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		assertNull(ns.resolve("ba_test_man_01"));
		assertEquals(0, CommunicatorBindings.numberOfBindings());
		ManagerDummy md = new ManagerDummy();
		assertEquals(0, SkeletonBindings.numberOfSkeletonBindings());
		ns.rebind(md,"ba_test_man_01");
		assertEquals(1, SkeletonBindings.numberOfSkeletonBindings());
		ManagerProxy mp = (ManagerProxy)ns.resolve("ba_test_man_01");
		assertNotNull(mp);
		assertEquals(6.66, mp.getBalance("testAccount"), 0.00001);
		assertEquals("FAKE_ACCOUNT", mp.createAccount("USER"));
	}

}
