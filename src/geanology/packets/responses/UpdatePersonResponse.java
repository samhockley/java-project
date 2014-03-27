package geanology.packets.responses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import geanology.Person;
import geanology.packets.Packet;

//this includes updating a person's relatives etc.
public class UpdatePersonResponse extends Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Person updatedPerson;

	public UpdatePersonResponse(Person updatedPerson) {
		this.updatedPerson = updatedPerson;
	}

	public Person getUpdatedPerson() {
		return updatedPerson;
	}
	
	public void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		aInputStream.defaultReadObject();
	}
	
	public void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.defaultWriteObject();
	}
}