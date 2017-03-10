package siarhei.pashkou.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.model.Codecast;
import siarhei.pashkou.model.License;
import siarhei.pashkou.model.User;
import siarhei.pashkou.model.License.LicenseType;
import siarhei.pashkou.usecases.PresentCodecastUseCase;

public class PresentCodecastUseCaseTest {
	private PresentCodecastUseCase useCase;
	private User firstUser;
	private Codecast codecast;
	
	@Before
	public void setUp(){
		useCase = new PresentCodecastUseCase();
		ContextSetup.initializeContext();
		codecast = new Codecast();
		codecast = Context.codecastGateway.saveCodecast(codecast);
		firstUser = Context.userGateway.save("FirstUser");
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
		assertTrue(useCase.presentCodecasts(firstUser).isEmpty());
	}

	@Test
	public void presentOneCodecast(){
		codecast.setTitle("This is codecast");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		codecast.setPublished(LocalDate.parse("05/03/2017", formatter));
		
		Context.codecastGateway.saveCodecast(codecast);
		List<PresentableCodecast> pCodecasts = useCase.presentCodecasts(firstUser);
		assertEquals(1, pCodecasts.size());
		
		PresentableCodecast pCodecast = pCodecasts.get(0);
		assertEquals("This is codecast", pCodecast.title);
		assertEquals("05/03/2017", pCodecast.published);
		
	}

	@Test
	public void withNoLicenseShowCodecastsNoViewable(){
		 assertFalse(useCase.presentCodecasts(firstUser).get(0).isViewable);
	}

	@Test
	public void withLicenseShowCodecastsViewable(){
		License license = new License(LicenseType.VIEWABLE, firstUser, codecast);
		Context.licenseGateway.save(license);
		assertTrue(useCase.presentCodecasts(firstUser).get(0).isViewable);
	}


	
}
