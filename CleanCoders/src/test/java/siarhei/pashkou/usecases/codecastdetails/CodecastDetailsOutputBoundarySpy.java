package siarhei.pashkou.usecases.codecastdetails;

import siarhei.pashkou.usecases.CodecastDetailsViewModel;
import siarhei.pashkou.usecases.CodecastOutputBoundary;
import siarhei.pashkou.usecases.CodecastResponseModel;
import siarhei.pashkou.usecases.CodecastViewModel;

public class CodecastDetailsOutputBoundarySpy implements CodecastOutputBoundary<CodecastDetailsViewModel, CodecastDetailsResponseModel> {
	public CodecastDetailsResponseModel codecastResponseModel;
	public CodecastDetailsViewModel viewModel;

	@Override
	public CodecastDetailsViewModel getViewModel() {
		return viewModel;
	}

	@Override
	public void present(CodecastDetailsResponseModel codecastResponseModel) {
		this.codecastResponseModel = codecastResponseModel;		
	}
}
