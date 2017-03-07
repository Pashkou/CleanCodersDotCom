package siarhei.pashkou.persistence;

import siarhei.pashkou.model.Codecast;

public class CodecastGatewayUtilities extends GatewayUtilities<Codecast>  {
	public Codecast saveCodecast(Codecast codecast){
		save(codecast);
		return codecast;
	}
}
