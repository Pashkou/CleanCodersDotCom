package siarhei.pashkou.html;

public class RequestParser {

	public ParsedRequest parse(String requestString) {
		ParsedRequest parsedRequest = new ParsedRequest();
		if(null != requestString){
			String[] parts = requestString.split(" ");
			if(parts.length >= 1)
				parsedRequest.method = parts[0];
			if(parts.length >= 2)
				parsedRequest.path = parts[1];
		}
		return parsedRequest; 
	}

}
