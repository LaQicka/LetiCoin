package com.leticoin.webapp.controller;

import com.leticoin.webapp.dao.CourseDAO;
import com.leticoin.webapp.dao.TaskDAO;
import com.leticoin.webapp.dao.UserDAO;
import com.leticoin.webapp.model.Course;
import com.leticoin.webapp.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    @Autowired
    private TaskDAO taskDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CourseDAO courseDAO;

    @GetMapping("/users/{user_id}/courses/{course_id}/{task_id}")
    public String task(@PathVariable("user_id") int user_id,
                       @PathVariable("course_id") int course_id,
                       @PathVariable("task_id") int task_id,
                       Model model){
        model.addAttribute("task",taskDAO.getTaskById(task_id));
        Boolean solved = taskDAO.isSolved(user_id,task_id);
        model.addAttribute("user", userDAO.getUserById(user_id));
        model.addAttribute("course",courseDAO.getCourseById(course_id));
        if(solved) model.addAttribute("solved","Already solved");
        else model.addAttribute("solved","Not solved yet");
        return "tasks/task";
    }

    @PostMapping("/users/{user_id}/courses/{course_id}/{task_id}")
    public String checkAnswer(
                              @RequestParam("answer") String answer,
                              @PathVariable("user_id") int user_id,
                              @PathVariable("course_id") int course_id,
                              @PathVariable("task_id") int task_id,
                              Model model
                              ){
        Boolean solved = taskDAO.isSolved(user_id,task_id);
        if(solved == false && taskDAO.getTaskById(task_id).getAnswer().equals(answer))taskDAO.solve(user_id,task_id,course_id);
        return "redirect:/users/{user_id}/courses/{course_id}/{task_id}";
    }
}