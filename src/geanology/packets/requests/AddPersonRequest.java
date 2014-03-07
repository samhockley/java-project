package geanology.packets.requests;

import geanology.packets.Packet;
import geanology.packets.Person;

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
}