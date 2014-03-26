package geanology.packets.requests;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import geanology.Person;
import geanology.packets.Packet;

public class AddPersonRequest extends Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4595400682768282509L;
	
	private final Person addPerson;

	public AddPersonRequest(Person addPerson) {
		this.addPerson = addPerson;
	}

	public Person getPersonToBeAdded() {
		return addPerson;
	}
	
	public void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		aInputStream.defaultReadObject();
	}
	
	public void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.defaultWriteObject();
	}
}