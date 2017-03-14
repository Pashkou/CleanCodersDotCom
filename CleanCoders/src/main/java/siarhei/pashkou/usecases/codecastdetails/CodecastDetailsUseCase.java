package siarhei.pashkou.usecases.codecastdetails;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;
import siarhei.pashkou.presenter.CodecastSummariesPresenter;
import siarhei.pashkou.usecases.CodecastUseCase;

public class CodecastDetailsUseCase extends CodecastUseCase {
	private User user;
	private String permalink;
	
	public PresentableCodecastDetailsViewModel requestCodecastDetails(User logedInUser, String permalink) {
		PresentableCodecastDetailsViewModel details = new PresentableCodecastDetailsViewModel();
		Codecast codecast = Context.codecastGateway.findCodecastByPermalink(permalink);		
		if(codecast != null){
			details.wasFound = true;
			//details.presentableCodecast = new CodecastSummariesPresenter().doFormat(logedInUser,codecast);
		}else{
			details.wasFound = false;
		}
	
		return details;
	}

}
