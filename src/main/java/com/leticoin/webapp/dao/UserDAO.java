package com.leticoin.webapp.dao;

import com.leticoin.webapp.model.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    public static final String URL = "jdbc:postgresql://localhost/LetiCoin_first_db";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "23922392";

    private static Connection connection;
    static {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<User> getUsers() {

        List <User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Person");

            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("userid"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(long id){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Person WHERE userid = " + id);
            if(resultSet.next()){
                User user = new User();
                user.setId(id);
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addCourse(long user_id, long course_id){
        try {
            Statement statement = connection.createStatement();
            String SQL = "insert into personcourses values(" + user_id + "," + course_id + "," + 0 +" )";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(User user){

    }
}
