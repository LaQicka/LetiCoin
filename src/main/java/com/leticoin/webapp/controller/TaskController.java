package com.leticoin.webapp.controller;

import com.leticoin.webapp.dao.TaskDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    @Autowired
    private TaskDAO taskDAO;

    @GetMapping("/tasks")
    public String tasks(Model model){
        model.addAttribute("tasks",taskDAO.getTasks());
        return "tasks/tasksList"; // list of tasks
    }

    @GetMapping("/tasks/{id}")
    public String task_info(@PathVariable("id") int id, Model model){
        model.addAttribute("task", taskDAO.getTaskById(id));
        return "tasks/task"; // page of task with field to answer
    }

    @PostMapping("/tasks/{id}")
    public String checkAnswer(@RequestParam(value = "id", required = false, defaultValue = "0") int id, @ModelAttribute("task") String answer){

        System.out.println(answer);

        return "redirect:/tasks/{id}"; // need to rework
    }

}