package geanology.packets.requests;

import geanology.packets.Packet;
import geanology.packets.Person;

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
}