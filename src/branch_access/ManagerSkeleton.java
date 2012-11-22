package branch_access;

final class ManagerSkeleton extends Thread {
	private final String name;
	private final Manager manager;

	ManagerSkeleton(String name, Manager manager) {
		this.name=name;
		this.manager=manager;
	}

	void unmarshal(String msg) {
		
	}
}
