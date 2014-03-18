package geanology.server;

import java.io.IOException;
import java.net.*;

public class Server {
	// Set this to false to stop the Server and shut down
		public boolean listening = true;
	
	// We need to start listening for client requests:

	public Server() {
		
		
		ServerSocket serverSocket = null; // we set this below
		try {
			serverSocket = new ServerSocket(5678);// at port 5678
			System.out.println("[Server] Server running!");
			while (listening) {
				Socket socket = serverSocket.accept(); // Waiting for a client
														// to
														// connect. When they do
														// try and connect, the
														// method will return a
														// socket object.
				System.out.println("[Server] Someone connected!");
				ServerClient newClient = new ServerClient(socket); 
				new Thread(newClient).start();//this will start the new thread
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Server();
	}
}