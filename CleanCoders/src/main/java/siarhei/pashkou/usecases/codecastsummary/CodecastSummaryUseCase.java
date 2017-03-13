package siarhei.pashkou.usecases.codecastsummary;

import java.util.ArrayList;
import java.util.List;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;
import siarhei.pashkou.presenter.CodecastPresenter;
import siarhei.pashkou.usecases.CodecastUseCase;

public class CodecastSummaryUseCase extends CodecastUseCase implements CodecastSummaryInputBoundary {

	public List<CodecastSummaryResponseModel> presentCodecasts(User logedInUser) {
		List<Codecast> codecasts = Context.codecastGateway.findAllCodecastsSortedByDate();
		List<CodecastSummaryResponseModel> presentableCodecats = new ArrayList<CodecastSummaryResponseModel>();
		for(Codecast codecast:codecasts){
			preparePresentationCodecast(logedInUser, presentableCodecats, codecast);
		}
		return presentableCodecats;
	}

	private void preparePresentationCodecast(User logedInUser, List<CodecastSummaryResponseModel> presentableCodecats, Codecast codecast) {
		CodecastSummaryResponseModel pc = new CodecastPresenter().doFormat(logedInUser, codecast);
		presentableCodecats.add(pc);
	}

	@Override
	public void summarizeCodecasts(User user, CodecastSummaryOutputBoundary presenter) {
		// TODO Auto-generated method stub
		
	}
}
