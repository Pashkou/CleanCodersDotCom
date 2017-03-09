package siarhei.pashkou.views;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ViewTemplate {
	private String content;
	public ViewTemplate(String inputContent) {
		content = inputContent;
	}
	public String getContent() {
		return content;
	}
	public void replace(String tagToReplace, String newTagValue) {
		content = content.replace("${" + tagToReplace + "}", newTagValue);
	}
	public static ViewTemplate create(String resourcePath) throws IOException {
		URL frontPageURL = ClassLoader.getSystemResource(resourcePath);
		byte[] frontPageBytes = Files.readAllBytes( Paths.get(frontPageURL.getPath().replaceFirst("^/(.:/)", "$1")));
		ViewTemplate frontPageTemplate = new ViewTemplate(new String(frontPageBytes));
		return frontPageTemplate;
	}
}
