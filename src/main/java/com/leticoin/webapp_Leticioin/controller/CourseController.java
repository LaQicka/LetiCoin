package com.leticoin.webapp.controller;

import com.leticoin.webapp.dao.CourseD;
import com.leticoin.webapp.dao.TaskD;
import com.leticoin.webapp.dao.UserD;
import com.leticoin.webapp.entity.Course;
import com.leticoin.webapp.entity.CourseAmountPair;
import com.leticoin.webapp.entity.Task;
//import com.leticoin.webapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;



@Controller
public class CourseController {
    @Autowired
    private CourseD courseDAO;
    @Autowired
    private UserD userDAO;
    @Autowired
    private TaskD taskDAO;

    @GetMapping("/courses")
    public String courseList(Model model){
        User temp = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.leticoin.webapp.entity.User user = userDAO.getUserByUsername(temp.getUsername());
        List<CourseAmountPair> courses = courseDAO.findUserCourses(user.getId());
        model.addAttribute("courses", courses);
//        model.addAttribute("user", user);
        return "coursesList";
    }

    @GetMapping("/courses/{course_id}")
    public String courseInfo(@PathVariable("course_id") int course_id,Model model){
        User temp = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.leticoin.webapp.entity.User user = userDAO.getUserByUsername(temp.getUsername());
        Course course = courseDAO.getCourseById(course_id);
//        model.addAttribute("user",user);
        model.addAttribute("course",course);
        List<Task> c = taskDAO.getTasksByCourseId(course_id);
        model.addAttribute("tasks",c);
        return "courseInfo";
    }
}