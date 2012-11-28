package branch_access;

import mware_lib.Communicator;

public final class ManagerSkeleton extends Thread implements mware_lib.Skeleton {
	private final String name;
	private final Manager manager;

	public ManagerSkeleton(String name, Manager manager) {
		this.name = name;
		this.manager = manager;
	}

	@Override
	public void unmarshal(String msg, Communicator comm) {
		String[] resultLine = msg.split(",");
		long msgId = Long.parseLong(resultLine[1]);
		String method = resultLine[3];
		String param = resultLine[4];
		if (method.equals("createAccount")) {
			new CreateAccountCaller(comm, msgId, manager, param).start();
		} else if (method.equals("getBalance")) {
			new GetBalanceCaller(comm, msgId, manager, param).start();
		} else {
			// method name not supported, throw exception
		}
	}

	@Override
	public String name() {
		return name;
	}
}
