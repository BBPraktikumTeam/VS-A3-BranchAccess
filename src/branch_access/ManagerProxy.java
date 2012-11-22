package branch_access;

import java.net.InetSocketAddress;
import java.util.concurrent.Semaphore;

import mware_lib.CommunicatorBindings;
import mware_lib.MessageId;
import mware_lib.MessageSemaphores;
import mware_lib.ReplyMessages;
import mware_lib.RequestMessage;

public final class ManagerProxy extends Manager implements mware_lib.Proxy {

	private final String name;
	private final InetSocketAddress address;

	public ManagerProxy() {
		this.name = "dummy";
		this.address = InetSocketAddress.createUnresolved("localhost", 0);
	}

	ManagerProxy(String name, InetSocketAddress address) {
		this.name = name;
		this.address = address;
	}

	@Override
	public String createAccount(String owner) {
		long id = MessageId.getNewId();
		Semaphore sem = MessageSemaphores.create(id);
		RequestMessage req = new RequestMessage(id, name, address,
				"createAccount", owner);
		CommunicatorBindings.getCommunicator(address).send(req.toString());
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ReplyMessages.pop(id).value();
	}

	@Override
	public double getBalance(String accountID) {
		long id = MessageId.getNewId();
		Semaphore sem = MessageSemaphores.create(id);
		RequestMessage req = new RequestMessage(id, name, address,
				"getBalance", accountID);
		CommunicatorBindings.getCommunicator(address).send(req.toString());
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Double.valueOf(ReplyMessages.pop(id).value());
	}

}
