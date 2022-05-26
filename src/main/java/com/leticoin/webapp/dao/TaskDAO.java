package com.leticoin.webapp.dao;

import com.leticoin.webapp.model.Task;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAO {
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

    public Task getTaskById(long id){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Task WHERE taskid = " + id);
            while(resultSet.next()){
                Task task = new Task(resultSet.getLong("taskid"),resultSet.getString("task"),resultSet.getString("condition"),resultSet.getString("answer"),resultSet.getLong("courseid"));
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Task> getTasksByCourseId(long id){
        List<Task> courseTasks = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Task WHERE courseid = " + id);
            while(resultSet.next()){
                Task task = new Task(resultSet.getLong("taskid"),resultSet.getString("task"),resultSet.getString("condition"),resultSet.getString("answer"),resultSet.getLong("courseid"));
                courseTasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseTasks;
    }
}