package geanology.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

import geanology.packets.Person;

public class Database {

	private static Connection connection = null;

	/**
	 * addPersonToDatabase
	 * 
	 * @param personToBeAdded
	 * @return
	 */
	public static Person addPersonToDatabase(Person personToBeAdded) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * getPeopleResultsForSearchTerm
	 * 
	 * @param personSearchedFor
	 * @return an array of search results for the term searched for
	 */
	public static Person[] getPeopleResultsForSearchTerm(
			Person personSearchedFor) {
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<Person> people = new ArrayList<Person>();
		try {
			stmt = connection.createStatement();
			rs = stmt
					.executeQuery("SELECT Person_ID, First_Name, Last_Name, "
							+ " Date_Of_Birth, Place_Of_Birth, Mother_ID, Father_ID, Child_ID, PlaceOfDeath, DateOfDeath, Bibliography FROM newdata.person WHERE Person_ID="
							+ personSearchedFor.getPerson_ID() + ";");

			while (rs.next()) {
				int personID = rs.getInt("Person_ID");
				String firstName = rs.getString("First_Name");
				String lastName = rs.getString("Last_Name");
				Date dateOfBirth = rs.getDate("Date_Of_Birth");
				String placeOfBirth = rs.getString("Place_Of_Birth");
				int motherID = rs.getInt("Mother_ID");
				int fatherID = rs.getInt("Father_ID");
				// as we can't have Arrays in SQL, we store all the children's IDs
				// as one String, separated by 'pipes'
				// e.g: 1354|1367
				String[] childID = rs.getString("Child_ID").split("|");
				int[] childID_int = new int[childID.length];
				for (int i = 0; i < childID.length; i++) {
					childID_int[i] = Integer.parseInt(childID[i]);
				}

				String placeOfDeath = rs.getString("PlaceOfDeath");
				Date dateOfDeath = rs.getDate("DateOfDeath");
				String bibliography = rs.getString("Bibliography");

				Person result = new Person(personID, firstName, lastName,
						dateOfBirth, placeOfBirth, motherID, fatherID,
						childID_int, placeOfDeath, dateOfDeath, bibliography);

				people.add(result);
			}

		} catch (SQLException e) {

		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// this converts the results ArrayList into an Array
		return people.toArray(new Person[0]);
	}

	public static void removePersonFromDatabase(Person personToBeRemoved) {
		// TODO Auto-generated method stub

	}

	public static Person updatePersonInDatabase(Person personUpdate) {

		return personUpdate;

		// TODO Auto-generated method stub
	}

	static {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(
					"jdbc:postgresql://dbteach2.cs.bham.ac.uk:5432/newdata",
					"bah180", "jackie");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error connecting to database! Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");
	}

}
