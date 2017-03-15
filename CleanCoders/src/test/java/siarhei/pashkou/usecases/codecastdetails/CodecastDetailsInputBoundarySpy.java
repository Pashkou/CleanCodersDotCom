package siarhei.pashkou.usecases.codecastdetails;

import siarhei.pashkou.model.User;
import siarhei.pashkou.usecases.CodecastDetailsViewModel;
import siarhei.pashkou.usecases.CodecastInputBoundary;
import siarhei.pashkou.usecases.CodecastOutputBoundary;
import siarhei.pashkou.usecases.RequestModel;

public class CodecastDetailsInputBoundarySpy implements CodecastInputBoundary<CodecastDetailsViewModel, CodecastDetailsResponseModel>  {
	public boolean detailedCodecastsWasCalled = false;
	public RequestModel actualModel;
	public User requestedUser;
	public CodecastOutputBoundary<CodecastDetailsViewModel, CodecastDetailsResponseModel> outputBoundary;
 	
	@Override
	public void execute(RequestModel requestModel,
			CodecastOutputBoundary<CodecastDetailsViewModel, CodecastDetailsResponseModel> presenter) {
		detailedCodecastsWasCalled = true;		
		actualModel = requestModel;
		requestedUser = requestModel.logedInUser;
		outputBoundary = presenter;
	}

}
