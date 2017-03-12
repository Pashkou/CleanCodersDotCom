package siarhei.pashkou.html;

import java.util.HashMap;
import java.util.Map;

public class Router {
	private Map<String, Controller> routers = new HashMap();

	public void addPath(String path, Controller controller) {
		routers.put(path, controller);
	}

	public String route(ParsedRequest parsedRequest) {
		String[] paths = parsedRequest.path.split("/");
		String controllerKey = paths.length > 1 ? paths[1] : "";
		Controller controller = routers.get(controllerKey);
		return controller == null ? "HTTP/1.1 404 OK\n" : controller.handle(parsedRequest);
	}


}
