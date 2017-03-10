package siarhei.pashkou.persistence;

import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.License;
import siarhei.pashkou.model.User;
import siarhei.pashkou.model.License.LicenseType;

public class LicenseGatewayUtilities extends GatewayUtilities<License> {
	public License save(LicenseType licenseType, User user, Codecast codecast){
		License license = new License(licenseType, user, codecast);
		license = save(license);
		return license;
	}
}
