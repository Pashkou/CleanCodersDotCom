package siarhei.pashkou.usecases.codecastsummary;

import java.io.IOException;
import java.util.List;

import siarhei.pashkou.views.ViewTemplate;

public class CodecastSummaryViewImpl implements CodecastSummariesView {
	public String toHTML(List<CodecastSummaryResponseModel> presentableCodecasts) throws IOException {
		ViewTemplate frontPageTemplate = ViewTemplate.create("html/frontPage.html");
		
		StringBuilder codecastView = new StringBuilder();
		for(CodecastSummaryResponseModel presentableCodecast:presentableCodecasts){
			ViewTemplate codecastTemplate = ViewTemplate.create("html/codecast.html");
			codecastTemplate.replace("title", presentableCodecast.title);	
			codecastTemplate.replace("publicationDate", presentableCodecast.publishedDate);	
			codecastTemplate.replace("permalink", presentableCodecast.permalink);	
			codecastView.append(codecastTemplate.getContent());
		}
		
		frontPageTemplate.replace("codecasts", codecastView.toString());
		return frontPageTemplate.getContent();
	}

	@Override
	public String generateView(CodecastSummariesViewModel responseModel) {
		// TODO Auto-generated method stub
		return null;
	}


}
