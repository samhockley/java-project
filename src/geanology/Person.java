package geanology;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;

//This Person class needs to be filled out (first name, etc.)
//this implements Serializable so that it can be converted into bytes to be sent
public class Person implements Serializable {

/**
*
*/
	private static final long serialVersionUID = -1801107241608319486L;
	private int Person_ID;
	private String First_Name;
	private String Last_Name;
	private Date Date_Of_Birth;
	private String Place_Of_Birth;
	private int Mother_ID;
	private int Father_ID;
	private int[] Child_ID;
	private String PlaceOfDeath;
	private Date DateOfDeath;
	private String biography;
    
	public Person() {
		
	}
	
	public Person(int person_ID, String first_Name, String last_Name,
			Date date_Of_Birth, String place_Of_Birth, int mother_ID,
			int father_ID, int[] child_ID, String placeOfDeath,
			Date dateOfDeath, String biography) {
		this.Person_ID = person_ID;
		this.First_Name = first_Name;
		this.Last_Name = last_Name;
		this.Date_Of_Birth = date_Of_Birth;
		this.Place_Of_Birth = place_Of_Birth;
		this.Mother_ID = mother_ID;
		this.Father_ID = father_ID;
		this.Child_ID = child_ID;
		this.PlaceOfDeath = placeOfDeath;
		this.DateOfDeath = dateOfDeath;
		this.biography = biography;
	}



	
// TODO Auto-generated constructor stub
	

	public int getPerson_ID() {
	return Person_ID;
	}




	public String getFirst_Name() {
	return First_Name;
	}




	public String getLast_Name() {
	return Last_Name;
	}




	public Date getDate_Of_Birth() {
	return Date_Of_Birth;
	}




	public String getPlace_Of_Birth() {
	return Place_Of_Birth;
	}




	public int getMother_ID() {
	return Mother_ID;
	}




	public int getFather_ID() {
	return Father_ID;
	}




	public int[] getChild_ID() {
	return Child_ID;
	}




	public String getPlaceOfDeath() {
	return PlaceOfDeath;
	}




	public Date getDateOfDeath() {
	return DateOfDeath;
	}
	
	
	public String getBiography() {
	return biography;
	}



	public void setPerson_ID(int person_ID) {
	Person_ID = person_ID;
	}




	public void setFirst_Name(String first_Name) {
	First_Name = first_Name;
	}




	public void setLast_Name(String last_Name) {
	Last_Name = last_Name;
	}




	public void setDate_Of_Birth(Date date_Of_Birth) {
	Date_Of_Birth = date_Of_Birth;
	}




	public void setPlace_Of_Birth(String place_Of_Birth) {
	Place_Of_Birth = place_Of_Birth;
	}




	public void setMother_ID(int mother_ID) {
	Mother_ID = mother_ID;
	}




	public void setFather_ID(int father_ID) {
	Father_ID = father_ID;
	}




	public void setChild_ID(int[] child_ID) {
	Child_ID = child_ID;
	}




	public void setPlaceOfDeath(String placeOfDeath) {
	PlaceOfDeath = placeOfDeath;
	}




	public void setDateOfDeath(Date dateOfDeath) {
	DateOfDeath = dateOfDeath;
	}
	


	public void setBiography(String biography) {
	biography = biography;
	}


	public void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
		aInputStream.defaultReadObject();
	}
	
	public void writeObject(ObjectOutputStream aOutputStream) throws IOException {
		aOutputStream.defaultWriteObject();
	}
			
}
