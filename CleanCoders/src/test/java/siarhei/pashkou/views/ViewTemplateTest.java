package siarhei.pashkou.views;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ViewTemplateTest {

	@Test
	public void noReplacement() {
		ViewTemplate viewTemplate = new ViewTemplate("some static content");
		assertEquals("some static content", viewTemplate.getContent());
	}
	
	@Test
	public void simpleReplacement(){
		ViewTemplate viewTemplate = new ViewTemplate("replace ${this}");
		viewTemplate.replace("this", "replacement");
		assertEquals("replace replacement", viewTemplate.getContent());
	}
	
	

}
