package branch_access;

import java.net.InetSocketAddress;

final class CreateAccountCaller extends Thread {
	private final InetSocketAddress sender;
	private final long senderObjectId;
	private final Manager manager;
	private final String owner;

	CreateAccountCaller(InetSocketAddress sender, long senderObjectId,
			Manager manager, String owner) {
		this.sender = sender;
		this.senderObjectId = senderObjectId;
		this.manager = manager;
		this.owner = owner;
	}

	@Override
	public void run() {
		String result = manager.createAccount(owner);
		String msg = "java.lang.String" + "," + result;
		mware_lib.Communicator.send(sender, senderObjectId, msg);
	}
}
