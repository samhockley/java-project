ppackage geanology.packets;

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
	private int person_id;
	private String first_name;
	private String last_name;
	private int date_of_birth;
	private String place_of_birth;
	private int mother_id;
	private int father_id;
	private int child_id;
	


	public Person(int person_id, String first_name, String last_name,
			int date_of_birth, String place_of_birth, int mother_id,
			int father_id, int child_id) {
		super();
		this.person_id = person_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.date_of_birth = date_of_birth;
		this.place_of_birth = place_of_birth;
		this.mother_id = mother_id;
		this.father_id = father_id;
		this.child_id = child_id;
	
// TODO Auto-generated constructor stub
	}


	

	public int getPerson_id() {
		return person_id;
	}




	public String getFirst_name() {
		return first_name;
	}




	public String getLast_name() {
		return last_name;
	}




	public int getDate_of_birth() {
		return date_of_birth;
	}




	public String getPlace_of_birth() {
		return place_of_birth;
	}




	public int getMother_id() {
		return mother_id;
	}




	public int getFather_id() {
		return father_id;
	}




	public int getChild_id() {
		return child_id;
	}




	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}




	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}




	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}




	public void setDate_of_birth(int date_of_birth) {
		this.date_of_birth = date_of_birth;
	}




	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}




	public void setMother_id(int mother_id) {
		this.mother_id = mother_id;
	}




	public void setFather_id(int father_id) {
		this.father_id = father_id;
	}




	public void setChild_id(int child_id) {
		this.child_id = child_id;
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

