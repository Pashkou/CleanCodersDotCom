package siarhei.pashkou.usecases.codecastdetails;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;
import siarhei.pashkou.presenter.CodecastPresenter;
import siarhei.pashkou.usecases.CodecastUseCase;

public class CodecastDetailsUseCase extends CodecastUseCase {
	private User user;
	private String permalink;
	
	public PresentableCodecastDetails requestCodecastDetails(User logedInUser, String permalink) {
		PresentableCodecastDetails details = new PresentableCodecastDetails();
		Codecast codecast = Context.codecastGateway.findCodecastByPermalink(permalink);		
		if(codecast != null){
			details.wasFound = true;
			details.presentableCodecast = new CodecastPresenter().doFormat(logedInUser,codecast);
		}else{
			details.wasFound = false;
		}
	
		return details;
	}

}
