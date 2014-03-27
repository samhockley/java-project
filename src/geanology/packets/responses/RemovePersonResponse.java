package geanology.packets.responses;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import geanology.packets.Packet;

public class RemovePersonResponse extends Packet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4595400682768282509L;

	public RemovePersonResponse() {
		
	}
	
	public void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		aInputStream.defaultReadObject();
	}
	
	public void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.defaultWriteObject();
	}
}
