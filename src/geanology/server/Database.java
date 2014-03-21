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
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = connection.createStatement();
			int idOfInsertedPerson = stmt
					.executeUpdate(
							"INSERT INTO newdata.person "
									+ "(Person_ID, First_Name, Last_Name, Date_Of_Birth, Place_Of_Birth, Mother_ID, Father_ID, Child_ID, PlaceOfDeath, DateOfDeath, Bibliography )  VALUES ("
									+ personToBeAdded.getPerson_ID()
									+ ","
									+ "'"
									+ personToBeAdded.getFirst_Name()
									+ "',"
									+ "'"
									+ personToBeAdded.getLast_Name()
									+ "',"
									+ "'"
									+ personToBeAdded.getDate_Of_Birth()
									+ "',"
									+ "'"
									+ personToBeAdded.getPlace_Of_Birth()
									+ "',"
									+ personToBeAdded.getMother_ID()
									+ ","
									+ personToBeAdded.getFather_ID()
									+ ","
									+ "'"
									// we're using this method to convert the
									// Child_ID ints into A String, separated by
									// pipes
									+ intArrayToPipeSeperatedString(personToBeAdded
											.getChild_ID())
									+ "'"
									+ ","
									+ personToBeAdded.getPlaceOfDeath()
									+ "',"
									+ "'"
									+ personToBeAdded.getDateOfDeath()
									+ "',"
									+ "'"
									+ personToBeAdded.getBibliography()
									+ "'"
									+ ");", Statement.RETURN_GENERATED_KEYS);

			Person personJustInsertedToSearchFor = new Person();
			personJustInsertedToSearchFor.setPerson_ID(idOfInsertedPerson);

			Person[] personJustInserted = getPeopleResultsForSearchTerm(personJustInsertedToSearchFor);

			if (personJustInserted.length > 0) {
				return personJustInserted[0];
			} else {
				return null;
			}

		} catch (Exception e) {

		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
				// as we can't have Arrays in SQL, we store all the children's
				// IDs
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
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			stmt.executeQuery("DELETE FROM newdata.person WHERE Person_ID="
					+ personToBeRemoved.getPerson_ID() + ";");
		} catch (Exception e) {

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Person updatePersonInDatabase(Person personUpdate) {
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			stmt.executeQuery("UPDATE newdata.person SET " + "First_Name='"
					+ personUpdate.getFirst_Name() + ", " + "Last_Name='"
					+ personUpdate.getLast_Name() + ", " + "Date_Of_Birth='"
					+ personUpdate.getDate_Of_Birth() + ", "
					+ "Place_Of_Birth='" + personUpdate.getPlace_Of_Birth()
					+ ", " + "Mother_ID='"
					+ personUpdate.getMother_ID()
					+ ", "
					+ "Father_ID='"
					+ personUpdate.getFather_ID()
					+ ", "
					+ "Child_ID='"
					+ intArrayToPipeSeperatedString(personUpdate.getChild_ID())
					+ ", "// change because there is the possibility of multiple
							// child ids
					+ "Place_Of_Death='" + personUpdate.getPlaceOfDeath()
					+ ", " + "Date_Of_Death='" + personUpdate.getDateOfDeath()
					+ ", " + "Bibliography='" + personUpdate.getBibliography());

			Person personJustUpdatedToSearchFor = new Person();
			personJustUpdatedToSearchFor.setPerson_ID(personUpdate
					.getPerson_ID());

			Person[] personJustUpdated = getPeopleResultsForSearchTerm(personJustUpdatedToSearchFor);

			// This prevents it from crashing by making sure that the Person
			// we're updating actually exists before returning it
			if (personJustUpdated.length > 0) {
				return personJustUpdated[0];
			} else {
				return null;
			}

		} catch (Exception e) {

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return personUpdate;

	}

	/**
	 * intArrayToPipeSeperatedString
	 * 
	 * this converts the child id ints into a String separated by pipes
	 * 
	 * @param arr
	 * @return A String of IDs separated by 'pipes': |
	 */
	private static String intArrayToPipeSeperatedString(int[] arr) {
		String toReturn = new String();

		for (int i = 0; i < arr.length; i++) {
			toReturn = toReturn + arr[i] + "|";
		}

		// this removes unnecessary pipes at the end
		toReturn = toReturn.substring(0, toReturn.length() - 1);
		return toReturn;
	}

	// we only have to connect to the Database once so we do it here, instead of
	// in every method
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