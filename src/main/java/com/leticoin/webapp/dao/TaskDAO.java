package com.leticoin.webapp.dao;

import com.leticoin.webapp.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAO {
    public static long TASK_counter = 0;
    private List<Task> tasks;

    {
        tasks = new ArrayList<>();
        tasks.add(new Task(++TASK_counter,"Решить диофантовое уравнение","16x+7y=15"));
        tasks.add(new Task(++TASK_counter,"Решить диофантовое уравнение","42x+36y=48"));
        tasks.add(new Task(++TASK_counter,"Решить диофантовое уравнение","2x+3y=5"));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTaskById(long id){
        for(int i=0;i<tasks.size();i++) if (tasks.get(i).getId() == id) return tasks.get(i);
        return null;
    }
}