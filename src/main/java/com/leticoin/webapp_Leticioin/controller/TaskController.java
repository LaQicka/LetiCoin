package com.leticoin.webapp.controller;

import com.leticoin.webapp.dao.CourseD;
import com.leticoin.webapp.dao.TaskD;
import com.leticoin.webapp.dao.UserD;
import com.leticoin.webapp.entity.Course;
import com.leticoin.webapp.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    @Autowired
    private TaskD taskDAO;
    @Autowired
    private UserD userDAO;
    @Autowired
    private CourseD courseDAO;

    @GetMapping("/courses/{course_id}/{task_id}")
    public String task(@PathVariable("course_id") int course_id,
                       @PathVariable("task_id") int task_id,
                       Model model){
        User temp = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.leticoin.webapp.entity.User user = userDAO.getUserByUsername(temp.getUsername());
        model.addAttribute("task",taskDAO.getTaskById(task_id));
        Boolean solved = taskDAO.isSolved(user.getId(),task_id);
//        model.addAttribute("user", userDAO.getUserById(user.getId()));
        model.addAttribute("course",courseDAO.getCourseById(course_id));
        if(solved) model.addAttribute("solved","Already solved");
        else model.addAttribute("solved","Not solved yet");
        return "task";
    }

    @PostMapping("/courses/{course_id}/{task_id}")
    public String checkAnswer(
            @RequestParam("answer") String answer,
            @PathVariable("course_id") int course_id,
            @PathVariable("task_id") int task_id,
            Model model
    ){
        User temp = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.leticoin.webapp.entity.User user = userDAO.getUserByUsername(temp.getUsername());
        Boolean solved = taskDAO.isSolved(user.getId(),task_id);
        if(solved == false && taskDAO.getTaskById(task_id).getAnswer().equals(answer))taskDAO.solve(user.getId(),task_id,course_id);
        return "redirect:/courses/{course_id}/{task_id}";
    }
}