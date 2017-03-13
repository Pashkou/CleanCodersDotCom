package siarhei.pashkou.fixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummaryResponseModel;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummaryUseCase;

//OrderedQuery
public class OfCodeCasts {
	private List<Object> list(Object...objects) { return Arrays.asList(objects);}
	
	public List<Object> query(){
		List<CodecastSummaryResponseModel> presentableCodecasts = new CodecastSummaryUseCase().presentCodecasts(Context.gateKepper.getLogedInUser());
		List<Object> queryResponse = new ArrayList<>();
		for(CodecastSummaryResponseModel pC: presentableCodecasts){
			queryResponse.add(makeRow(pC.title, pC.publishedDate, "picture", "description", pC.isViewable, pC.isDownloadable));
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
