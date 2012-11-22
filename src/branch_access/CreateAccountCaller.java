package branch_access;

import mware_lib.Communicator;
import mware_lib.Utilities;

final class CreateAccountCaller extends Thread {
	private final Communicator comm;
	private final long msgId;
	private final Manager manager;
	private final String owner;

	CreateAccountCaller(Communicator comm, long msgId, Manager manager,
			String owner) {
		this.comm = comm;
		this.msgId = msgId;
		this.manager = manager;
		this.owner = owner;
	}

	@Override
	public void run() {
		String result = manager.createAccount(owner);
		comm.send(Utilities.join(",", String.valueOf(msgId), result));
	}
}
