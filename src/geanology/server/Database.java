package geanology.server;

import geanology.packets.Person;

public class Database {

	/**addPersonToDatabase
	 * 
	 * @param personToBeAdded
	 * @return
	 */
	public static void addPersonToDatabase(Person personToBeAdded) {
		/*
    	 * This method is an initial attempt. It prints out the whole database after adding person
    	 * to confirm it added new person, which is not necessary.
    	 */
        System.out.println("PostgreSQL JDBC Connection Testing");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
           
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        Connection connection = null;
        Statement stmt = null;
       
        try {
            connection = DriverManager
                    .getConnection(
                            "jdbc:postgresql://dbteach2.cs.bham.ac.uk:5432/bah180",
                            "bah180", "jackie");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("INSERT INTO newdata.person " +
            		"(Person_ID, First_Name, Last_Name,) "
                    + "( Date_Of_Birth, Place_Of_Birth, Mother_ID, Father_ID, Child_ID, PlaceOfDeath, DateOfDeath, Bibliography ) 
                    VALUES ("+ personToBeAdded.getPerson_ID()+ "," +"'"+ personToBeAdded.getFirst_Name()+"',"+"'"
                    + personToBeAdded.getLast_Name()+"',"+"'"+ personToBeAdded.getDate_Of_Birth()+"',"+"'"
                    + personToBeAdded.getPlace_Of_Birth()+"',"+ personToBeAdded.getMother_ID()+","+ personToBeAdded.getFather_ID()+
                    ","+"'{"+ personToBeAdded.getChild_ID()+"}',"+"'"+ personToBeAdded.getPlaceOfDeath()+"'," +"'"
                    + personToBeAdded.getDateOfDeath()+"',"+"'"+ personToBeAdded.getBibliography()+"'"
                    + ");");
           
            while (rs.next()) {
               
                int personID = rs.getInt("Person_ID");
                String firstName = rs.getString("First_Name");
                String lastName = rs.getString("Last_Name");
                Date dateOfBirth = rs.getDate("Date_Of_Birth");
                String placeOfBirth = rs.getString("Place_Of_Birth");
                int motherID = rs.getInt("Mother_ID");
                int fatherID = rs.getInt("Father_ID");
                childID = rs.getArray("Child_ID");
                String placeOfDeath = rs.getString("PlaceOfDeath");
                Date dateOfDeath = rs.getDate("DateOfDeath");
                String bibliography = rs.getString("Bibliography");
                System.out.println("Person ID = " + personID);
                System.out.println("First Name = " + firstName);
                System.out.println("Last Name = " + lastName);
                System.out.println("DOB = " + dateOfBirth);
                System.out.println("Place Of Birth = " + placeOfBirth);
                System.out.println("Mother ID = " + motherID);
                System.out.println("Father ID = " + fatherID);
                System.out.println("Child ID(s) = " + childID);
                System.out.println("Place Of Death = " + placeOfDeath);
                System.out.println("Date Of Death = " + dateOfDeath);
                System.out.println("Extra Information = " + bibliography);

               System.out.println();
            }
           
           
           
           
            rs.close();
            stmt.close();
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
		  	Person result[];
        System.out.println("PostgreSQL JDBC Connection Testing");
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
          
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager
                    .getConnection(
                            "jdbc:postgresql://dbteach2.cs.bham.ac.uk:5432/bah180",
                            "bah180", "jackie");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Person_ID, First_Name, Last_Name, "
            + " Date_Of_Birth, Place_Of_Birth, Mother_ID, Father_ID, Child_ID, PlaceOfDeath, DateOfDeath, Bibliography FROM newdata.person WHERE First_Name ="+personSearchedFor.getFirst_Name()+";");
            while (rs.next()) {
               
            	 int personID = rs.getInt("Person_ID");
                 String firstName = rs.getString("First_Name");
                 String lastName = rs.getString("Last_Name");
                 Date dateOfBirth = rs.getDate("Date_Of_Birth");
                 String placeOfBirth = rs.getString("Place_Of_Birth");
                 int motherID = rs.getInt("Mother_ID");
                 int fatherID = rs.getInt("Father_ID");
                 childID = rs.getArray("Child_ID");
                 String placeOfDeath = rs.getString("PlaceOfDeath");
                 Date dateOfDeath = rs.getDate("DateOfDeath");
                 String bibliography = rs.getString("Bibliography");
            
                 while(personID != null){
                	 
                 }
               System.out.println();
            }
           
           
           
           
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            
        }
		return result;
	}

	
	public static void removePersonFromDatabase(Person personToBeRemoved) {
	 System.out.println("PostgreSQL JDBC Connection Testing");
         try {
             Class.forName("org.postgresql.Driver");
         } catch (ClassNotFoundException e) {
             System.out.println("Where is your PostgreSQL JDBC Driver? "
                     + "Include in your library path!");
             e.printStackTrace();
            
         }
         System.out.println("PostgreSQL JDBC Driver Registered!");
         Connection connection = null;
         Statement stmt = null;
        
         try {
             connection = DriverManager
                     .getConnection(
                             "jdbc:postgresql://dbteach2.cs.bham.ac.uk:5432/bah180",
                             "bah180", "jackie");
             stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("DELETE FROM newdata.person WHERE First_name=" +
             personToBeRemoved.getFirst_Name()     );
            
             while (rs.next()) {
                
                 int personID = rs.getInt("Person_ID");
                 String firstName = rs.getString("First_Name");
                 String lastName = rs.getString("Last_Name");
                 Date dateOfBirth = rs.getDate("Date_Of_Birth");
                 String placeOfBirth = rs.getString("Place_Of_Birth");
                 int motherID = rs.getInt("Mother_ID");
                 int fatherID = rs.getInt("Father_ID");
                 childID = rs.getArray("Child_ID");
                 String placeOfDeath = rs.getString("PlaceOfDeath");
                 Date dateOfDeath = rs.getDate("DateOfDeath");
                 String bibliography = rs.getString("Bibliography");
                 System.out.println("Person ID = " + personID);
                 System.out.println("First Name = " + firstName);
                 System.out.println("Last Name = " + lastName);
                 System.out.println("DOB = " + dateOfBirth);
                 System.out.println("Place Of Birth = " + placeOfBirth);
                 System.out.println("Mother ID = " + motherID);
                 System.out.println("Father ID = " + fatherID);
                 System.out.println("Child ID(s) = " + childID);
                 System.out.println("Place Of Death = " + placeOfDeath);
                 System.out.println("Date Of Death = " + dateOfDeath);
                 System.out.println("Extra Information = " + bibliography);

                System.out.println();
             }
            
            
            
            
             rs.close();
             stmt.close();
             connection.close();
         } catch (SQLException e) {
             System.out.println("Connection Failed! Check output console");
             e.printStackTrace();
             return;
         }
    }
	}

	public static Person updatePersonInDatabase(Person personUpdate) {
		return personUpdate;
		// TODO Auto-generated method stub
		
	}
}
