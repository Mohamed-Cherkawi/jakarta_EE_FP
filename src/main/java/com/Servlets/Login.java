package com.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String passw = request.getParameter("pwd");

        String url = "jdbc:mysql://localhost:3306/testtest";
        String user = "adminX";
        String dbPwd = "coder911";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,user ,dbPwd);
            PreparedStatement pst = con.prepareStatement("SELECT id FROM users WHERE user_name=? AND password=?");
            pst.setString(1,username);
            pst.setString(2 , passw);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                session.setAttribute("username",username);
                response.sendRedirect("index.jsp");
            }else response.sendRedirect("auth.jsp");
            // Closing Ojects
            rs.close();
            pst.close();
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
