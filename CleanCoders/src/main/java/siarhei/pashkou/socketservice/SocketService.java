package siarhei.pashkou.socketservice;

import java.net.Socket;

public interface SocketService {
	int getConnections();
	void serve(Socket s);
	String getMessage();
}
