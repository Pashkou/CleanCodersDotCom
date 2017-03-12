package siarhei.pashkou.utilities;

import java.util.List;

import siarhei.pashkou.model.User;
import siarhei.pashkou.persistence.UserGateway;
import siarhei.pashkou.persistence.UserGatewayUtilities;

public class InMemoryUserGateway extends UserGatewayUtilities implements UserGateway {

	@Override
	public User findUserByName(String username) {
		List<User> users = findAll();
		return users.stream().
				filter(u -> u.getUsername().equalsIgnoreCase(username)).
				findAny().orElse(null);
	}
}
