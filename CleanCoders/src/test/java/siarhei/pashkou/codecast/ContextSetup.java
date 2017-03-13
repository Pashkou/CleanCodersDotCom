package siarhei.pashkou.codecast;

import java.time.LocalDate;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.context.GateKeeper;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.License;
import siarhei.pashkou.model.User;
import siarhei.pashkou.model.License.LicenseType;
import siarhei.pashkou.utilities.InMemoryCodecastGateway;
import siarhei.pashkou.utilities.InMemoryLicenseGateway;
import siarhei.pashkou.utilities.InMemoryUserGateway;

public class ContextSetup {
	public static void initializeContext(){
		Context.userGateway = new InMemoryUserGateway();
		Context.licenseGateway = new InMemoryLicenseGateway();
		Context.codecastGateway = new InMemoryCodecastGateway();
		Context.gateKepper = new GateKeeper();
	}
	
	public static void setupSampleData(){
		initializeContext();
		
		User user = Context.userGateway.save("Sergei");
		
		Codecast episode1 = new Codecast();
		episode1.setTitle("Episode 1 - The beginning");
		episode1.setPublished(LocalDate.now());
		episode1.setPermalink("e1");
		Context.codecastGateway.saveCodecast(episode1);
		
		Codecast episode2 = new Codecast();
		episode2.setTitle("Episode 2 - The continuetion");
		episode2.setPublished(episode1.getPublishedDate().plusDays(1));
		episode2.setPermalink("e2");
		Context.codecastGateway.saveCodecast(episode2);
		
		License sergeiE1 = new License(LicenseType.VIEWABLE, user, episode1);
		License sergeiE2 = new License(LicenseType.VIEWABLE, user, episode2);
		Context.licenseGateway.save(sergeiE1);
		Context.licenseGateway.save(sergeiE2);
		
		Context.gateKepper.setLogedInUser(user);
	}
}
