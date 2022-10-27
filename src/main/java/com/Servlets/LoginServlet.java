package com.Servlets;

import com.util.DbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.helpers.UserQueries.SELECT_ALL_BY_EMAIL_PASS;

@WebServlet(name = "Login", value = "/Login")
public class LoginServlet extends HttpServlet {
    private Connection connection ;
    @Override
    public void init(ServletConfig config){
        this.connection = DbConnection.getConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        RequestDispatcher dispatcher ;
        if( email == null || email.equals("") ){
            request.setAttribute("status","invEmail");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request,response);
        }
        if( password == null || password.equals("") ){
            request.setAttribute("status","invPass");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request,response);
        }
        try {
            PreparedStatement pst = this.connection.prepareStatement(SELECT_ALL_BY_EMAIL_PASS);
            pst.setString(1,email);
            pst.setString(2 , password);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                session.setAttribute("name",rs.getString("user_name"));
                dispatcher = request.getRequestDispatcher("index.jsp");
            }else {
                request.setAttribute("status","failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(request,response);
            // Closing Ojects
            rs.close();
            pst.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}