package com.leticoin.webapp.dao;

import com.leticoin.webapp.entity.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserD {
    public static final String URL = "jdbc:postgresql://localhost/demo";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "user1234";

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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Person\"");

            while(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Person\" WHERE id = " + id);
            if(resultSet.next()){
                User user = new User();
                user.setId(id);
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByUsername(String name){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"user_test\" WHERE email =" + "'" + name + "'");
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
//                user.resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
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
            String SQL = "insert into public.\"personcourses\" values(" + user_id + "," + course_id + "," + 0 +" )";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(User user){

    }
}