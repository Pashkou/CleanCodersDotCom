package siarhei.pashkou.usecases.codecastsummary;

public class CodecastSummaryOutputBoundarySpy implements CodecastSummaryOutputBoundary {

	public CodecastSummaryResponseModel responseModel;
	public CodecastSummariesViewModel viewModel;

	public CodecastSummaryResponseModel getResponseModel() {
		return responseModel;
	}

	@Override
	public CodecastSummariesViewModel getViewModel() {
		return viewModel;
	}

	@Override
	public void present(CodecastSummaryResponseModel codecastSummaryResponseModel) {
		responseModel = codecastSummaryResponseModel;
	}

}
