package com.helpers;

public abstract class UserQueries {
    public static final String INSERT_USERS = "INSERT INTO users( user_name , password , user_email , user_phone ) VALUES ( ?,?,?,? ) ;";
    public static final String SELECT_USER_BY_ID = "SELECT user_name , password , user_email , user_phone FROM users WHERE id = ? ;";
    public static final String SELECT_ALL_USERS = "SELECT * FROM users ;";
    public static final String SELECT_ALL_BY_EMAIL_PASS = "SELECT * FROM users WHERE user_email=? AND password=?";
    public static final String UPDATE_USER_BY_ID = "UPDATE users SET user_name = ? , password = ? , user_email = ? , user_phone = ? WHERE id = ?";
    public static final String DELETE_USER_BY_ID = "DELETE FROM USERS WHERE id = ? ;";
}
