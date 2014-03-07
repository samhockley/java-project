package geanology.packets.requests;

import geanology.packets.Packet;
import geanology.packets.Person;

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
}