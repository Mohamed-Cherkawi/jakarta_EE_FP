package com.DaoInterface;

import com.Entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDaoInter {

    boolean insertUser(User user) throws SQLException;

    User selectUSer(int id);

    ArrayList<User> selectAllUsers();

    boolean updateUserById(User user) throws SQLException;

    boolean deleteUserById(int id) throws SQLException;

}