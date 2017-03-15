package siarhei.pashkou.usecases.codecastsummary;

import siarhei.pashkou.model.User;
import siarhei.pashkou.usecases.CodecastInputBoundary;
import siarhei.pashkou.usecases.CodecastOutputBoundary;
import siarhei.pashkou.usecases.RequestModel;

public class CodecastSummaryInputBoundarySpy implements CodecastInputBoundary {

	public boolean summarizeCodecastsWasCalled = false;
	public User requestedUser;
	public CodecastOutputBoundary outputBoundary;


	@Override
	public void execute(RequestModel requestModel, CodecastOutputBoundary presenter) {
		summarizeCodecastsWasCalled = true;
		requestedUser = requestModel.logedInUser;
		outputBoundary = presenter;
	}
		
}
