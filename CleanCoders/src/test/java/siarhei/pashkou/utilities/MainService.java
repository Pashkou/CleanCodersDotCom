package siarhei.pashkou.utilities;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.context.Context;
import siarhei.pashkou.context.PresentableCodecast;
import siarhei.pashkou.socketservice.SocketService;
import siarhei.pashkou.usecases.PresentCodecastUseCase;
import siarhei.pashkou.views.ViewTemplate;

public class MainService implements SocketService {

	public MainService(){
		ContextSetup.initializeContext();
		ContextSetup.setupSampleData();
	}
	@Override
	public int getConnections() {
		return 0;
	}

	@Override
	public void serve(Socket s) {
		try {
			String frontPage = getFrontPage();
			String response = makeResponse(frontPage);
			s.getOutputStream().write(response.getBytes());
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private String makeResponse(String frontPage) {
		String response = "HTTP/1.0 200 OK\n"+
				"Content-Length: "+frontPage.length()+"\n"+
				"\n"+
				frontPage;
		return response;
	}

	private String getFrontPage() throws IOException {
		PresentCodecastUseCase useCase = new PresentCodecastUseCase();
		List<PresentableCodecast> codecasts = useCase.presentCodecasts(Context.userGateway.findUserByName("Sergei"));
		ViewTemplate frontPageTemplate = ViewTemplate.create("html/frontPage.html");
		
		StringBuilder codecastView = new StringBuilder();
		for(PresentableCodecast codecast:codecasts){
			ViewTemplate codecastTemplate = ViewTemplate.create("html/codecast.html");
			codecastTemplate.replace("title", codecast.title);	
			codecastTemplate.replace("publicationDate", codecast.publishedDate);	
			codecastView.append(codecastTemplate.getContent());
		}
		
		frontPageTemplate.replace("codecasts", codecastView.toString());
		return frontPageTemplate.getContent();
	}

	@Override
	public String getMessage() {
		return null;
	}

}
