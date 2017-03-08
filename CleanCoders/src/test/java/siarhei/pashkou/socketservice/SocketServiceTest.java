package siarhei.pashkou.socketservice;

import java.io.IOException;
import java.net.Socket;

public abstract class SocketServiceTest {

	protected int connections = 0;
	private String message;
	
	public int getConnections() {
		return connections;
	}

	public void serve(Socket s) {
		try {
			doServe(s);
			synchronized (this) { notify(); }
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected abstract void doServe(Socket s) throws IOException;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}