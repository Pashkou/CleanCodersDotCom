package siarhei.pashkou.codecast;

import java.time.LocalDate;

import siarhei.pashkou.License;
import siarhei.pashkou.License.LicenseType;
import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;
import siarhei.pashkou.persistence.InMemoryCodecastGateway;
import siarhei.pashkou.persistence.InMemoryLicenseGateway;
import siarhei.pashkou.persistence.InMemoryUserGateway;

public class ContextSetup {
	public static void initializeContext(){
		Context.userGateway = new InMemoryUserGateway();
		Context.licenseGateway = new InMemoryLicenseGateway();
		Context.codecastGateway = new InMemoryCodecastGateway();
	}
	
	public static void setupSampleData(){
		User user = Context.userGateway.save("Sergei");
		
		Codecast episode1 = new Codecast();
		episode1.setTitle("Episode 1 - The beginning");
		episode1.setPublished(LocalDate.now());
		Context.codecastGateway.saveCodecast(episode1);
		
		Codecast episode2 = new Codecast();
		episode2.setTitle("Episode 2 - The continuetion");
		episode2.setPublished(episode1.getPublished().plusDays(1));
		Context.codecastGateway.saveCodecast(episode2);
		
		License sergeiE1 = new License(LicenseType.VIEWABLE, user, episode1);
		License sergeiE2 = new License(LicenseType.VIEWABLE, user, episode2);
		Context.licenseGateway.save(sergeiE1);
		Context.licenseGateway.save(sergeiE2);
		
	}
}
