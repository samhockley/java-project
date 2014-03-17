package geanology.server;

import geanology.packets.Packet;
import geanology.packets.Person;
import geanology.packets.requests.AddPersonRequest;
import geanology.packets.responses.AddPersonResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

//this is a server-side client that connects to the Client
//this sends responses, whereas GenealogyClient sends requests
public class ServerClient implements Runnable {

	// this socket connects to one client instance
	// final because it should never change
	private final Socket socket;

	public ServerClient(Socket socket) {
		System.out.println("[Server] New ServerClient object created!");

		this.socket = socket;
	}

	// put the executable thread code here
	@Override
	public void run() {
		// this gets the object streams:
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(
					socket.getOutputStream());// this creates a new object
												// output stream from the normal
												// output stream
			ObjectInputStream inputStream = new ObjectInputStream(
					socket.getInputStream());// this creates a new input stream

			Packet request = (Packet) inputStream.readObject();

			Packet response = getResponseForRequestFromDatabase(request);

			outputStream.writeObject(response);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//This is not yet fully implemented with the database
	private Packet getResponseForRequestFromDatabase(Packet request) {
		if (request instanceof AddPersonRequest) {
			
			System.out.println("[Server] Received an AddPersonRequest");

			// we're casting it from a packet to an add person request
			AddPersonRequest theRequest = (AddPersonRequest) request;

			Person personToBeAdded = theRequest.getPersonToBeAdded();

			// this is where we need to interact with the database and add this
			// person

			AddPersonResponse response = new AddPersonResponse(personToBeAdded);

			return response;
		}
		if (request instanceof RemovePersonRequest) {
			
			System.out.println("[Server] Received a RemovePersonRequest");

			// we're casting it from a packet to an add person request
			RemovePersonRequest theRequest = (RemovePersonRequest) request;

			Person personToBeRemoved = theRequest.getPersonToBeRemoved();

			// this is where we need to interact with the database and
			// return a RemovePersonResponse with no arguments

			RemovePersonResponses response = new RemovePersonResponses();

			return response;
		}
		
		if (request instanceof UpdatePersonRequest) {
	
	System.out.println("[Server] Received a UpdatePersonRequest");

	// we're casting it from a packet to an update person request
	UpdatePersonRequest theRequest = (UpdatePersonRequest) request;

	Person personToBeUpdated = theRequest.getPersonUpdate();

	// this is where we need to interact with the database and
	// return an UpdatePersonResponse 

	UpdatePersonResponse response = new UpdatePersonResponse(personToBeUpdated);

	return response;
}
		return null;
	}

}
