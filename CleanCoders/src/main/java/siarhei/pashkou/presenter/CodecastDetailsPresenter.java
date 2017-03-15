package siarhei.pashkou.presenter;

import java.time.format.DateTimeFormatter;

import siarhei.pashkou.usecases.CodecastDetailsViewModel;
import siarhei.pashkou.usecases.CodecastOutputBoundary;
import siarhei.pashkou.usecases.codecastdetails.CodecastDetailsResponseModel;
import siarhei.pashkou.usecases.codecastdetails.DetailsViewModel;

public class CodecastDetailsPresenter implements CodecastOutputBoundary<CodecastDetailsViewModel, CodecastDetailsResponseModel>{
	private CodecastDetailsViewModel codecastDetailsViewModel = new CodecastDetailsViewModel(); 
	
	@Override
	public CodecastDetailsViewModel getViewModel() {
		return codecastDetailsViewModel;
	}

	@Override
	public void present(CodecastDetailsResponseModel codecastResponseModel) {
		DetailsViewModel detailsViewModel = new DetailsViewModel();
		detailsViewModel.title = codecastResponseModel.codecastDetails.title;
		detailsViewModel.publishedDate = codecastResponseModel.codecastDetails.publishedDate.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
		detailsViewModel.permalink = codecastResponseModel.codecastDetails.permalink;
		
		codecastDetailsViewModel.detaisViewModel = detailsViewModel;
	}

}
