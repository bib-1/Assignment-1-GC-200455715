package com.example.assignment1gc200455715;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DBUtility {
    //This method will select data from the database.
    public static ArrayList<ChessPlayer> getChessPlayersFromDB() throws SQLException {
        ArrayList<ChessPlayer> players = new ArrayList<>();

        String selectQuery = "SELECT id, name, country, sex, rating FROM chess_player;";

        try (
                Connection conn = DriverManager.getConnection(DBCred.getConnectURL(), DBCred.getUserName(), DBCred.getPassword()); //connects to the database
                Statement st = conn.createStatement(); //Creates connection object
                ResultSet rs = st.executeQuery(selectQuery) // Executes query and stores result
        ) {
            while (rs.next()) {
                int id = rs.getInt("id"); // gets the integer value indexed from id column of db table
                int rating = rs.getInt("rating"); // gets the integer value indexed from rating column of db table
                String name = rs.getString("name"); //gets the string value indexes from name column of database
                String sex = rs.getString("sex"); //gets the string value indexes from sex column of database
                String country = rs.getString("country"); //gets the string value indexes from country column of database

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
        try (BufferedReader br = Files.newBufferedReader(Path.of(filename))
        ) {
            String line = br.readLine(); //to read the first line of the file

            line = br.readLine(); // to skip the title line

            while (line != null) {

                String[] playerArray = line.split(",");

                //calling the insert function to inter the player array which contains the name, country, sex and rating
                insertPlayerIntoDB(playerArray);

                line = br.readLine();
            }
        }
    }

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

    //method to get the countries and the number of players
    public static XYChart.Series<String, Integer> getCountryAndPlayer() {
    XYChart.Series<String, Integer>countries = new XYChart.Series<>();


        String selectQuery = "SELECT country, count(country)\n" +
                "from chess_player\n" +
                "GROUP BY country\n" +
                "ORDER BY COUNT(country) DESC\n" +
                "LIMIT 10;";

        try (
                Connection conn = DriverManager.getConnection(DBCred.getConnectURL(), DBCred.getUserName(), DBCred.getPassword()); //connects to the database
                Statement st = conn.createStatement(); //Creates connection object
                ResultSet rs = st.executeQuery(selectQuery) // Executes query and stores result
        ) {
            while (rs.next()) {
                int id = rs.getInt("count(country)"); // gets the integer value indexed from id column of db table
                String country = rs.getString("country"); //gets the string value indexes from country column of database

                countries.getData().addAll(new XYChart.Data<>(country, id));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return countries;
    }


    //To return the numbers of players based on the rating category
    public static XYChart.Series<String, Integer> getRatingsAndNumber() {
        XYChart.Series<String, Integer>ratings = new XYChart.Series<>();


        String selectQuery = "SELECT '1000 - 1250' AS 'Rating Range', count(rating) as 'Players #'\n" +
                "FROM chess_player\n" +
                "where rating BETWEEN 1000 AND 1250\n" +
                "UNION\n" +
                "SELECT '1251 - 1500', count(rating)\n" +
                "FROM chess_player\n" +
                "where rating BETWEEN 1251 AND 1500\n" +
                "UNION\n" +
                "SELECT '1501 - 1750', count(rating)\n" +
                "FROM chess_player\n" +
                "where rating BETWEEN 1501 AND 1750\n" +
                "UNION\n" +
                "SELECT '1751 - 2000', count(rating)\n" +
                "FROM chess_player\n" +
                "where rating BETWEEN 1751 AND 2000\n" +
                "UNION\n" +
                "SELECT '> 2000', count(rating)\n" +
                "FROM chess_player\n" +
                "where rating > 2000;";

        try (
                Connection conn = DriverManager.getConnection(DBCred.getConnectURL(), DBCred.getUserName(), DBCred.getPassword()); //connects to the database
                Statement st = conn.createStatement(); //Creates connection object
                ResultSet rs = st.executeQuery(selectQuery) // Executes query and stores result
        ) {
           while (rs.next()) {
                int numbers = rs.getInt("Players #"); // gets the integer value indexed from id column of db table
                String range = rs.getString("Rating Range"); //gets the string value indexes from country column of database

                ratings.getData().addAll(new XYChart.Data<>(range, numbers));
              }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ratings;
    }

    public static ObservableList<PieChart.Data> getPieChartData() {


        //Reference: https://stackoverflow.com/questions/12673873/javafx-piechart-created-with-scene-builder-does-not-dispaly-data
        //Observable
        ObservableList<PieChart.Data> genderData = FXCollections.observableArrayList();
        try (
                Connection conn = DriverManager.getConnection(DBCred.getConnectURL(), DBCred.getUserName(), DBCred.getPassword()); //connects to the database
                Statement st = conn.createStatement(); //Creates connection object
                ResultSet rs = st.executeQuery("SELECT sex, count(sex)\n" +
                        "from chess_player\n" +
                        "GROUP BY sex;") // Executes query and stores result
        ) {
            while (rs.next()) {
                int numbers = rs.getInt("count(sex)"); // gets the integer value indexed from id column of db table
                String gender = rs.getString("sex"); //gets the string value indexes from country column of database
                if(gender.equalsIgnoreCase("M")) {
                    genderData.addAll(new PieChart.Data("Male", numbers));
                }
                else{
                    genderData.addAll(new PieChart.Data("Female", numbers));
                }
//                Metadata to check which column to select https://docs.oracle.com/javase/8/docs/api/java/sql/ResultSetMetaData.html
//                ResultSetMetaData rsMetaData = rs.getMetaData();
//                System.out.println(rsMetaData.getColumnName(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genderData;
    }

    //main method to test the methods of this class
    public static void main(String[] args) throws SQLException {
        getPieChartData();
    }
}
