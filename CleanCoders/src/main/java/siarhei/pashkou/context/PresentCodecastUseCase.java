package siarhei.pashkou.context;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import siarhei.pashkou.License;
import siarhei.pashkou.License.LicenseType;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;

public class PresentCodecastUseCase {

	public List<PresentableCodecast> presentCodecasts(User logedInUser) {
		List<Codecast> codecasts = Context.codecastGateway.findAllCodecastsSortedByDate();
		List<PresentableCodecast> presentableCodecats = new ArrayList<PresentableCodecast>();
		for(Codecast codecast:codecasts){
			preparePresentationCodecast(logedInUser, presentableCodecats, codecast);
		}
		return presentableCodecats;
	}


	private void preparePresentationCodecast(User logedInUser, List<PresentableCodecast> presentableCodecats, Codecast codecast) {
		PresentableCodecast pc = new PresentableCodecast();
		pc.isViewable = isLincenseForCodecast(LicenseType.VIEWABLE, logedInUser, codecast);
		pc.isDownloadable = isLincenseForCodecast(LicenseType.DOWNLOADABLE, logedInUser, codecast);
		pc.title = codecast.getTitle();
		pc.published = codecast.getPublished().
				format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).
				toString();
		presentableCodecats.add(pc);
	}
	
	public boolean isLincenseForCodecast(LicenseType type, User user, Codecast codecast) {
		List<License> licenses = Context.licenseGateway.findLicenseForUserAndCodecast(user, codecast);
		for(License l: licenses)
			if(l.getType() == type)
				return true;
		return false;
	}
}
