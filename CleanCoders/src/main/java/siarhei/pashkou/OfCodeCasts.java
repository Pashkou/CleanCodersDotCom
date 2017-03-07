package siarhei.pashkou;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import siarhei.pashkou.context.PresentCodecastUseCase;
import siarhei.pashkou.context.PresentableCodecast;

//OrderedQuery
public class OfCodeCasts {
	private List<Object> list(Object...objects) { return Arrays.asList(objects);}
	
	public List<Object> query(){
		List<PresentableCodecast> presentableCodecasts = new PresentCodecastUseCase().presentCodecasts(CodecastPresentation.gateKepper.getLogedInUser());
		List<Object> queryResponse = new ArrayList<>();
		for(PresentableCodecast pC: presentableCodecasts){
			queryResponse.add(makeRow(pC.title, pC.published, "picture", "description", pC.isViewable, pC.isDownloadable));
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
