package com.leticoin.webapp.entity;


public class CourseAmountPair {
    Course course;
    Integer amount;

    public CourseAmountPair(Course course, Integer amount) {
        this.course = course;
        this.amount = amount;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}