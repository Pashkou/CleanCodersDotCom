package siarhei.pashkou.fixture;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.usecases.CodecastDetailsUseCase;

public class CodecastDetails {
	public boolean requestCodecast(String permalink){
		CodecastDetailsUseCase codecastDetailsUseCase = new CodecastDetailsUseCase();
		codecastDetailsUseCase.requestCodecastDetails(Context.gateKepper.getLogedInUser(), permalink);
		return false;
	}
	
	public String codecastDetailsTitle(){
		return "";
	}
	
	public String codecastDetailsDate(){
		return "";
	}
	
	public boolean codecastDetailsOfferPurchaseOf(String licenseType){
		return false;
	}
}
