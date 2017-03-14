package siarhei.pashkou.presenter;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import siarhei.pashkou.usecases.codecastsummary.CodecastSummariesViewModel;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummary;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummaryResponseModel;

public class CodecastSummariesPresenterTest {

	@Test
	public void validateViewModel() {
		CodecastSummaryResponseModel codecastSummaryResponseModel = new CodecastSummaryResponseModel();
		CodecastSummary codecastSummary = new CodecastSummary();
		codecastSummary.title = "Title";
		codecastSummary.publishedDate = LocalDate.now();
		codecastSummary.permalink = "permalink";
		codecastSummary.isViewable = true;
		codecastSummary.isDownloadable = false;
		codecastSummaryResponseModel.addCodecastSummary(codecastSummary);
		CodecastSummariesPresenter codecastSummariesPresenter = new CodecastSummariesPresenter();

		codecastSummariesPresenter.present(codecastSummaryResponseModel);
		CodecastSummariesViewModel codecastSummariesViewModel = codecastSummariesPresenter.getViewModel();
		CodecastSummaryViewModel codecastSummaryViewModel = codecastSummariesViewModel.viewModels.get(0);
		
		assertEquals(codecastSummary.title, codecastSummaryViewModel.title);
		assertEquals(codecastSummary.publishedDate.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")), codecastSummaryViewModel.publishedDate);
		assertEquals(codecastSummary.permalink, codecastSummaryViewModel.permalink);
		assertEquals(codecastSummary.isViewable, codecastSummaryViewModel.isViewable);
		assertEquals(codecastSummary.isDownloadable, codecastSummaryViewModel.isDownloadable);
	}

}
