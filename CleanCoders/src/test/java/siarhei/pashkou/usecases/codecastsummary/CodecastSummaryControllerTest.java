package siarhei.pashkou.usecases.codecastsummary;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.context.Context;
import siarhei.pashkou.html.ParsedRequest;

public class CodecastSummaryControllerTest {

	private CodecastSummaryInputBoundarySpy useCaseMock;
	private CodecastSummaryOutputBoundarySpy presenterMock;
	private CodecastSummaryController codecastSummaryController;
	private CodecastSummaryViewSpy viewSpy;
	private ParsedRequest request;


	@Before
	public void setUp(){
		ContextSetup.setupSampleData();
		useCaseMock = new CodecastSummaryInputBoundarySpy();
		presenterMock = new CodecastSummaryOutputBoundarySpy();
		viewSpy = new CodecastSummaryViewSpy();
		codecastSummaryController = new CodecastSummaryController(useCaseMock, presenterMock, viewSpy);
		request = new ParsedRequest("GET", "doesnot matter");
		
	}
	
	@Test
	public void testInputBoundaryInvocation() {
		codecastSummaryController.handle(request, Context.gateKepper.getLogedInUser());  			   //invokes controller       
		
		assertTrue(useCaseMock.summarizeCodecastsWasCalled);											//check if useCase method was called
		assertEquals(Context.gateKepper.getLogedInUser(), useCaseMock.requestedUser);					//check if useCase was called with the right params
		assertSame(presenterMock, useCaseMock.outputBoundary);											//makes sure that the controller passes the presenter to the usecase
	}

	
	@Test
	public void controllerSendViewModelToView(){
		presenterMock.viewModel = new CodecastSummariesViewModel();
		codecastSummaryController.handle(request, Context.gateKepper.getLogedInUser());  	
		
		assertTrue(viewSpy.generateViewWasCalled);
		assertSame(presenterMock.viewModel, viewSpy.viewModel);
	}
}
