package siarhei.pashkou.usecases.codecastdetails;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;
import siarhei.pashkou.usecases.CodecastDetailsViewModel;
import siarhei.pashkou.usecases.CodecastInputBoundary;
import siarhei.pashkou.usecases.CodecastOutputBoundary;
import siarhei.pashkou.usecases.CodecastUseCase;
import siarhei.pashkou.usecases.RequestModel;

public class CodecastDetailsUseCase extends CodecastUseCase implements CodecastInputBoundary<CodecastDetailsViewModel, CodecastDetailsResponseModel> {
	
	@Override
	public void execute(RequestModel requestModel, CodecastOutputBoundary<CodecastDetailsViewModel, CodecastDetailsResponseModel> presenter) {
		Codecast codecast = Context.codecastGateway.findCodecastByPermalink(requestModel.permalink);		
		CodecastDetailsResponseModel codecastDetailsResponseModel = createCodecastDetails(codecast);
		presenter.present(codecastDetailsResponseModel);
	}
	
	private CodecastDetailsResponseModel createCodecastDetails(Codecast codecast){
		CodecastDetailsResponseModel codecastDetailsResponseModel = new CodecastDetailsResponseModel();
		CodecastDetails codecastDetails = new CodecastDetails();
		codecastDetails.title = codecast.getTitle();
		codecastDetails.permalink = codecast.getPermalink();
		codecastDetails.publishedDate = codecast.getPublishedDate();
		codecastDetailsResponseModel.codecastDetails = codecastDetails;
		return codecastDetailsResponseModel;
	}
}
