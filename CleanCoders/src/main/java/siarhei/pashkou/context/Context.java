package siarhei.pashkou.context;

import siarhei.pashkou.persistence.CodecastGateway;
import siarhei.pashkou.persistence.LicenseGateway;
import siarhei.pashkou.persistence.UserGateway;

public class Context {
	public static UserGateway userGateway;
	public static CodecastGateway codecastGateway;
	public static LicenseGateway licenseGateway;
}
