package siarhei.pashkou.fixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.presenter.CodecastSummariesPresenter;
import siarhei.pashkou.presenter.CodecastSummaryViewModel;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummary;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummaryUseCase;

//OrderedQuery
public class OfCodeCasts {
	private List<Object> list(Object...objects) { return Arrays.asList(objects);}
	
	public List<Object> query(){
		CodecastSummaryUseCase useCase = new CodecastSummaryUseCase();
		
		CodecastSummariesPresenter presenter = new CodecastSummariesPresenter();
		
		useCase.summarizeCodecasts(Context.gateKepper.getLogedInUser(), presenter);
		
		List<CodecastSummaryViewModel> codecastSummaryViewModels = presenter.getViewModel().viewModels;
		
		List<Object> queryResponse = new ArrayList<>();
		for(CodecastSummaryViewModel pC: codecastSummaryViewModels){
			queryResponse.add(makeRow(pC.title, pC.publishedDate.toString(), "picture", "description", pC.isViewable, pC.isDownloadable));
		}
		return queryResponse;
	}
	
	private List<Object> makeRow(String title, String publishedDate, String picture, String description, boolean viewable, boolean downloadable){
		return list(
				list("title", title),
				list("publication date", publishedDate),
				list("picture", title),
				list("description", title),
				list("viewable", viewable ? "+" : "-"),
				list("downloadable", downloadable ? "+" : "-")
				);
	}
}
