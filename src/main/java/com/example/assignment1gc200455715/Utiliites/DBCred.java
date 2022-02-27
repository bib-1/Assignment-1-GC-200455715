package com.example.assignment1gc200455715.Utiliites;

public class DBCred {
    private static String userName = "Bibek200455715";
    private static String password = "TLY78N8G_4";
    private static String connectURL = "jdbc:mysql://172.31.22.43:3306/Bibek200455715";

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static String getConnectURL() {
        return connectURL;
    }
}


