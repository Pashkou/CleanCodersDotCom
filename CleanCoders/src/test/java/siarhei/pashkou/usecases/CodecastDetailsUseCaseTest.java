package siarhei.pashkou.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;
import siarhei.pashkou.usecases.codecastdetails.CodecastDetailsUseCase;
import siarhei.pashkou.usecases.codecastdetails.PresentableCodecastDetailsViewModel;

public class CodecastDetailsUseCaseTest {
	private User user;
	
	@Before
	public void setUp(){
		ContextSetup.initializeContext();
		user = Context.userGateway.save("Sergei");
	}
	
	@Ignore
	@Test
	public void createCodecastDetailsPresentation() {
		Codecast codecast = createSampleDataCodecast();
		CodecastDetailsUseCase codecastDetailsUseCase = new CodecastDetailsUseCase();
		PresentableCodecastDetailsViewModel codecastDetailsPresenter = codecastDetailsUseCase.requestCodecastDetails(user, codecast.getPermalink());
		
		assertEquals(codecast.getTitle(), codecastDetailsPresenter.presentableCodecast.getCodecastSummaries().get(0).title);
		assertEquals(codecast.getPublishedDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")).toString(), codecastDetailsPresenter.presentableCodecast.getCodecastSummaries().get(0).publishedDate.toString());
		assertEquals(codecast.getPermalink(), codecastDetailsPresenter.presentableCodecast.getCodecastSummaries().get(0).permalink);
	}

	@Test
	public void doesnotCrushOnMissingCodecast() {
		CodecastDetailsUseCase codecastDetailsUseCase = new CodecastDetailsUseCase();
		PresentableCodecastDetailsViewModel codecastDetailsPresenter = codecastDetailsUseCase.requestCodecastDetails(user, "missing");
		
		assertFalse(codecastDetailsPresenter.wasFound);
	}

	private Codecast createSampleDataCodecast() {
		Codecast codecast = new Codecast();
		codecast.setTitle("title");
		codecast.setPermalink("episode1-link");
		codecast.setPublished(LocalDate.now());
		codecast = Context.codecastGateway.saveCodecast(codecast);
		return codecast;
	}

}
