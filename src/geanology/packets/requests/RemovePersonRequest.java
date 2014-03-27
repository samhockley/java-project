package geanology.packets.requests;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import geanology.Person;
import geanology.packets.Packet;

public class RemovePersonRequest extends Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8266759960698604713L;
	
	private final Person removePerson;

	public RemovePersonRequest(Person removePerson) {
		this.removePerson = removePerson;
	}

	public Person getPersonToBeRemoved() {
		return removePerson;
	}
	
	public void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		aInputStream.defaultReadObject();
	}
	
	public void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.defaultWriteObject();
	}
}