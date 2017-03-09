package siarhei.pashkou.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import siarhei.pashkou.socketservice.SocketService;

public class ConnectionHandler implements Runnable{
	private ServerSocket sSocket;
	private boolean running;
	private ExecutorService executorService;
	private SocketService service;

	public ConnectionHandler(ServerSocket sSocket ,SocketService service, ExecutorService executorService, boolean running) {
		this.sSocket = sSocket;
		this.setRunning(running);
		this.executorService = executorService;
		this.setService(service);
	}

	@Override
	public void run() {
		try {
			while(isRunning()){
				Socket socket = sSocket.accept();
				executorService.execute(() -> getService().serve(socket));
			}
		} catch (IOException e) {
			if(isRunning())
				e.printStackTrace();
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public SocketService getService() {
		return service;
	}

	public void setService(SocketService service) {
		this.service = service;
	}

}
