package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static final String jdbcUrl = "jdbc:mysql://localhost:3306/javaee";
    public static final String jdbcUsername = "adminX";
    public static final String jdbcPassword = "coder911@";
    public static final String jdbcDriver = "com.mysql.jdbc.Driver";

    public static Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(jdbcUrl , jdbcUsername , jdbcPassword);
        }catch (SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }
}


