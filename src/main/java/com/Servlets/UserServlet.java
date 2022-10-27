package com.Servlets;

import com.DaoImplementations.UserDaoImp;
import com.Entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", value = "/UserServ")
public class UserServlet extends HttpServlet {

    private UserDaoImp userDaoImp;
    // The init method is called only once. It is called only when the servlet is created, and not called for any user requests afterwards. So, it is used for one-time initializations, just as with the init method of applets
    @Override
    public void init() throws ServletException {
        userDaoImp = new UserDaoImp();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action){
            case  "/new" :
            this.showNewForm(request , response);
            break;
            case  "/insert" :
                try {
                    this.insertUser(request , response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/test" :
                System.out.println(request.getRequestURI());
                break;
            default:
        }
    }
    private void showNewForm(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request , response);
    }
    private void insertUser(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException, SQLException {
        String message;
        boolean isInserted;
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        isInserted = this.userDaoImp.insertUser(new User(name, password, email, phone));
        if( isInserted ) message = "Inserted Successfuly";
        else message = "Not Inserted";
        response.sendRedirect("list?isInserted=" + message);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
