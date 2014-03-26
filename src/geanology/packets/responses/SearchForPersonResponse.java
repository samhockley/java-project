package geanology.packets.responses;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import geanology.Person;
import geanology.packets.Packet;

public class SearchForPersonResponse extends Packet {

	//This is an Array of possible people, from the search
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Person> searchPersonResult = new ArrayList<Person>();
		
	public ArrayList<Person> getSearchPersonResult() {
		return searchPersonResult;
	}
		
	public SearchForPersonResponse() {
		
	}
	
	public void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		aInputStream.defaultReadObject();
	}
	
	public void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.defaultWriteObject();
	}
}