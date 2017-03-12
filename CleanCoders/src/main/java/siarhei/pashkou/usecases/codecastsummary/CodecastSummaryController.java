package siarhei.pashkou.usecases.codecastsummary;

import java.io.IOException;
import java.util.List;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.html.Controller;
import siarhei.pashkou.html.ParsedRequest;
import siarhei.pashkou.views.ViewTemplate;

public class CodecastSummaryController extends Controller{

	@Override
	public String handle(ParsedRequest request) {
		CodecastSummaryUseCase useCase = new CodecastSummaryUseCase();
		
		List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(Context.gateKepper.getLogedInUser());
		String frontPage = null;
		try {
			frontPage = new CodecastSummaryView().toHTML(presentableCodecasts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return makeResponse(frontPage);
	}
}
