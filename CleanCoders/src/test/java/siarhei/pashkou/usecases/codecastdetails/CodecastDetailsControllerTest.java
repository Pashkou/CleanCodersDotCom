package siarhei.pashkou.usecases.codecastdetails;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.context.Context;
import siarhei.pashkou.html.ParsedRequest;
import siarhei.pashkou.usecases.CodecastDetailsViewModel;

public class CodecastDetailsControllerTest {
	private CodecastDetailsController codecastDetailsController;
	private CodecastDetailsInputBoundarySpy useCaseMock;
	private CodecastDetailsOutputBoundarySpy presenterMock;
	private CodecastDetailsViewSpy viewSpy;
	private ParsedRequest request;
	
	@Before
	public void setUp(){
		ContextSetup.initializeContext();
		useCaseMock = new CodecastDetailsInputBoundarySpy();
		presenterMock = new CodecastDetailsOutputBoundarySpy();
		viewSpy = new CodecastDetailsViewSpy();
		codecastDetailsController = new CodecastDetailsController(useCaseMock, presenterMock, viewSpy);
		request = new ParsedRequest("GET", "doesnot matter");
	}
	
	@Test
	public void testInputBoundaryInvocation() {
		ParsedRequest request = new ParsedRequest();
		codecastDetailsController.handle(request, Context.gateKepper.getLogedInUser());  			   //invokes controller       
		
		assertTrue(useCaseMock.detailedCodecastsWasCalled);											
		assertEquals(Context.gateKepper.getLogedInUser(), useCaseMock.requestedUser); //check if useCase method was called			
		assertSame(presenterMock, useCaseMock.outputBoundary);						  //makes sure that the controller passes the presenter to the usecase					
	}
	
	@Test
	public void controllerSendViewModelToView(){
		presenterMock.viewModel = new CodecastDetailsViewModel();
		codecastDetailsController.handle(request, Context.gateKepper.getLogedInUser());  	
		
		assertTrue(viewSpy.generateViewWasCalled);
		assertSame(presenterMock.viewModel, viewSpy.viewModel);
	}

}
