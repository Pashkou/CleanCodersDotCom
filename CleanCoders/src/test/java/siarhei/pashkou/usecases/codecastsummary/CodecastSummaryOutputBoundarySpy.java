package siarhei.pashkou.usecases.codecastsummary;

import siarhei.pashkou.usecases.CodecastOutputBoundary;

public class CodecastSummaryOutputBoundarySpy implements CodecastOutputBoundary<CodecastSummariesViewModel, CodecastSummaryResponseModel> {

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
