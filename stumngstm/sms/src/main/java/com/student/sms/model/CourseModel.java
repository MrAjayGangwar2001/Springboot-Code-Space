package com.student.sms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class CourseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CourseId;
    @Column(nullable = false, unique = true)
    private String CourseName;

    public CourseModel(String CourseName) {
        // this.CourseId = CourseId;
        this.CourseName = CourseName;
    }

    public CourseModel() {
        
    }




    
    @ManyToOne
    @JoinColumn(name = "student_id")             // JoinColumn is used to forgien key
    private StudentModel studentModel;

    public Integer getCourseId() {
        return CourseId;
    }

    public void setCourseId(Integer CourseId) {
        this.CourseId = CourseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }
}
