package geanology.client;

import geanology.packets.Packet;
import geanology.packets.Person;
import geanology.packets.requests.*;
import geanology.packets.responses.AddPersonResponse;
import geanology.packets.responses.SearchForPersonResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

// The client needs to send a packet and be able to receive a packet
public class GenealogyClient {

	public static void main(String args[]) {
		// here we're creating a search term which only has the person ID set
		// (we're only searching by ID)
		Person searchTerm = new Person();
		searchTerm.setPerson_ID(1);
		
		Packet testSearchForPersonPacket = new SearchForPersonRequest(searchTerm);
		
		Packet response = getResponseForRequest(testSearchForPersonPacket);
		
		if(response instanceof SearchForPersonResponse) {
			System.out.println("[Client] Received a SearchForPersonResponse");
			ArrayList<Person> searchResults = ((SearchForPersonResponse) response).getSearchPersonResult();
			System.out.println("[Client] It has "+searchResults.size()+" results in it!");
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