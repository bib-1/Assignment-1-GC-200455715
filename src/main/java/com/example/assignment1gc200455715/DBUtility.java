package com.example.assignment1gc200455715;

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {


    /*
    //Testing database connection

     public static void DBTest(){
         try(
                 Connection conn = DriverManager.getConnection(DBCred.getConnectURL(), DBCred.getUserName(), DBCred.getPassword());
         )
         {
             System.out.println("Connected with database");
         }
         catch (SQLException e)
         {
             e.printStackTrace();
         }
     }

     */

    /*
        *This method will select data from the database.
     */
    public static ArrayList<ChessPlayer> getChessPlayersFromDB() throws SQLException{
        ArrayList<ChessPlayer> players = new ArrayList<ChessPlayer>();

        String selectQuery = "SELECT id, name, country, sex, rating FROM chess_player;";

        try(
                Connection conn = DriverManager.getConnection(DBCred.getConnectURL(), DBCred.getUserName(), DBCred.getPassword()); //connects to the database
                Statement st = conn.createStatement(); //Creates connection object
                ResultSet rs = st.executeQuery(selectQuery); // Executes query and stores result
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

    public static void main(String[] args) {
        try {
            System.out.println(getChessPlayersFromDB());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
