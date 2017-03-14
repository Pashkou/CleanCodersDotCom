package siarhei.pashkou.usecases.codecastsummary;

public interface CodecastSummaryOutputBoundary {

	CodecastSummariesViewModel getViewModel();

	void present(CodecastSummaryResponseModel codecastSummaryResponseModel);

}
