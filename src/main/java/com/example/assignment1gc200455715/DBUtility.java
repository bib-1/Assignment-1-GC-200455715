package com.example.assignment1gc200455715;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
//This method will select data from the database.
    public static ArrayList<ChessPlayer> getChessPlayersFromDB() throws SQLException{
        ArrayList<ChessPlayer> players = new ArrayList<>();

        String selectQuery = "SELECT id, name, country, sex, rating FROM chess_player;";

        try(
                Connection conn = DriverManager.getConnection(DBCred.getConnectURL(), DBCred.getUserName(), DBCred.getPassword()); //connects to the database
                Statement st = conn.createStatement(); //Creates connection object
                ResultSet rs = st.executeQuery(selectQuery) // Executes query and stores result
        ){
            while(rs.next()){
                int id = rs.getInt("id"); // gets the integer value indexed from id column of db table
                int rating = rs.getInt("rating"); // gets the integer value indexed from rating column of db table
                String  name = rs.getString("name"); //gets the string value indexes from name column of database
                String  sex = rs.getString("sex"); //gets the string value indexes from sex column of database
                String  country = rs.getString("country"); //gets the string value indexes from country column of database

                //Creating new ChessPlayer Object
                ChessPlayer newPlayer = new ChessPlayer(id, name, country, sex, rating);

                //adding the object into the arraylist collection
                players.add(newPlayer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    /*
    * To load data from csv to database
    * Reference: https://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html
    **/
    public static void exportCsvToDatabase(String filename) throws IOException {

        //using try with resource to read the file using BufferedReader
        try(BufferedReader br = Files.newBufferedReader(Path.of(filename))
        )   {
            String line = br.readLine(); //to read the first line of the file

            line = br.readLine(); // to skip the title line

            while(line != null){

                String [] playerArray = line.split(",");

                //calling the insert function to inter the player array which contains the name, country, sex and rating
                insertPlayerIntoDB(playerArray);

                line = br.readLine();
            }
        }
    }

    public static void insertPlayerIntoDB( String [] array) {

        //insert statement
        String insertQuery = "INSERT INTO chess_player(name, country, sex, rating) VALUES(?, ?, ?, ?);";

        try(
                Connection conn = DriverManager.getConnection(DBCred.getConnectURL(), DBCred.getUserName(), DBCred.getPassword()); //connects to the database
                PreparedStatement ps = conn.prepareStatement(insertQuery)
        ){

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

    //main method to test the methods of this class
    public static void main(String[] args) throws SQLException {
        for ( ChessPlayer c: getChessPlayersFromDB()) {
            System.out.printf("%s, %s, %s, %d%n", c.getName(), c.getCountry(), c.getSex(), c.getRating());
        }
     }
}
