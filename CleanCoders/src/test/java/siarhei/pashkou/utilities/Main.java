package siarhei.pashkou.utilities;

import java.io.IOException;

import siarhei.pashkou.codecast.ContextSetup;
import siarhei.pashkou.socketserver.SocketServer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Main main = new Main();
	}
	
	public Main() throws IOException{
		SocketServer socketServer = new SocketServer(8080, new MainService());
		socketServer.start();
	}

}
