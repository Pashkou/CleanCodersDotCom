package siarhei.pashkou.usecases;

import java.util.ArrayList;
import java.util.List;

import siarhei.pashkou.context.Context;
import siarhei.pashkou.context.PresentableCodecast;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;

public class PresentCodecastUseCase extends CodecastUseCase {

	public List<PresentableCodecast> presentCodecasts(User logedInUser) {
		List<Codecast> codecasts = Context.codecastGateway.findAllCodecastsSortedByDate();
		List<PresentableCodecast> presentableCodecats = new ArrayList<PresentableCodecast>();
		for(Codecast codecast:codecasts){
			preparePresentationCodecast(logedInUser, presentableCodecats, codecast);
		}
		return presentableCodecats;
	}

	private void preparePresentationCodecast(User logedInUser, List<PresentableCodecast> presentableCodecats, Codecast codecast) {
		PresentableCodecast pc = doFormat(logedInUser, codecast);
		presentableCodecats.add(pc);
	}
}
