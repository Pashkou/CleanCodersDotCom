package siarhei.pashkou.fixture;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.usecases.codecastdetails.CodecastDetailsUseCase;
import siarhei.pashkou.usecases.codecastdetails.PresentableCodecastDetailsViewModel;

public class CodecastDetails {
	private PresentableCodecastDetailsViewModel details;

	public boolean requestCodecast(String permalink){
		CodecastDetailsUseCase codecastDetailsUseCase = new CodecastDetailsUseCase();
		//details = codecastDetailsUseCase.requestCodecastDetails(Context.gateKepper.getLogedInUser(), permalink);
		return true;
	}
	
	public String codecastDetailsTitle(){
		return null;
	}
	
	public String codecastDetailsDate(){
		return null;
	}
	
	public boolean codecastDetailsOfferPurchaseOf(String licenseType){
		/*return 
				   (  (  licenseType.equals("viewing") && !details.presentableCodecast.isViewable  ) ||
					  (  licenseType.equals("download") && !details.presentableCodecast.isDownloadable ));*/
		return false;
	}
}
