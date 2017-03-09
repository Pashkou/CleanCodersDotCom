package siarhei.pashkou.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import siarhei.pashkou.socketservice.SocketService;

public class SocketServer {
	private int port;
	private ServerSocket sSocket;
	private ExecutorService executorService;
	private ConnectionHandler connectionHandler;
	
	public SocketServer(int port, SocketService service) throws IOException {
		this.setPort(port);
		sSocket = new ServerSocket(port);
		executorService = Executors.newFixedThreadPool(4);
		connectionHandler = new ConnectionHandler(sSocket, service, executorService, false);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setService(SocketService service) {
		connectionHandler.setService(service);
	}

	public void start() throws IOException {
		connectionHandler.setRunning(true);
		executorService.execute(connectionHandler);
	}

	public void stop() throws IOException, InterruptedException {
		sSocket.close();
		connectionHandler.setRunning(false);
		executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
		executorService.shutdown();
	}

	public boolean isRunning() {
		return connectionHandler.isRunning();
	}

}
