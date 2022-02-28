package com.example.assignment1gc200455715.Utiliites;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

//class that contains the methods to create and populate table using scv file
public class CreateDbTable {

    //method to create the table based on the passed query
    public static void executeQuery(String sqlString){
        try(Connection conn = DriverManager.getConnection(DBCred.getConnectURL(), DBCred.getUserName(), DBCred.getPassword());
            Statement st = conn.createStatement()
        ) {
            st.executeUpdate(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * To load data from csv to database
     * Reference: https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html
     **/
    public static void exportCsvToDatabase(String filename) throws IOException {

        //using try with resource to read the file using BufferedReader
        try (BufferedReader br = Files.newBufferedReader(Path.of(filename))
        ) {
            String line = br.readLine(); //to read the first line of the file

            line = br.readLine(); // to skip the title line
            System.out.println("Loading csv data into table ........");
            while (line != null) {

                String[] playerArray = line.split(",");

                //calling the insert function to inter the player array which contains the name, country, sex and rating
                insertPlayerIntoDB(playerArray);

                line = br.readLine();
            }
            System.out.println("All data populated in the table");
        }
    }

    //method to insert the appropriate string array into database
    public static void insertPlayerIntoDB(String[] array) {

        //insert statement
        String insertQuery = "INSERT INTO chess_player(name, country, sex, rating) VALUES(?, ?, ?, ?);";

        try (
                Connection conn = DriverManager.getConnection(DBCred.getConnectURL(), DBCred.getUserName(), DBCred.getPassword()); //connects to the database
                PreparedStatement ps = conn.prepareStatement(insertQuery)
        ) {

            //configuring the prepared statement to prevent SQL injection attacks
            ps.setString(1, array[0]);
            ps.setString(2, array[1]);
            ps.setString(3, array[2]);
            ps.setInt(4, Integer.parseInt(array[3]));

            //run the command into DB
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //run this method to create the table
    public static void main(String[] args) throws IOException {
        //drops table if already exists
        executeQuery("DROP TABLE If Exists chess_player;");
        //creates new table
        executeQuery("""
                CREATE TABLE chess_player (
                id int NOT NULL AUTO_INCREMENT,
                name varchar(60) NOT NULL,
                country varchar(45) NOT NULL,
                sex varchar(1) NOT NULL,
                rating int NOT NULL,
                PRIMARY KEY(id)
                );""");
        //populates the value into the table from the csv file
        exportCsvToDatabase("players_in_chess.csv");
    }
}
