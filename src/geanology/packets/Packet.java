package geanology.packets;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Packet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -200190996737105460L;

	/**
	* Always treat de-serialization as a full-blown constructor, by validating
	* the final state of the de-serialized object.
	*/
	private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
	    aInputStream.defaultReadObject();
	}
	 
	/**
	* This is the default implementation of writeObject. Customise if
	* necessary.
	*/
	private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
	    aOutputStream.defaultWriteObject();
	}
}
