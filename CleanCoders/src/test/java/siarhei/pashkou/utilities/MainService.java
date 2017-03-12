package siarhei.pashkou.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.html.ParsedRequest;
import siarhei.pashkou.html.RequestParser;
import siarhei.pashkou.html.Router;
import siarhei.pashkou.socketservice.SocketService;
import siarhei.pashkou.usecases.codecastsummary.CodecastSummaryController;

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
		Router router = new Router();
		router.addPath("", new CodecastSummaryController());
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
			ParsedRequest request = new RequestParser().parse(reader.readLine());
			String response = router.route(request);
			s.getOutputStream().write(response.getBytes());
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String getMessage() {
		return null;
	}
}
