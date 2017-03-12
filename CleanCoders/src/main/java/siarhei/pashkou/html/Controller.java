package siarhei.pashkou.html;

public abstract class Controller {
	public abstract String handle(ParsedRequest request); 
	
	public String makeResponse(String frontPage) {
		String response = "HTTP/1.0 200 OK\n"+
				"Content-Length: "+frontPage.length()+"\n"+
				"\n"+
				frontPage;
		return response;
	}
}
