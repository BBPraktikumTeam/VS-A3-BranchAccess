package mware_lib;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.Socket;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import branch_access.Manager;
import branch_access.ManagerDummy;

public class TestNameServiceStub {

	private static final String NS_HOST = "localhost";
	private static final int NS_PORT = 6666;

	private static NameServiceStub nss = null;
	private static Socket socket = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			socket = new Socket(NS_HOST, NS_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		nss = new NameServiceStub(socket);
	}

	@Test
	public void nameNotFound() {
		assertNull(nss.resolve("ba_TestNameServiceStub_name0"));
	}

	@Test
	public void rebindWithCreateSkeleton() {
		assertNull(nss.resolve("ba_TestNameServiceStub_name1"));
		assertNull(SkeletonBindings.getSkeleton("ba_TestNameServiceStub_name1"));
		Manager m = new ManagerDummy();
		nss.rebind(m, "ba_TestNameServiceStub_name1");
		Object obj = nss.resolve("ba_TestNameServiceStub_name1");
		assertEquals("class branch_access.ManagerProxy", obj.getClass().toString());
		assertNotNull(SkeletonBindings.getSkeleton("ba_TestNameServiceStub_name1"));
	}

	// @Test
	// public void manager() {
	// nss.rebind(new branch_access.ManagerDummy(), "name2");
	// Object obj = nss.resolve("name2");
	// assertEquals("class branch_access.ManagerStub",
	// obj.getClass().toString());
	// }

}
