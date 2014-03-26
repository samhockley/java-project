package geanology.server;

import geanology.packets.Person;

import java.sql.*;

import SWGroup.Person;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.postgresql.util.PSQLException;

public class Database {

    /**
     * addPersonToDatabase
     *
     * @param personToBeAdded
     * @return
     */
    final static String Database_URL = "jdbc:postgresql://dbteach2.cs.bham.ac.uk:5432/newdata";
   
   
    public static void aux(){
       
    }
	public static void addPersonToDatabase(Person personToBeAdded) {
		/*
    	 * This method is an initial attempt. It prints out the whole database after adding person
    	 * to confirm it added new person, which is not necessary.
    	 */
        System.out.println("PostgreSQL JDBC Connection Testing");

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(Database_URL, "bah180",
                    "jackie");

            statement = connection.createStatement();

            statement
                    .executeUpdate("INSERT INTO newdata.person "
                            + "(Person_ID, First_Name, Last_Name, "
                            + " Date_Of_Birth, Place_Of_Birth, Mother_ID, Father_ID, Child_ID, PlaceOfDeath, DateOfDeath, Biography ) VALUES ("
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
                            + "'{"
                            + personToBeAdded.getChild_ID()
                            + "}',"
                            + "'"
                            + personToBeAdded.getPlaceOfDeath()
                            + "',"
                            + "'"
                            + personToBeAdded.getDateOfDeath()
                            + "',"
                            + "'"
                            + personToBeAdded.getBiography() + "'" + ");");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
    }

	/**getPeopleResultsForSearchTerm
	 * 
	 * @param personSearchedFor
	 * @return an array of search results for the term searched for 
	 */
	
	public static Person[] getPeopleResultsForSearchTerm(
			Person personSearchedFor) {
		  	  Person result[] = null;

        System.out.println("PostgreSQL JDBC Connection Testing");

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(Database_URL, "bah180",
                    "jackie");

            statement = connection.createStatement();

            ResultSet rs = statement
                    .executeQuery("SELECT (person_ID, first_Name, last_Name, "
                            + " Date_Of_Birth, Place_Of_Birth, Mother_ID, Father_ID, Child_ID, PlaceOfDeath, DateOfDeath, Biography) FROM newdata.person WHERE first_Name = '"
                            + personSearchedFor.getFirst_Name() + "';");

            ResultSetMetaData metaData = rs.getMetaData();
            System.out.println();
            int numberOfColumns = metaData.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.printf("%-8s\t", metaData.getColumnName(i));

            for (int i = 1; i <= numberOfColumns; i++)
                System.out.printf("%-8s\t", metaData.getColumnName(i));

            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.printf("%-8s\t", rs.getObject(i));
                System.out.println();
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return result;
        }
        System.out.println(result);
        return result;
    }

	
	public static void removePersonFromDatabase(Person personToBeRemoved) {
	 System.out.println("PostgreSQL JDBC Connection Testing");

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(Database_URL, "bah180",
                    "jackie");

            statement = connection.createStatement();

            ResultSet rs = statement
                    .executeQuery("DELETE FROM newdata.person WHERE first_Name = '"
                            + personToBeRemoved.getFirst_Name() + "';");

            ResultSetMetaData metaData = rs.getMetaData();
            System.out.println();
            int numberOfColumns = metaData.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++)
                System.out.printf("%-8s\t", metaData.getColumnName(i));

            for (int i = 1; i <= numberOfColumns; i++)
                System.out.printf("%-8s\t", metaData.getColumnName(i));
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= numberOfColumns; i++)
                    System.out.printf("%-8s\t", rs.getObject(i));
                System.out.println();
            }

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

    }

	public static Person updatePersonInDatabase(Person personUpdate) {
		System.out.println("PostgreSQL JDBC Connection Testing");

        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection(Database_URL, "bah180",
                    "jackie");

            statement = connection.createStatement();

            statement
                    .executeUpdate("UPDATE newdata.person SET PlaceOfDeath = '"
                            + personUpdate.getPlaceOfDeath()
                            + "' WHERE first_Name = 'Mollie';");

            System.out.println("Connection Established!");

            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

    }
}
	}
}
