/*
 Sunlim Lee
 COP 2805 - Final Exam
 05/03/2023
 This is to view, insert, and delete staff information stored in the Staff table
 from Javabook database
 */

package javabook;

import java.sql.*;
import java.util.Scanner;
public class Javabook {
    // 
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
      
        Class.forName("com.mysql.jdbc.Driver"); // Load the JDBC driver
        System.out.println("Welcome to the Staff Management program!\n");
        
        //conect to a database
        Connection connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/javabook", "scott", "tiger");
        System.out.println("Database connected");
        System.out.println();
        //create a statement
        Statement statement = connection.createStatement();
        // Create a Scanner object
        Scanner userInput = new Scanner(System.in);
        int choice; // to hold number for choice
        String qid; // to hold id
        
        // do-while loop to iterate menu until a user enters 4
        do {
            // Createa Scanner object
            Scanner input = new Scanner(System.in);
            // Display menu
            System.out.println("1. View\n" + "2. Insert\n" + "3. Delete\n" + 
                    "4. Exit\n");
            System.out.print("Enter a choice: ");
            choice = userInput.nextInt(); // Read and store user input
            if(choice == 1){ // if user enters 1
                System.out.print("Enter staff's ID to view: "); // ask for id
                qid = input.nextLine(); // read and store id
                // Create a PreparedStatement for SQL statement
                PreparedStatement stmt = 
                        connection.prepareStatement("SELECT * FROM Staff WHERE id = ?");
                stmt.setString(1, qid); // Sets a String value, quid, to index 1
                ResultSet resultSet = stmt.executeQuery(); // Execute a statement
                // If query retrieves result from ResultSet object
                if (resultSet.next()){
                    System.out.println("Record found! "); // display message
                    System.out.println("--------------");
                    // display 9 attributes
                    System.out.println(resultSet.getString(1) + "\t" + 
                        resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" +
                        resultSet.getString(4) + "\t" + resultSet.getString(5) + "\t" +
                        resultSet.getString(6) + "\t" + resultSet.getString(7) + "\t" +
                        resultSet.getString(8) + "\t" + resultSet.getString(9));
                    } else { // if no result is found
                        System.out.println("No record found!"); // display message
                    }

            } else if(choice == 2){ // if user enters 2
                System.out.print("ID: "); // ask for id
                String tid = input.nextLine(); // read and store input
                System.out.print("Last name: "); // ask for lastName
                String lastname = input.nextLine(); // read and store input
                System.out.print("First name: "); // ask for firstName
                String firstname = input.nextLine(); // read and store input
                System.out.print("Middle initial: "); // ask for middle name
                String middlei = input.nextLine(); // read and store input
                System.out.print("Address: "); // ask for address
                String addr = input.nextLine(); // read and store input
                System.out.print("City: "); // ask for city
                String city = input.nextLine(); // read and store input
                System.out.print("State (2 letters): "); // ask for state
                String stusps = input.nextLine(); // read and store input
                System.out.print("Telephone: "); // ask for phone number
                String phone = input.nextLine(); // readn and store
                System.out.print("Email: "); // ask for email 
                String email = input.nextLine(); // read and store
                
                // Create a PreparedStatement for SQL statement
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO "+
                        "Staff(id,lastName,firstName,mi,address,city,state, " +
                        "telephone,email) VALUES(?,?,?,?,?,?,?,?,?);");
                stmt.setString(1, tid); // set tid to index 1
                stmt.setString(2, lastname); // set lastname to 2
                stmt.setString(3, firstname); // set firstname to 3
                stmt.setString(4, middlei); // set middlei to 4
                stmt.setString(5, addr); // set addr to 5
                stmt.setString(6, city); // set city to 6
                stmt.setString(7, stusps); // set stusps to 7
                stmt.setString(8, phone); // set phone to 8
                stmt.setString(9, email); // set email to 9
                
                // User try-catch statements for input valdiation
                try {
                    stmt.executeUpdate(); // Execute the procedure
                    System.out.println("Record inserted.");
                } catch (SQLException sqe) {
                    // If id input already exists, display message
                    if (sqe.getMessage().startsWith("Duplicate entry")){
                        System.out.println("A record with this ID has already "
                                + "existed! Record NOT inserted.");
                    } else { // display other messages for other types of input errors
                        System.out.println("Error Code = " + sqe.getErrorCode());
                        System.out.println("SQL state = " + sqe.getSQLState());
                        System.out.println("Message = " + sqe.getMessage());
                        System.out.println("Record NOT inserted.");
                    }
                }
            } else if (choice == 3) { // if user enters 3
                System.out.print("Enter staff's ID to delete: ");
                qid = input.nextLine(); // read and store user input
                // Create a PreparedSatement for delete query
                PreparedStatement stmt = 
                        connection.prepareStatement("DELETE FROM Staff WHERE id = ?");
                stmt.setString(1, qid); // Eet qid to 1
                stmt.executeUpdate(); // Execute the procedure
                System.out.println("Record deleted.");
            }
        } 
        // If user enters 4, exit the loop
        while(choice != 4);
        if (choice == 4){
            System.out.println("Good bye!"); 
        }
        connection.close(); // Close connection
    }        
}
