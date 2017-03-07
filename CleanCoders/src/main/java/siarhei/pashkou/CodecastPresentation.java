package siarhei.pashkou;

import java.util.List;

import siarhei.pashkou.License.LicenseType;
import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.context.Context;
import siarhei.pashkou.context.GateKeeper;
import siarhei.pashkou.context.PresentCodecastUseCase;
import siarhei.pashkou.context.PresentableCodecast;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;

public class CodecastPresentation {
	
	PresentCodecastUseCase useCase = new PresentCodecastUseCase();
	public static GateKeeper gateKepper = new GateKeeper();
	
	public CodecastPresentation(){
		ContextSetup.initializeContext();
	}
	
	public boolean clearCodecasts(){ 
		List<Codecast> codecasts = Context.codecastGateway.findAllCodecastsSortedByDate();
		codecasts.forEach(c -> Context.codecastGateway.delete(c));
		return Context.codecastGateway.findAllCodecastsSortedByDate().size() == 0;
	}

	public boolean addUser(String username){
		Context.userGateway.save(username);
		return true;
	}
	
	public boolean loginUser(String username){	
		User user = Context.userGateway.findUserByName(username);
		if(user != null){
			gateKepper.setLogedInUser(user);
			return true;
		}
		return false;
	}
	
	public boolean createLicenseForViewing(String username, String codecastTitle) {
    	User user = Context.userGateway.findUserByName(username);
		Codecast codecast = Context.codecastGateway.findCodecastByTitle(codecastTitle);
		License license = new License(LicenseType.VIEWABLE, user, codecast);
		Context.licenseGateway.save(license);
		return useCase.isLincenseForCodecast(LicenseType.VIEWABLE, user, codecast);
	
	}
	
	public boolean createLicenseForDownloading(String username, String codecastTitle) {
		User user = Context.userGateway.findUserByName(username);
		Codecast codecast = Context.codecastGateway.findCodecastByTitle(codecastTitle);
		License license = new License(LicenseType.DOWNLOADABLE, user, codecast);
		Context.licenseGateway.save(license);
		return useCase.isLincenseForCodecast(LicenseType.DOWNLOADABLE, user, codecast);
	}
	public String presentationUser(){ 
		return gateKepper.getLogedInUser().getUsername();
	}
	
	
	public int countOfCodecastsPresented(){	
		List<PresentableCodecast> presentations = useCase.presentCodecasts(gateKepper.getLogedInUser());
		return presentations.size();
	}
}
