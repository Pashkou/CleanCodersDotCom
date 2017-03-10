package siarhei.pashkou.fixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.context.PresentableCodecast;
import siarhei.pashkou.usecases.PresentCodecastUseCase;

//OrderedQuery
public class OfCodeCasts {
	private List<Object> list(Object...objects) { return Arrays.asList(objects);}
	
	public List<Object> query(){
		List<PresentableCodecast> presentableCodecasts = new PresentCodecastUseCase().presentCodecasts(Context.gateKepper.getLogedInUser());
		List<Object> queryResponse = new ArrayList<>();
		for(PresentableCodecast pC: presentableCodecasts){
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
