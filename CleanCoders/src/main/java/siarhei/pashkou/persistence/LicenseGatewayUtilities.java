package siarhei.pashkou.persistence;

import siarhei.pashkou.License;
import siarhei.pashkou.License.LicenseType;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;

public class LicenseGatewayUtilities extends GatewayUtilities<License> {
	public License save(LicenseType licenseType, User user, Codecast codecast){
		License license = new License(licenseType, user, codecast);
		license = save(license);
		return license;
	}
}
