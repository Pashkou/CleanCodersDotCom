package siarhei.pashkou.usecases.codecastdetails;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.User;
import siarhei.pashkou.usecases.RequestModel;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummaryOutputBoundarySpy;

public class CodecastDetailsUseCaseTest {
	private User user;
	private Codecast selectedCodecast;
	
	@Before
	public void setUp(){
		ContextSetup.initializeContext();
		user = Context.userGateway.save("Sergei");
		selectedCodecast = createSampleDataCodecast();
	}
	
	private Codecast createSampleDataCodecast() {
		Codecast codecast = new Codecast();
		codecast.setTitle("title");
		codecast.setPermalink("episode1-link");
		codecast.setPublished(LocalDate.now());
		codecast = Context.codecastGateway.saveCodecast(codecast);
		return codecast;
	}
	
	@Test
	public void useCaseWiring(){
		CodecastDetailsOutputBoundarySpy presenterSpy = new CodecastDetailsOutputBoundarySpy();
		CodecastDetailsUseCase useCase = new CodecastDetailsUseCase();
		RequestModel requestModel = new RequestModel();
		requestModel.logedInUser = Context.gateKepper.getLogedInUser();
		requestModel.permalink = selectedCodecast.getPermalink();
		
		useCase.execute(requestModel, presenterSpy);
		
		assertNotNull(presenterSpy.codecastResponseModel);
	}
	
	@Test
	public void doesnotCrushOnMissingCodecast() {
		Codecast codecast = new Codecast();
		codecast.setTitle("title");
		codecast.setPermalink("episode1-link");
		codecast.setPublished(LocalDate.now());
		RequestModel requestModel = new RequestModel();
		requestModel.logedInUser = Context.gateKepper.getLogedInUser();
		requestModel.permalink = codecast.getPermalink();
		CodecastDetailsUseCase useCase = new CodecastDetailsUseCase();
		CodecastDetailsOutputBoundarySpy presenterSpy = new CodecastDetailsOutputBoundarySpy();
		
		useCase.execute(requestModel, presenterSpy);
		
		assertNotNull(presenterSpy);
	}

}
