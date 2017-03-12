package siarhei.pashkou.html;

import static org.junit.Assert.*;

import org.junit.Test;

public class RequestParserTest {

	@Test
	public void emptyRequest() {
		RequestParser requestParser = new RequestParser();
		ParsedRequest parsedRequest = requestParser.parse("");
		assertEquals("", parsedRequest.method);
		assertEquals("", parsedRequest.path);
	}

	@Test
	public void nullRequest() {
		RequestParser requestParser = new RequestParser();
		ParsedRequest parsedRequest = requestParser.parse(null);
		assertEquals("", parsedRequest.method);
		assertEquals("", parsedRequest.path);
	}
	
	@Test
	public void requestNonEmpltyRequest(){
		RequestParser requestParser = new RequestParser();
		ParsedRequest parsedRequest = requestParser.parse("GET /foo/bar HTTP/1.1");
		assertEquals("GET", parsedRequest.method);
		assertEquals("/foo/bar", parsedRequest.path);
	}
	
	@Test
	public void partialRequst(){
		RequestParser requestParser = new RequestParser();
		ParsedRequest parsedRequest = requestParser.parse("GET");
		assertEquals("GET", parsedRequest.method);
		assertEquals("", parsedRequest.path);
	}

}
