package siarhei.pashkou.usecases;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.context.Context;
import siarhei.pashkou.context.PresentableCodecastDetails;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;

public class CodecastDetailsUseCaseTest {

	private User user;
	@Before
	public void setUp(){
		ContextSetup.initializeContext();
		user = Context.userGateway.save("Sergei");
	}
	@Test
	public void createCodecastDetailsPresentation() {
		Codecast codecast = new Codecast();
		codecast.setTitle("title");
		codecast.setPermalink("episode1-link");
		codecast.setPublished(LocalDate.now());
		codecast = Context.codecastGateway.saveCodecast(codecast);
		CodecastDetailsUseCase codecastDetailsUseCase = new CodecastDetailsUseCase();
		PresentableCodecastDetails codecastDetailsPresenter = codecastDetailsUseCase.requestCodecastDetails(user, codecast.getPermalink());
	
		assertEquals(codecast.getTitle(), codecastDetailsPresenter.presentableCodecast.title);
		assertEquals(codecast.getPublishedDate().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")).toString(), codecastDetailsPresenter.presentableCodecast.publishedDate.toString());
		assertEquals(codecast.getPermalink(), codecastDetailsPresenter.permalink);
	}

}
