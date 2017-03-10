package siarhei.pashkou.usecases;

import java.time.format.DateTimeFormatter;
import java.util.List;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.context.PresentableCodecast;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.License;
import siarhei.pashkou.model.User;
import siarhei.pashkou.model.License.LicenseType;

public abstract class CodecastUseCase {
	protected PresentableCodecast doFormat(User logedInUser, Codecast codecast) {
		PresentableCodecast pc = new PresentableCodecast();
		pc.isViewable = isLincenseForCodecast(LicenseType.VIEWABLE, logedInUser, codecast);
		pc.isDownloadable = isLincenseForCodecast(LicenseType.DOWNLOADABLE, logedInUser, codecast);
		pc.title = codecast.getTitle();
		pc.publishedDate = codecast.getPublishedDate().
				format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).
				toString();
		return pc;
	}
	
	public boolean isLincenseForCodecast(LicenseType type, User user, Codecast codecast) {
		List<License> licenses = Context.licenseGateway.findLicenseForUserAndCodecast(user, codecast);
		for(License l: licenses)
			if(l.getType() == type)
				return true;
		return false;
	}
}
