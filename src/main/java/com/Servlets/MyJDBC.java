package com.Servlets;

import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/red_line_project", "adminX","coder911@");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select product_name from products");
            while (resultSet.next()){
                System.out.println(resultSet.getString("product_name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
