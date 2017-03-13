package siarhei.pashkou.html;

import siarhei.pashkou.model.User;

public abstract class Controller {
	public abstract String handle(ParsedRequest request, User user); 
	
	public String makeResponse(String frontPage) {
		String response = "HTTP/1.0 200 OK\n"+
				"Content-Length: "+frontPage.length()+"\n"+
				"\n"+
				frontPage;
		return response;
	}
}
