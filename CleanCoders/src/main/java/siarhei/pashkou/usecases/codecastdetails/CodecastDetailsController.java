package siarhei.pashkou.usecases.codecastdetails;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.html.Controller;
import siarhei.pashkou.html.ParsedRequest;
import siarhei.pashkou.model.User;
import siarhei.pashkou.usecases.CodecastDetailsViewModel;
import siarhei.pashkou.usecases.CodecastInputBoundary;
import siarhei.pashkou.usecases.CodecastOutputBoundary;
import siarhei.pashkou.usecases.CodecastView;
import siarhei.pashkou.usecases.RequestModel;

public class CodecastDetailsController extends Controller {
	private CodecastInputBoundary<CodecastDetailsViewModel, CodecastDetailsResponseModel> useCase;
	private CodecastOutputBoundary<CodecastDetailsViewModel, CodecastDetailsResponseModel> presenter;
	private CodecastView<CodecastDetailsViewModel> view;
	
	
	public CodecastDetailsController(CodecastInputBoundary<CodecastDetailsViewModel, CodecastDetailsResponseModel> useCase,
			CodecastOutputBoundary<CodecastDetailsViewModel, CodecastDetailsResponseModel>  presenter,
			CodecastView<CodecastDetailsViewModel> view) {
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
