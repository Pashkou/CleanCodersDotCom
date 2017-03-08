package siarhei.pashkou.sockerserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class FackeSocketService implements SocketService {
	private int connections = 0;
	private String message;
	
	@Override
	public int getConnections() {
		return connections;
	}

	@Override
	public void server(Socket s) {
		connections++;
		try {
			InputStream is = s.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			message = br.readLine();	
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getMessage() {
		return message;
	}
}
