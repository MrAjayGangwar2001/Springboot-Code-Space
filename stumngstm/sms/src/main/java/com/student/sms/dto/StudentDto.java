package com.student.sms.dto;

import java.util.List;



public class StudentDto {
    
    private int id;
    private String FirstName;
    private String LastName;
    private String Email;

    // private List<CourseDto> course;

    public StudentDto(String Email, String FirstName, String LastName, int id) {
        this.Email = Email;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.id = id;
    }

    // public StudentDto(String Email, String FirstName, String LastName, List<CourseDto> course, int id) {
    //     this.Email = Email;
    //     this.FirstName = FirstName;
    //     this.LastName = LastName;
    //     this.course = course;
    //     this.id = id;
    // }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}
