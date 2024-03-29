package com.leticoin.webapp.controller;

import com.leticoin.webapp.dao.CourseDAO;
import com.leticoin.webapp.dao.TaskDAO;
import com.leticoin.webapp.dao.UserDAO;
import com.leticoin.webapp.model.Course;
import com.leticoin.webapp.model.CourseAmountPair;
import com.leticoin.webapp.model.Task;
import com.leticoin.webapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TaskDAO taskDAO;

    @GetMapping("/users/{user_id}/courses")
    public String courseList(@PathVariable("user_id") int user_id, Model model){
        User user = userDAO.getUserById(user_id);
        List<CourseAmountPair> courses = courseDAO.findUserCourses(user.getId());
        model.addAttribute("courses", courses);
        model.addAttribute("user", user);
        return "courses/coursesList.html";
    }

    @GetMapping("/users/{user_id}/courses/{course_id}")
    public String courseInfo(@PathVariable("user_id") int user_id,@PathVariable("course_id") int course_id,Model model){
        User user = userDAO.getUserById(user_id);
        Course course = courseDAO.getCourseById(course_id);
        model.addAttribute("user",user);
        model.addAttribute("course",course);
        List<Task> c = taskDAO.getTasksByCourseId(course_id);
        model.addAttribute("tasks",c);
        return "courses/courseInfo";
    }
}
