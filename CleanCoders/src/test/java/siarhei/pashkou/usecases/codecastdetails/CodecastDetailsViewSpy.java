package siarhei.pashkou.usecases.codecastdetails;

import siarhei.pashkou.usecases.CodecastDetailsViewModel;
import siarhei.pashkou.usecases.CodecastView;

public class CodecastDetailsViewSpy implements CodecastView<CodecastDetailsViewModel>{
	public CodecastDetailsViewModel viewModel;
	public boolean generateViewWasCalled;
	
	@Override
	public String generateView(CodecastDetailsViewModel viewModel) {
		this.viewModel = viewModel;
		generateViewWasCalled = true;
		return null;
	}

}
