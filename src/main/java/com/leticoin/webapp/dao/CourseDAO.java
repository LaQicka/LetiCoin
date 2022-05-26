package com.leticoin.webapp.dao;

import com.leticoin.webapp.model.Course;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class CourseDAO {
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
    public Course getCourseById(long id){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Course WHERE courseid = " + id);
            if(resultSet.next()){
                Course course = new Course(resultSet.getLong("courseid"),resultSet.getString("name"));
                return course;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
