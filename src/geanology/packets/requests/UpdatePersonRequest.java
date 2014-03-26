package geanology.packets.requests;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import geanology.Person;
import geanology.packets.Packet;

public class UpdatePersonRequest extends Packet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2467665459652325503L;
	
	private final Person updateOfPerson;

	public UpdatePersonRequest(Person updateOfPerson) {
		this.updateOfPerson = updateOfPerson;
	}

	public Person getPersonUpdate() {
		return updateOfPerson;
	}
	
	public void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		aInputStream.defaultReadObject();
	}
	
	public void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.defaultWriteObject();
	}
}