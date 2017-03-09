package siarhei.pashkou.sockerserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import siarhei.pashkou.socketserver.SocketServer;
import siarhei.pashkou.socketservice.ClosingSocketService;
import siarhei.pashkou.socketservice.EchoSocketService;
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
		clientSocket.close();
		assertEquals("Hello", socketService.getMessage());
	}

	@Test
	public void canSendAndRecieveEchoData() throws IOException, InterruptedException{
		socketService = new EchoSocketService(); 
		socketServer.setService(socketService);
		socketServer.start();
		Socket clientSocket = new Socket("localhost", port);
		clientSocket.getOutputStream().write("Hello\n".getBytes());
		synchronized (socketService) { socketService.wait(); }
		InputStream is = clientSocket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String echoLine = br.readLine();
		socketServer.stop();
		clientSocket.close();
		assertEquals("Hello", echoLine);
	}
	
}
