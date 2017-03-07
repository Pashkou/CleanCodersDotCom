package siarhei.pashkou.persistence;

import java.util.List;

import siarhei.pashkou.model.User;

public interface UserGateway {
	User save(String username);
	User findUserByName(String username);
	List<User> findAll();
}
