package geanology.packets.requests;

import geanology.packets.Packet;
import geanology.packets.Person;

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
}