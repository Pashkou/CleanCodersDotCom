package siarhei.pashkou.usecases.codecastsummary;

import java.util.ArrayList;
import java.util.List;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;
import siarhei.pashkou.presenter.CodecastPresenter;
import siarhei.pashkou.usecases.CodecastUseCase;

public class CodecastSummaryUseCase extends CodecastUseCase {

	public List<PresentableCodecast> presentCodecasts(User logedInUser) {
		List<Codecast> codecasts = Context.codecastGateway.findAllCodecastsSortedByDate();
		List<PresentableCodecast> presentableCodecats = new ArrayList<PresentableCodecast>();
		for(Codecast codecast:codecasts){
			preparePresentationCodecast(logedInUser, presentableCodecats, codecast);
		}
		return presentableCodecats;
	}

	private void preparePresentationCodecast(User logedInUser, List<PresentableCodecast> presentableCodecats, Codecast codecast) {
		PresentableCodecast pc = new CodecastPresenter().doFormat(logedInUser, codecast);
		presentableCodecats.add(pc);
	}
}
