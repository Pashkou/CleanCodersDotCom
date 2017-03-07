package siarhei.pashkou.codecast;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.persistence.InMemoryCodecastGateway;
import siarhei.pashkou.persistence.InMemoryLicenseGateway;
import siarhei.pashkou.persistence.InMemoryUserGateway;

public class ContextSetup {
	public static void initializeContext(){
		Context.userGateway = new InMemoryUserGateway();
		Context.licenseGateway = new InMemoryLicenseGateway();
		Context.codecastGateway = new InMemoryCodecastGateway();

	}
}
