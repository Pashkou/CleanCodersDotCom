package siarhei.pashkou.persistence;

import siarhei.pashkou.model.User;

public class UserGatewayUtilities extends GatewayUtilities<User> {
	public User save(String username){
		User user = new User(username);
		user = save(user);
		return user;
	}
}
