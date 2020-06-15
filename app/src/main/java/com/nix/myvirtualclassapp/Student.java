package com.nix.myvirtualclassapp;

public class Student {
    private String first_name;
    private String second_name;
    private String course;
    //constructor - method with a class name we use to intialize our class attributes values
    //create an object of that class
    // alt + ins


    public Student() {
    }

    public Student(String first_name, String second_name, String course) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.course = course;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
