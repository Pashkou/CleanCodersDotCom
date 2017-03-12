package siarhei.pashkou.html;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RouterTest {
	private ParsedRequest actualRequest;
	private Router router;
	
	@Before
	public void setUp(){
		router = new Router();
	}

	@Test
	public void simplePath() {
		router.addPath("it", new TestController());
		
		ParsedRequest request = new ParsedRequest("GET", "/it");
		router.route(request);
		
		assertEquals(actualRequest, request);
	}

	@Test
	public void complocatedPath() {
		router.addPath("a", new TestController());
		
		ParsedRequest request = new ParsedRequest("GET", "/a/b/c");
		router.route(request);
		
		assertEquals(actualRequest, request);
	}

	@Test
	public void rootPath() {
		router.addPath("", new TestController());
		
		ParsedRequest request = new ParsedRequest("GET", "/");
		router.route(request);
		
		assertEquals(actualRequest, request);
	}
	
	@Test
	public void For04(){
		String result = router.route(new ParsedRequest("GET", "somthing-missing"));
		assertEquals("HTTP/1.1 404 OK\n", result);
	}
	class TestController extends Controller{
		@Override
		public String handle(ParsedRequest request) {
			actualRequest = request;
			return "";
		}
	}

}
