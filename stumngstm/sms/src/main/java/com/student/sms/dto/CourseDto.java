package com.student.sms.dto;


public class CourseDto {

    private Integer CourseId;
    private String CourseName;

    public CourseDto(String CourseName, Integer CourseId) {
        this.CourseName = CourseName;
        this.CourseId = CourseId;
    }

    public CourseDto() {
    }

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
