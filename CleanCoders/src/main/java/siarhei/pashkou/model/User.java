package siarhei.pashkou.model;

public class User extends Entity{
	private String username;

	public User(String username) {
		super();
		this.setUsername(username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
