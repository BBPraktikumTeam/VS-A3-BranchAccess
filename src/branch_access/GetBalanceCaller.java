package branch_access;

import java.net.InetSocketAddress;

final class GetBalanceCaller extends Thread {
	private final InetSocketAddress sender;
	private final long senderObjectId;
	private final Manager manager;
	private final String accountID;

	GetBalanceCaller(InetSocketAddress sender, long senderObjectId,
			Manager manager, String accountID) {
		this.sender = sender;
		this.senderObjectId = senderObjectId;
		this.manager = manager;
		this.accountID = accountID;
	}

	@Override
	public void run() {
		String result = manager.createAccount(accountID);
		String msg = "java.lang.String" + "," + result;
		mware_lib.Communicator.send(sender, senderObjectId, msg);
	}
}
