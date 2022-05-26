package com.leticoin.webapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;

    private String name, surname;

    @ManyToMany()
    private List<Long> courses = new ArrayList<>();

    public User() {
        name = "Jim";
        surname = "Jones";
    }

    public User(Long id, String name) {
        this.user_id = id;
        this.name = name;
    }

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Long> getCourses() {
        return courses;
    }

    public void setCourses(List<Long> courses) {
        this.courses = courses;
    }

    public void addCourse(Long course_id){
        courses.add(course_id);
    }
}
