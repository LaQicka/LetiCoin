package com.leticoin.webapp.dao;

import com.leticoin.webapp.model.Course;

import com.leticoin.webapp.model.CourseAmountPair;
import com.leticoin.webapp.model.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<CourseAmountPair> findUserCourses(long user_id){
            try {
                List<CourseAmountPair> courses = new ArrayList<>();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM personcourses WHERE userid = " + user_id);
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
