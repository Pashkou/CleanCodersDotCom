package siarhei.pashkou.usecases;

import java.util.List;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.License;
import siarhei.pashkou.model.License.LicenseType;
import siarhei.pashkou.model.User;

public class CodecastUseCase {
	
	public static boolean isLincenseForCodecast(LicenseType type, User user, Codecast codecast) {
		List<License> licenses = Context.licenseGateway.findLicenseForUserAndCodecast(user, codecast);
		for(License l: licenses)
			if(l.getType() == type)
				return true;
		return false;
	}
}
