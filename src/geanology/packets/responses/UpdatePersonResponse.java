package geanology.packets.responses;

import geanology.packets.Packet;
import geanology.packets.Person;

//this includes updating a person's relatives etc.
public class UpdatePersonResponse extends Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Person updatedPerson;

	public UpdatePersonResponse(Person updatedPerson) {
		this.updatedPerson = updatedPerson;
	}

	public Person getUpdatedPerson() {
		return updatedPerson;
	}
}