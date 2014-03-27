package geanology.packets.requests;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import geanology.Person;
import geanology.packets.Packet;

public class SearchForPersonRequest extends Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1469917223317380818L;
	
	private final Person searchTerm;

	public SearchForPersonRequest(Person searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Person getSearchTerm() {
		return searchTerm;
	}
	
	public void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		aInputStream.defaultReadObject();
	}
	
	public void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.defaultWriteObject();
	}
}