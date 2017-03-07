package siarhei.pashkou.persistence;

import java.util.List;
import java.util.stream.Collectors;

import siarhei.pashkou.License;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;

public class InMemoryLicenseGateway extends LicenseGatewayUtilities implements LicenseGateway {

	@Override
	public List<License> findLicenseForUserAndCodecast(User user, Codecast codecast) {
		return findAll().stream().
				filter(l -> l.getUser().equals(user)).
				filter(l -> l.getCodecast().equals(codecast)).
				collect(Collectors.toList());
	}


}
