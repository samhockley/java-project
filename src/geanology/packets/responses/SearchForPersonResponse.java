package geanology.packets.responses;
import java.util.ArrayList;

import geanology.packets.Packet;
import geanology.packets.Person;

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
}