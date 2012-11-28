package branch_access;

public final class ManagerDummy extends Manager {

	@Override
	public String createAccount(String owner) {
		return "FAKE_ACCOUNT";
	}

	@Override
	public double getBalance(String accountID) {
		return 6.66;
	}

}
