package siarhei.pashkou.socketservice;

import java.net.Socket;

public interface SocketService {
	int getConnections();
	String getMessage();
	void serve(Socket s);
}
