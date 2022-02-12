package com.example.assignment1gc200455715;

import java.sql.*;

public class DBUtility {

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

    public static void main(String[] args) {
        DBTest();
    }
}
