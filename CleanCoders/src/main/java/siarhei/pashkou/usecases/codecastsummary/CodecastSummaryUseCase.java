package siarhei.pashkou.usecases.codecastsummary;

import java.util.List;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.License.LicenseType;
import siarhei.pashkou.model.User;
import siarhei.pashkou.usecases.CodecastUseCase;

public class CodecastSummaryUseCase extends CodecastUseCase implements CodecastSummaryInputBoundary {

	@Override
	public void summarizeCodecasts(User user, CodecastSummaryOutputBoundary presenter) {
		CodecastSummaryResponseModel codecastSummaryResponseModel = new CodecastSummaryResponseModel();
		
		List<Codecast> codecasts = Context.codecastGateway.findAllCodecastsSortedByDate();
		codecasts
			.stream()
			.forEach((c)-> codecastSummaryResponseModel.addCodecastSummary(summarizeCodecast(c, user)));
		presenter.present(codecastSummaryResponseModel);
	}

	private CodecastSummary summarizeCodecast(Codecast codecast, User user){
		CodecastSummary codecastSummary = new CodecastSummary(); //DTO
		codecastSummary.title = codecast.getTitle();
		codecastSummary.publishedDate = codecast.getPublishedDate();
		codecastSummary.permalink = codecast.getPermalink();
		codecastSummary.isViewable = CodecastUseCase.isLincenseForCodecast(LicenseType.VIEWABLE, user, codecast);
		codecastSummary.isDownloadable = CodecastUseCase.isLincenseForCodecast(LicenseType.DOWNLOADABLE, user, codecast);
		return codecastSummary;
	}
}
