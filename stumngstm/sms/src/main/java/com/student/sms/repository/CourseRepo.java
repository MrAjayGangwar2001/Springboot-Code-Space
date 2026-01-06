package com.student.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.sms.model.CourseModel;

public interface CourseRepo extends JpaRepository<CourseModel, Integer>{

}
