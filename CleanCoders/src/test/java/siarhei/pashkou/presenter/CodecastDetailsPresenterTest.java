package siarhei.pashkou.presenter;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import siarhei.pashkou.usecases.CodecastDetailsViewModel;
import siarhei.pashkou.usecases.codecastdetails.CodecastDetails;
import siarhei.pashkou.usecases.codecastdetails.CodecastDetailsResponseModel;
import siarhei.pashkou.usecases.codecastdetails.DetailsViewModel;

public class CodecastDetailsPresenterTest {
	
	@Test
	public void validateViewModel() {
		CodecastDetailsResponseModel codecastDetailsResponseModel = new CodecastDetailsResponseModel();
		CodecastDetails codecastDetails = new CodecastDetails();
		codecastDetails.title = "Title";
		codecastDetails.publishedDate = LocalDate.now();
		codecastDetails.permalink = "permalink";
		codecastDetailsResponseModel.codecastDetails = codecastDetails;
		CodecastDetailsPresenter codecastPresenter = new CodecastDetailsPresenter();
		
		codecastPresenter.present(codecastDetailsResponseModel);
		
		CodecastDetailsViewModel viewModel = codecastPresenter.getViewModel();
		DetailsViewModel detailesViewModel = viewModel.detaisViewModel;
		assertEquals(codecastDetails.title, detailesViewModel.title);
		assertEquals(codecastDetails.publishedDate.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")), detailesViewModel.publishedDate);
		assertEquals(codecastDetails.permalink, detailesViewModel.permalink);
	}
}
