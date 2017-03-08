package siarhei.pashkou.socketservice;

import java.io.IOException;
import java.net.Socket;

public class ClosingSocketService extends SocketServiceTest implements SocketService {

	protected void doServe(Socket s) throws IOException{
		connections++;
	}
}
