package siarhei.pashkou.persistence;

import java.util.List;

import siarhei.pashkou.License;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;

public interface LicenseGateway {
	License save(License license);
	void delete(License license);
	List<License> findAll();
	List<License> findLicenseForUserAndCodecast(User user, Codecast codecast);
}
