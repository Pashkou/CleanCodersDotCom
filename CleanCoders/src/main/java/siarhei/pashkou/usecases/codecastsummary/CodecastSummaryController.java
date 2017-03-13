package siarhei.pashkou.usecases.codecastsummary;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.html.Controller;
import siarhei.pashkou.html.ParsedRequest;
import siarhei.pashkou.model.User;

public class CodecastSummaryController extends Controller{
	private CodecastSummaryInputBoundary useCase;
	private  CodecastSummaryOutputBoundary presenter;
	private CodecastSummariesView view;
	public CodecastSummaryController() {}
	
	
	public CodecastSummaryController(CodecastSummaryInputBoundary useCase, CodecastSummaryOutputBoundary presenter, CodecastSummariesView view) {
		this.useCase = useCase;
		this.presenter = presenter;
		this.view = view;
	}

	@Override
	public String handle(ParsedRequest request, User user) {
		useCase.summarizeCodecasts(Context.gateKepper.getLogedInUser(), presenter);
		view.generateView(presenter.getVieweModel());
		return "";
	}


}

/*CodecastSummaryUseCase useCase = new CodecastSummaryUseCase();
		
		List<PresentableCodecastViewModel> presentableCodecasts = useCase.presentCodecasts(Context.gateKepper.getLogedInUser());
		String frontPage = null;
		try {
			frontPage = new CodecastSummaryView().toHTML(presentableCodecasts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return makeResponse(frontPage);*/