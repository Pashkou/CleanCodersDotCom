package siarhei.pashkou;

import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.Entity;
import siarhei.pashkou.model.User;

public class License extends Entity {
	private User user;
	private Codecast codecast;
	private LicenseType type;
	
	public License(LicenseType type, User user, Codecast codecast) {
		this.user = user;
		this.codecast = codecast;
		this.setType(type);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Codecast getCodecast() {
		return codecast;
	}

	public void setCodecast(Codecast codecast) {
		this.codecast = codecast;
	}
	
	public LicenseType getType() {
		return type;
	}

	public void setType(LicenseType type) {
		this.type = type;
	}

	public enum LicenseType{
		VIEWABLE, DOWNLOADABLE
	}

}
