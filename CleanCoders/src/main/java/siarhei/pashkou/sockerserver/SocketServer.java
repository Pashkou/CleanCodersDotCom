package siarhei.pashkou.sockerserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SocketServer {
	private int port;
	private SocketService service;
	private ServerSocket sSocket;
	private boolean running;
	private ExecutorService executorService;
	
	public SocketServer(int port, SocketService service) throws IOException {
		this.setPort(port);
		this.setService(service);
		sSocket = new ServerSocket(port);
		executorService = Executors.newFixedThreadPool(4);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public SocketService getService() {
		return service;
	}

	public void setService(SocketService service) {
		this.service = service;
	}

	public void start() throws IOException {
		Runnable connectionHandler = new Runnable() {
			@Override
			public void run() {
				try {
					Socket socket = sSocket.accept();
					service.server(socket);
				} catch (IOException e) {
					if(running)
						e.printStackTrace();
				}
			}
		};
		executorService.execute(connectionHandler);
		running = true;
	}

	public void stop() throws IOException, InterruptedException {
		executorService.shutdown();
		executorService.awaitTermination(200, TimeUnit.MILLISECONDS);
		sSocket.close();
		running = false;
	}

	public boolean isRunning() {
		return running;
	}

}