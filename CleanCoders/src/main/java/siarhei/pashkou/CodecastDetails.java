package siarhei.pashkou;

import siarhei.pashkou.context.Context;

public class CodecastDetails {
	public boolean requestCodecast(String permalink){
		CodecastDetailsUseCase codecastDetailsUseCase = new CodecastDetailsUseCase();
		//codecastDetailsUseCase.requestCodecastDetails(Context. permalink);
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
