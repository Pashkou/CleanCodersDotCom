package siarhei.pashkou.sockerserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siarhei.pashkou.socketserver.SocketServer;
import siarhei.pashkou.socketservice.ClosingSocketService;
import siarhei.pashkou.socketservice.ReadingSocketService;
import siarhei.pashkou.socketservice.SocketService;

public class SockerServerTest {

	private int port = 8042;
	private SocketService socketService;
	private SocketServer socketServer;

	@Before
	public void setUp() throws IOException{
		socketService = new ClosingSocketService();
		socketServer = new SocketServer(port , socketService);
	}
	
	@After
	public void tearDown() throws IOException, InterruptedException{
		socketServer.stop();
	}
	
	@Test
	public void instanciate(){
		assertEquals(port, socketServer.getPort());
		assertEquals(socketService, socketServer.getService());
	}
	
	@Test
	public void canStartAndStopServer() throws IOException, InterruptedException{
		socketServer.start();
		assertTrue(socketServer.isRunning());
		socketServer.stop();
		assertFalse(socketServer.isRunning());
	}
	
	@Test
	@SuppressWarnings("resource")
	public void acceptAnIncomingConnection() throws IOException, InterruptedException{
		socketServer.start();
		new Socket("localhost", port);
		synchronized (socketService) { socketService.wait(); }
		socketServer.stop();
		assertEquals(1, socketService.getConnections());
	}
	
	@Test
	@SuppressWarnings("resource")
	public void acceptAnMultipleConnection() throws IOException, InterruptedException{
		socketServer.start();
		new Socket("localhost", port);
		synchronized (socketService) { socketService.wait(); }
		new Socket("localhost", port);
		synchronized (socketService) { socketService.wait(); }
		new Socket("localhost", port);
		synchronized (socketService) { socketService.wait(); }
		new Socket("localhost", port);
		synchronized (socketService) { socketService.wait(); }
		socketServer.stop();
		assertEquals(4, socketService.getConnections());
	}
	
	@Test
	public void canSendAndRecieveData() throws IOException, InterruptedException{
		socketService = new ReadingSocketService(); 
		socketServer.setService(socketService);
		socketServer.start();
		Socket clientSocket = new Socket("localhost", port);
		clientSocket.getOutputStream().write("Hello\n".getBytes());
		synchronized (socketService) { socketService.wait(); }
		socketServer.stop();
		assertEquals("Hello", socketService.getMessage());
	}
	
}
