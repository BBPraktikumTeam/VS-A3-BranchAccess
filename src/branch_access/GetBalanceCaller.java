package branch_access;

import mware_lib.Communicator;
import mware_lib.Utilities;

final class GetBalanceCaller extends Thread {
	private final Communicator comm;
	private final long msgId;
	private final Manager manager;
	private final String accountID;

	GetBalanceCaller(Communicator comm, long msgId, Manager manager,
			String accountID) {
		this.comm = comm;
		this.msgId = msgId;
		this.manager = manager;
		this.accountID = accountID;
	}

	@Override
	public void run() {
		double result = manager.getBalance(accountID);
		comm.send(Utilities.join(",", String.valueOf(msgId),
				String.valueOf(result)));
	}
}
