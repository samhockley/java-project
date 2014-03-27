package geanology.packets.responses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import geanology.Person;
import geanology.packets.Packet;

public class AddPersonResponse extends Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4595400682768282509L;
	
	private final Person addedPerson;

	public AddPersonResponse(Person addedPerson) {
		this.addedPerson = addedPerson;
	}

	public Person getPersonAdded() {
		return addedPerson;
	}
	
	public void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		aInputStream.defaultReadObject();
	}
	
	public void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.defaultWriteObject();
	}
}