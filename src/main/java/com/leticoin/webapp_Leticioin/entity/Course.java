package com.leticoin.webapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long course_id;

    private String name;

    public Course(){
        this.course_id = Long.valueOf(0);
        this.name = "course is not available";
    }

    public Course(Long id, String name){
        this.course_id = id;
        this.name = name;
    }

    public Long getId() {
        return course_id;
    }

    public void setId(Long id) {
        this.course_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}