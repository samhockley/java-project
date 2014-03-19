package geanology.client;

import geanology.packets.Packet;
import geanology.packets.Person;
import geanology.packets.requests.*;
import geanology.packets.responses.AddPersonResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

// The client needs to send a packet and be able to receive a packet
public class GenealogyClient {

	public static void main(String args[]) {
		//This tests a fake client
		Packet testAddPersonPacket = new AddPersonRequest(new Person());
		
		Packet response = getResponseForRequest(testAddPersonPacket);// null because we've not made a request yet
		
		if(response instanceof AddPersonResponse) {
			System.out.println("[Client] Received an AddPersonResponse");
		}
		
	}

	public static Packet getResponseForRequest(Packet request) {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 5678);// localhost because
													// we're connecting
													// to the local
													// computer and 5678
													// because this is
													// the port number
													// of the server

			ObjectOutputStream outputStream = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream inputStream = new ObjectInputStream(
					socket.getInputStream());

			// send the request object to the server
			outputStream.writeObject(request);

			// get our response packet from the server
			Packet response = (Packet) inputStream.readObject();

			socket.close();
			
			// return this response packet
			return response;

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;// if everything fails, return null
	}

	public GenealogyClient() {
		// TODO Auto-generated constructor stub
	}
}