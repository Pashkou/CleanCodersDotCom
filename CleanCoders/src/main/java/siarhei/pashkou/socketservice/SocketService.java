package siarhei.pashkou.sockerserver;

import java.net.Socket;

public interface SocketService {
	int getConnections();
	void server(Socket s);
	String getMessage();
}
