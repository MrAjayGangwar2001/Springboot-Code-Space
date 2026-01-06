package com.student.sms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity


public class StudentModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String FirstName;
    private String LastName;
    @Column(unique = true)
    private String Email;

    public StudentModel() {

    }

    public StudentModel(String Email, String FirstName, String LastName) {
        this.Email = Email;
        this.FirstName = FirstName;
        this.LastName = LastName;
        
    }



//       @OneToMany
//   private List<CourseModel> courses = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
}
