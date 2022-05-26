package com.leticoin.webapp.controller;

import com.leticoin.webapp.dao.TaskDAO;
import com.leticoin.webapp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    @Autowired
    private TaskDAO taskDAO;

    @GetMapping("/users/{user_id}/courses/{course_id}/{task_id}")
    public String tasks(@PathVariable("user_id") int user_id, @PathVariable("course_id") int course_id, @PathVariable("task_id") int task_id,Model model){
        model.addAttribute("task",taskDAO.getTaskById(task_id));
        return "tasks/task";
    }

    @PostMapping("/tasks/{id}")
    public String checkAnswer(@PathVariable("id") int id, @ModelAttribute("task") Task task){

        System.out.println(id);
        System.out.println(task.getAnswer());
        return "redirect:/tasks/{id}"; // need to rework
    }
}