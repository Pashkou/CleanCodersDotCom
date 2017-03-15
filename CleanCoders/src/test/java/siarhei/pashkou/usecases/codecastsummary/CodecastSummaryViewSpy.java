package siarhei.pashkou.usecases.codecastsummary;

import siarhei.pashkou.usecases.CodecastView;

public class CodecastSummaryViewSpy implements CodecastView<CodecastSummariesViewModel> {
	public CodecastSummariesViewModel viewModel;
	public boolean generateViewWasCalled;
	@Override
	public String generateView(CodecastSummariesViewModel viewModel) {
		this.viewModel = viewModel;
		generateViewWasCalled = true;
		return null;
	}

}
