package siarhei.pashkou.html;

public class ParsedRequest {
	public String method = "";
	public String path = "";

	public ParsedRequest(String method, String path) {
		this.method = method;
		this.path = path;
	}
	
	public ParsedRequest(){};
	
	@Override
	public boolean equals(Object o){
		ParsedRequest request = (ParsedRequest)o;
		if((path != null) && (path.equalsIgnoreCase(request.path))
				&& (method != null) && (method.equalsIgnoreCase(request.method)))
			return true;
		return false;
	}

}
