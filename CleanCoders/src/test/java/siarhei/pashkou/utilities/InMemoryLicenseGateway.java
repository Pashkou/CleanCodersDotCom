package siarhei.pashkou.utilities;

import java.util.List;
import java.util.stream.Collectors;

import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.License;
import siarhei.pashkou.model.User;
import siarhei.pashkou.persistence.LicenseGateway;
import siarhei.pashkou.persistence.LicenseGatewayUtilities;

public class InMemoryLicenseGateway extends LicenseGatewayUtilities implements LicenseGateway {

	@Override
	public List<License> findLicenseForUserAndCodecast(User user, Codecast codecast) {
		return findAll().stream().
				filter(l -> l.getUser().equals(user)).
				filter(l -> l.getCodecast().equals(codecast)).
				collect(Collectors.toList());
	}


}
