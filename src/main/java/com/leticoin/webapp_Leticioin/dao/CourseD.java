package com.leticoin.webapp.dao;

import com.leticoin.webapp.entity.Course;

import com.leticoin.webapp.entity.CourseAmountPair;
import com.leticoin.webapp.entity.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseD {
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
    public Course getCourseById(long id){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Course\" WHERE courseid = " + id);
            if(resultSet.next()){
                Course course = new Course(resultSet.getLong("courseid"),resultSet.getString("name"));
                return course;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CourseAmountPair> findUserCourses(long user_id){
        try {
            List<CourseAmountPair> courses = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Personcourses\" WHERE userid = " + user_id);
            while(resultSet.next()){
                Course course = getCourseById(resultSet.getLong("courseid"));
                Integer amount = resultSet.getInt("amount");
                courses.add(new CourseAmountPair(course,amount));
            }
            return courses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}