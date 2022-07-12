package com.leticoin.webapp.dao;


import com.leticoin.webapp.entity.Task;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskD {
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

    public Task getTaskById(long id){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Task\" WHERE taskid = " + id);
            while(resultSet.next()){
                Task task = new Task(resultSet.getLong("taskid"),
                        resultSet.getString("task"),
                        resultSet.getString("condition"),
                        resultSet.getString("answer"),
                        resultSet.getLong("courseid"),
                        resultSet.getInt("price"));
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"Task\" WHERE courseid = " + id);
            while(resultSet.next()){
                Task task = new Task(resultSet.getLong("taskid"),
                        resultSet.getString("task"),
                        resultSet.getString("condition"),
                        resultSet.getString("answer"),
                        resultSet.getLong("courseid"),
                        resultSet.getInt("price"));
                courseTasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseTasks;
    }

    public boolean isSolved(long user_id, long task_id){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select count(*) <> 0 as answer from public.\"Solved\" where userid = "+ user_id +" and taskid = " + task_id);
            resultSet.next();
            return resultSet.getBoolean("answer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void solve(long user_id, long task_id, long course_id){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select * from public.\"Personcourses\" where userid = "+user_id+" and courseid = "+course_id);
            resultSet.next();
            int amount = resultSet.getInt("amount");
//            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from public.\"Task\" where taskid = " + task_id);
            resultSet.next();
            amount += resultSet.getInt("price");
            statement.executeUpdate(
                    "insert into public.\"Solved\" values(" + user_id + "," + task_id + " )");
            statement.executeUpdate(
                    "update public.\"Personcourses\" set amount = " + amount + " where userid = " + user_id + " and courseid = " + course_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}