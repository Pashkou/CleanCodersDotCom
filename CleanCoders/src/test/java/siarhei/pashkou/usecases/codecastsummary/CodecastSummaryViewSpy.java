package siarhei.pashkou.usecases.codecastsummary;

public class CodecastSummaryViewSpy implements CodecastSummariesView {
	public CodecastSummariesViewModel viewModel;
	public boolean generateViewWasCalled;
	@Override
	public String generateView(CodecastSummariesViewModel viewModel) {
		this.viewModel = viewModel;
		generateViewWasCalled = true;
		return null;
	}

}
