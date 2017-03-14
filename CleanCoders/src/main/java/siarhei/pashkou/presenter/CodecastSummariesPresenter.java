package siarhei.pashkou.presenter;

import java.time.format.DateTimeFormatter;

import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.License.LicenseType;
import siarhei.pashkou.model.User;
import siarhei.pashkou.usecases.CodecastUseCase;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummariesViewModel;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummary;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummaryOutputBoundary;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummaryResponseModel;

public class CodecastSummariesPresenter implements CodecastSummaryOutputBoundary {
	private CodecastSummariesViewModel codecastSummariesViewModel = new CodecastSummariesViewModel(); 
	
	/*public CodecastSummaryResponseModel doFormat(User logedInUser, Codecast codecast) {
		CodecastSummaryResponseModel pc = new CodecastSummaryResponseModel();
		pc.isViewable = CodecastUseCase.isLincenseForCodecast(LicenseType.VIEWABLE, logedInUser, codecast);
		pc.isDownloadable = CodecastUseCase.isLincenseForCodecast(LicenseType.DOWNLOADABLE, logedInUser, codecast);
		pc.title = codecast.getTitle();
		pc.permalink = codecast.getPermalink();
		pc.publishedDate = codecast.getPublishedDate();
		return pc;
	}*/

	@Override
	public CodecastSummariesViewModel getViewModel() {
		return codecastSummariesViewModel;
	}

	@Override
	public void present(CodecastSummaryResponseModel codecastSummaryResponseModel) {
		for(CodecastSummary codecastSummary:  codecastSummaryResponseModel.getCodecastSummaries()){
		CodecastSummaryViewModel codecastSummaryViewModel = new CodecastSummaryViewModel();
			codecastSummaryViewModel.title = codecastSummary.title;
			codecastSummaryViewModel.publishedDate = codecastSummary.publishedDate.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
			codecastSummaryViewModel.permalink = codecastSummary.permalink;
			codecastSummaryViewModel.isDownloadable = codecastSummary.isDownloadable;
			codecastSummaryViewModel.isViewable = codecastSummary.isViewable;
			codecastSummariesViewModel.addModel(codecastSummaryViewModel);
		}

	}
}
