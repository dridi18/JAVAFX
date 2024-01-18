package com.example.projet_javafx.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {


    private static String url ="jdbc:mysql://localhost:3306/foodiefinder";
    private static String user ="root";
    private static String pwd ="";
    private static DataSource data;

    private Connection con;

    public Connection getCon() {
        return con;
    }
    private DataSource(){
        try {
            con= DriverManager.getConnection(url,user,pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public static DataSource getInstance(){
        if (data == null) data = new DataSource();
        return data;
    }





}