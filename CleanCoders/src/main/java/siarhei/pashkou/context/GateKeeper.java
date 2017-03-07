package siarhei.pashkou.context;

import siarhei.pashkou.model.User;

public class GateKeeper {
	private User user;
	
	public void setLogedInUser(User user) {
		this.user = user;
	}

	public User getLogedInUser() {
		return user;
	}


}
