package geanology.packets.responses;

import geanology.packets.Packet;
import geanology.packets.Person;

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
}