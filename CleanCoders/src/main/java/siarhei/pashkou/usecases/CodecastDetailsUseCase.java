package siarhei.pashkou.usecases;

import java.util.List;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.context.PresentableCodecastDetails;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.License;
import siarhei.pashkou.model.User;

public class CodecastDetailsUseCase extends CodecastUseCase {
	private User user;
	private String permalink;
	
	public PresentableCodecastDetails requestCodecastDetails(User logedInUser, String permalink) {
		PresentableCodecastDetails details = new PresentableCodecastDetails();
		Codecast codecast = Context.codecastGateway.findCodecastByPermalink(permalink);		
		details.presentableCodecast = doFormat(logedInUser,codecast);
		details.permalink = permalink;
		List<License> license = Context.licenseGateway.findLicenseForUserAndCodecast(logedInUser, codecast);
	
		return (PresentableCodecastDetails)details;
	}

}
