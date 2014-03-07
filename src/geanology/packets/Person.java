package geanology.packets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//This Person class needs to be filled out (first name, etc.)
//this implements Serializable so that it can be converted into bytes to be sent
public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1801107241608319486L;

	public Person() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Always treat de-serialization as a full-blown constructor, by validating
	 * the final state of the de-serialized object.
	 */
	private void readObject(ObjectInputStream aInputStream)
			throws ClassNotFoundException, IOException {
		aInputStream.defaultReadObject();
	}

	/**
	 * This is the default implementation of writeObject. Customise if
	 * necessary.
	 */
	private void writeObject(ObjectOutputStream aOutputStream)
			throws IOException {
		aOutputStream.defaultWriteObject();
	}
}