package geanology.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;

import geanology.Person;

public class Database {

	private static Connection connection = null;

	private static String addPersonStatement = "INSERT INTO newdata.person (First_Name, Last_Name, Date_Of_Birth, Place_Of_Birth, Mother_ID, Father_ID, Child_ID, PlaceOfDeath, DateOfDeath, Biography ) "
										+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	private static String searchForPersonStatement_Base = "SELECT * FROM newdata.person WHERE (First_Name ILIKE ? AND Last_Name ILIKE ? AND Place_Of_Birth ILIKE ? AND PlaceOfDeath ILIKE ? AND Biography ILIKE ?";
	private static String searchPersonStatement = searchForPersonStatement_Base+") OR (person_id = ?);";
	private static String searchPersonStatement_M = searchForPersonStatement_Base+" AND Mother_ID=?) OR (person_id = ?);";
	private static String searchPersonStatement_F = searchForPersonStatement_Base+" AND Father_ID=?) OR (person_id = ?);";
	private static String searchPersonStatement_MF = searchForPersonStatement_Base+" AND Mother_ID=? AND Father_ID=?) OR (person_id = ?);";

	private static String updatePersonStatement = "UPDATE newdata.person SET First_Name=?, Last_Name=?, Date_Of_Birth=?, Place_Of_Birth=?, Mother_ID=?, Father_ID=?, Child_ID=?, PlaceOfDeath=?, DateOfDeath=?, Biography=? WHERE Person_ID=?;";
	
	/**
	 * addPersonToDatabase
	 * 
	 * @param personToBeAdded
	 * @return
	 */
	public static Person addPersonToDatabase(Person personToBeAdded) {
		try {
			PreparedStatement prep = connection.prepareStatement(addPersonStatement, Statement.RETURN_GENERATED_KEYS);
			prep.setString(1, personToBeAdded.getFirst_Name());
			prep.setString(2, personToBeAdded.getLast_Name());
			prep.setDate(3, personToBeAdded.getDate_Of_Birth());
			prep.setString(4, personToBeAdded.getPlace_Of_Birth());
			prep.setInt(5, personToBeAdded.getMother_ID());
			prep.setInt(6, personToBeAdded.getFather_ID());
			System.out.println(intArrayToPipeSeperatedString(personToBeAdded.getChild_ID()));
			prep.setString(7, intArrayToPipeSeperatedString(personToBeAdded.getChild_ID()));
			prep.setString(8, personToBeAdded.getPlaceOfDeath());
			prep.setDate(9, personToBeAdded.getDateOfDeath());
			prep.setString(10, personToBeAdded.getBiography());

			prep.executeUpdate();
			
			ResultSet rs = prep.getGeneratedKeys();
            if(rs.next())
            {
                int last_inserted_id = rs.getInt("person_id");
                System.out.println("ID: "+last_inserted_id);
    			Person personJustInsertedToSearchFor = new Person();
    			personJustInsertedToSearchFor.setPerson_ID(last_inserted_id);

    			Person[] personJustInserted = getPeopleResultsForSearchTerm(personJustInsertedToSearchFor);

    			if (personJustInserted.length > 0) {
    				return personJustInserted[0];
    			} else {
    				return null;
    			}
            }
            

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

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
		ResultSet rs = null;

		ArrayList<Person> people = new ArrayList<Person>();
		try {
			PreparedStatement prep = null;
			boolean m = false, f = false;
			if(personSearchedFor.getMother_ID() != 0) {
				m = true;
			}
			if(personSearchedFor.getFather_ID() != 0) {
				f = true;
			}
			if(m && !f) {
				prep = connection.prepareStatement(searchPersonStatement_M);
			} else if (!m && f) {
				prep = connection.prepareStatement(searchPersonStatement_F);
			} else if (m && f) {
				prep = connection.prepareStatement(searchPersonStatement_MF);
			} else {
				prep = connection.prepareStatement(searchPersonStatement);
			}
			
			prep.setString(1, "%" + personSearchedFor.getFirst_Name() + "%");
			prep.setString(2, "%" + personSearchedFor.getLast_Name() + "%");
			prep.setString(3, "%" + personSearchedFor.getPlace_Of_Birth());
			prep.setString(4, "%" + personSearchedFor.getPlaceOfDeath() + "%");
			prep.setString(5, "%" + personSearchedFor.getBiography() + "%");
			
			if(m && !f) {
				prep.setInt(6, personSearchedFor.getMother_ID());
				prep.setInt(7, personSearchedFor.getPerson_ID());
			} else if (!m && f) {
				prep.setInt(6, personSearchedFor.getFather_ID());
				prep.setInt(7, personSearchedFor.getPerson_ID());
			} else if (m && f) {
				prep.setInt(6, personSearchedFor.getMother_ID());
				prep.setInt(7, personSearchedFor.getFather_ID());
				prep.setInt(8, personSearchedFor.getPerson_ID());
			} else {
				prep.setInt(6, personSearchedFor.getPerson_ID());
			}
			

			rs = prep.executeQuery();

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
				String childID_str = rs.getString("Child_ID");
				int[] childID_int = new int[0];
				if(childID_str != null) {
						String[] childID = childID_str.split("|");
						childID_int = new int[childID.length];
						for (int i = 0; i < childID.length; i++) {
							try {
								childID_int[i] = Integer.parseInt(childID[i]);
							} catch(NumberFormatException e) {
							}
						}
				}

				String placeOfDeath = rs.getString("PlaceOfDeath");
				Date dateOfDeath = rs.getDate("DateOfDeath");
				String biography = rs.getString("Biography");

				Person result = new Person(personID, firstName, lastName,
						dateOfBirth, placeOfBirth, motherID, fatherID,
						childID_int, placeOfDeath, dateOfDeath, biography);

				people.add(result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		// this converts the results ArrayList into an Array
		return people.toArray(new Person[0]);
	}

	public static void removePersonFromDatabase(Person personToBeRemoved) {
		Statement stmt = null;

		try {
			stmt = connection.createStatement();
			stmt.execute("DELETE FROM newdata.person WHERE Person_ID="
					+ personToBeRemoved.getPerson_ID() + ";");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static Person updatePersonInDatabase(Person personUpdate) {
		try {
			PreparedStatement prep = connection.prepareStatement(updatePersonStatement);
			
			prep.setString(1, personUpdate.getFirst_Name());
			prep.setString(2, personUpdate.getLast_Name());
			prep.setDate(3, personUpdate.getDate_Of_Birth());
			prep.setString(4, personUpdate.getPlace_Of_Birth());
			prep.setInt(5, personUpdate.getMother_ID());
			prep.setInt(6, personUpdate.getFather_ID());
			prep.setString(7, intArrayToPipeSeperatedString(personUpdate.getChild_ID()));
			prep.setString(8, personUpdate.getPlaceOfDeath());
			prep.setDate(9, personUpdate.getDateOfDeath());
			prep.setString(10, personUpdate.getBiography());
			prep.setInt(11, personUpdate.getPerson_ID());

			prep.execute();

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
			e.printStackTrace();
		} finally {
			
		}
		return null;
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
					"jdbc:postgresql://10.42.13.3:9090/newdata",
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