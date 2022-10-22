package com.servlets;

import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testtest", "adminX","coder911");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()){
                System.out.println(resultSet.getString("user_name") + " " + resultSet.getString("user_phone"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
