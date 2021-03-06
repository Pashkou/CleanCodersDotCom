package siarhei.pashkou.usecases.codecastsummary;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.html.Controller;
import siarhei.pashkou.html.ParsedRequest;
import siarhei.pashkou.model.User;
import siarhei.pashkou.usecases.CodecastInputBoundary;
import siarhei.pashkou.usecases.CodecastOutputBoundary;
import siarhei.pashkou.usecases.CodecastView;
import siarhei.pashkou.usecases.RequestModel;

public class CodecastSummaryController extends Controller {
	private CodecastInputBoundary<CodecastSummariesViewModel, CodecastSummaryResponseModel> useCase;
	private  CodecastOutputBoundary<CodecastSummariesViewModel, CodecastSummaryResponseModel> presenter;
	private CodecastView<CodecastSummariesViewModel> view;
	public CodecastSummaryController() {}
	
	
	public CodecastSummaryController(CodecastInputBoundary<CodecastSummariesViewModel, CodecastSummaryResponseModel> useCase,
			CodecastOutputBoundary<CodecastSummariesViewModel, CodecastSummaryResponseModel> presenter,
			CodecastView<CodecastSummariesViewModel> view) {
		this.useCase = useCase;
		this.presenter = presenter;
		this.view = view;
	}

	@Override
	public String handle(ParsedRequest request, User user) {
		RequestModel requestModel = new RequestModel();
		requestModel.logedInUser = Context.gateKepper.getLogedInUser();
		useCase.execute(requestModel, presenter);
		return view.generateView(presenter.getViewModel());
	}


}

