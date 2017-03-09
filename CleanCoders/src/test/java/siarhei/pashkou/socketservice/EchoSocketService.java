package siarhei.pashkou.socketservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class EchoSocketService extends SocketServiceTest implements SocketService {
	protected void doServe(Socket s) throws IOException{
		InputStream is = s.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		setMessage(br.readLine());	
		OutputStream os = s.getOutputStream();
		os.write(getMessage().getBytes());
		os.flush();
	}
}
