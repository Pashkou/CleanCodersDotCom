package siarhei.pashkou.usecases.codecastsummary;

import siarhei.pashkou.model.User;

public class CodecastSummaryInputBoundarySpy implements CodecastSummaryInputBoundary {

	public boolean summarizeCodecastsWasCalled = false;
	public User requestedUser;
	public CodecastSummaryOutputBoundary outputBoundary;


	@Override
	public void summarizeCodecasts(User user, CodecastSummaryOutputBoundary presenter) {
		summarizeCodecastsWasCalled = true;
		requestedUser = user;
		outputBoundary = presenter;
	}
		
}
