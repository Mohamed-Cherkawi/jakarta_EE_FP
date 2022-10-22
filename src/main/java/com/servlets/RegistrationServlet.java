package com.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "Register", value = "/Register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Getting all the data provided by the user
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String phone = request.getParameter("contact");
        RequestDispatcher dispatcher = null;
        Connection connection ;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// Creating an instance of Driver Class to use JDBC services
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testtest", "adminX","coder911");
            // PreparedStatement statement = connection.prepareStatement("INSERT INTO testtest.users(user_name , password , user_email , user_phone) VALUES(?,?,?,?) ");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO testtest.users(user_name , password , user_email , user_phone) VALUES(?,?,?,?); ");
            statement.setString(1 , name);
            statement.setString(2 , password);
            statement.setString(3 , email);
            statement.setString(4 , phone);

            int rowCount = statement.executeUpdate();
            dispatcher = request.getRequestDispatcher("registration.jsp");
            if(rowCount > 0){
                request.setAttribute("status","success");
            }else {
                request.setAttribute("status","failed");
            }
            dispatcher.forward(request , response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
