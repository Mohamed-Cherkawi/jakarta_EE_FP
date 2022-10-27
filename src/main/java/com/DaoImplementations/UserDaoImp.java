package com.DaoImplementations;

import com.DaoInterface.UserDaoInter;
import com.helpers.UserQueries;
import com.util.DbConnection;
import com.Entity.User;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImp implements UserDaoInter {
    private Connection connection = DbConnection.getConnection();

    // Insert A User
    public boolean insertUser(@NotNull User user) throws SQLException {
        boolean isInserted = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(UserQueries.INSERT_USERS);
            preparedStatement.setString(1 , user.getName());
            preparedStatement.setString(2 , user.getPassword());
            preparedStatement.setString(3 , user.getEmail());
            preparedStatement.setString(4 , user.getPhone());
            isInserted = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isInserted;
    }
    // Select A User By Id
    public User selectUSer(int id){
        User user = null;
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(UserQueries.SELECT_USER_BY_ID);
            preparedStatement.setInt(1 , id);
            ResultSet rs = preparedStatement.executeQuery();
            String name = rs.getString("user_name");
            String password = rs.getString("password");
            String email = rs.getString("user_email");
            String phone = rs.getString("user_phone");
            user = new User(id , name , password , email , phone);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
    // Select All The Users
    public ArrayList<User> selectAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        try{
            Statement statement = this.connection.createStatement();
            ResultSet rs = statement.executeQuery(UserQueries.SELECT_ALL_USERS);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("user_name");
                String password = rs.getString("password");
                String email = rs.getString("user_email");
                String phone = rs.getString("user_phone");
                users.add(new User(id , name , password , email , phone));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
    // Update a user by id
    public boolean updateUserById(@NotNull User user) throws SQLException{
        boolean isUpdated = false;
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(UserQueries.UPDATE_USER_BY_ID);
            preparedStatement.setString(1 , user.getName());
            preparedStatement.setString(2 , user.getPassword());
            preparedStatement.setString(3 , user.getEmail());
            preparedStatement.setString(4 , user.getPhone());
            preparedStatement.setInt(5 , user.getId());
            isUpdated = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isUpdated;
    }
    // Delete a user by id
    public boolean deleteUserById(int id) throws SQLException{
        boolean isDeleted = false;
        try{
            PreparedStatement preparedStatement = this.connection.prepareStatement(UserQueries.DELETE_USER_BY_ID);
            preparedStatement.setInt(1 , id);
            isDeleted = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return isDeleted;
    }
}
