package siarhei.pashkou.persistence;

import java.util.List;

import siarhei.pashkou.model.User;

public class InMemoryUserGateway extends UserGatewayUtilities implements UserGateway {

	@Override
	public User findUserByName(String username) {
		List<User> users = findAll();
		return users.stream().
				filter(u -> u.getUsername().equalsIgnoreCase(username)).
				findAny().orElse(null);
	}
}
