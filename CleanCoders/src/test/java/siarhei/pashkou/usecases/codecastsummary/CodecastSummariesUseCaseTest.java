package siarhei.pashkou.usecases.codecastsummary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.context.Context;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.License;
import siarhei.pashkou.model.License.LicenseType;
import siarhei.pashkou.model.User;
import siarhei.pashkou.usecases.RequestModel;

public class CodecastSummariesUseCaseTest {
	private CodecastSummaryUseCase useCase;
	private User firstUser;
	private Codecast codecast;
	private CodecastSummaryOutputBoundarySpy presenterSpy;
	private RequestModel requestModel;
	
	@Before
	public void setUp(){
		useCase = new CodecastSummaryUseCase();
		ContextSetup.initializeContext();
		codecast = new Codecast();
		codecast = Context.codecastGateway.saveCodecast(codecast);
		firstUser = Context.userGateway.save("FirstUser");
		presenterSpy = new CodecastSummaryOutputBoundarySpy();
		requestModel = new RequestModel();
		requestModel.logedInUser = firstUser;
	}
	
	@Test
	public void userWithoutViewLicense_canNotShowCodecast(){
		assertFalse(useCase.isLincenseForCodecast(LicenseType.VIEWABLE, firstUser, codecast));
		
	}
	
	@Test
	public void userWithViewLicense_canViewCodecast() {
		License license = new License(LicenseType.VIEWABLE, firstUser, codecast);
		Context.licenseGateway.save(license);
		assertTrue(useCase.isLincenseForCodecast(LicenseType.VIEWABLE, firstUser, codecast));
	}

	@Test
	public void userWithDownloadLicense_canDownloadCodecast() {
		License license = new License(LicenseType.DOWNLOADABLE, firstUser, codecast);
		Context.licenseGateway.save(license);
		assertTrue(useCase.isLincenseForCodecast(LicenseType.DOWNLOADABLE, firstUser, codecast));
	}

	@Test
	public void userWithViewableLicense_cannotDownloadCodecast() {
		License license = new License(LicenseType.VIEWABLE, firstUser, codecast);
		Context.licenseGateway.save(license);
		assertFalse(useCase.isLincenseForCodecast(LicenseType.DOWNLOADABLE, firstUser, codecast));
	}
	
	@Test
	public void userWithDownloadableLicense_cannotViewCodecast() {
		License license = new License(LicenseType.DOWNLOADABLE, firstUser, codecast);
		Context.licenseGateway.save(license);
		assertFalse(useCase.isLincenseForCodecast(LicenseType.VIEWABLE, firstUser, codecast));
	}
	
	@Test
	public void userWithoutViewLicense_canNotViewOtherCodecast(){
		User otherUser = Context.userGateway.save("otherUser");
		License license = new License(LicenseType.VIEWABLE,firstUser, codecast);
		Context.licenseGateway.save(license);
		assertFalse(useCase.isLincenseForCodecast(LicenseType.VIEWABLE, otherUser, codecast));
	}
	
	@Test
	public void userWithWrongViewLicense_canNotShowCodecast(){
		License license = new License(LicenseType.VIEWABLE,firstUser, codecast);
		Context.licenseGateway.save(license);
		User otherUser = Context.userGateway.save("otherUser");
		Codecast codecast2 = new Codecast();
		License license2 = new License(LicenseType.VIEWABLE,otherUser, codecast2);
		Context.licenseGateway.save(license2);
		assertFalse(useCase.isLincenseForCodecast(LicenseType.VIEWABLE, firstUser, codecast2));
	}
	
	@Test
	public void presentNoCodecasts(){
		Context.codecastGateway.delete(codecast);
		useCase.execute(requestModel, presenterSpy); 
		assertTrue(presenterSpy.responseModel.getCodecastSummaries().isEmpty());
	}

	@Test
	public void presentOneCodecast(){
		codecast.setTitle("This is codecast");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate now = LocalDate.parse("05/03/2017", formatter);
		codecast.setPublished(now);
		codecast.setPermalink("permalink");
		Context.codecastGateway.saveCodecast(codecast);
		
		useCase.execute(requestModel, presenterSpy); 
		
		assertEquals(1, presenterSpy.responseModel.getCodecastSummaries().size());
		CodecastSummary codecastSummary = presenterSpy.responseModel.getCodecastSummaries().get(0);
		assertEquals("This is codecast", codecastSummary.title);
		assertEquals(now, codecastSummary.publishedDate);
		assertEquals("permalink", codecastSummary.permalink);
		 
	}

	@Test
	public void withNoLicenseShowCodecastsNoViewable(){
		useCase.execute(requestModel, presenterSpy); 
		
		assertFalse(presenterSpy.responseModel.getCodecastSummaries().get(0).isViewable);
	}

	@Test
	public void withLicenseShowCodecastsViewable(){
		License license = new License(LicenseType.VIEWABLE, firstUser, codecast);
		Context.licenseGateway.save(license);
		useCase.execute(requestModel, presenterSpy); 
		assertTrue(presenterSpy.responseModel.getCodecastSummaries().get(0).isViewable);
		assertFalse(presenterSpy.responseModel.getCodecastSummaries().get(0).isDownloadable);
	}

	@Test
	public void withLicenseDownloadCodecastsDownloadable(){
		License license = new License(LicenseType.DOWNLOADABLE, firstUser, codecast);
		Context.licenseGateway.save(license);
		useCase.execute(requestModel, presenterSpy); 
		assertTrue(presenterSpy.responseModel.getCodecastSummaries().get(0).isDownloadable);
		assertFalse(presenterSpy.responseModel.getCodecastSummaries().get(0).isViewable);
	}

	@Test
	public void useCaseWiring(){
		CodecastSummaryOutputBoundarySpy presenterSpy = new CodecastSummaryOutputBoundarySpy();
		useCase.execute(requestModel, presenterSpy); //firstUse is DTO in this case
		assertNotNull(presenterSpy.responseModel);
	}

	
}
