package siarhei.pashkou.socketservice;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import siarhei.pashkou.socketserver.SocketServer;

public class HtmlSocketService implements SocketService {

	public static void main(String[] args){
		try {
			SocketService htmlService = new HtmlSocketService();
			SocketServer server = new SocketServer(8080, htmlService);
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public int getConnections() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void serve(Socket s) {
		OutputStream os;
		try {
			os = s.getOutputStream();
			String response = "HTTP/1.0 200 OK\n"+
								"Content-Length: 21\n"+
								"\n"+
								"<h1>Hello, world</h1>";
			os.write(response.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
