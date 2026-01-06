package com.student.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.sms.model.StudentModel;

public interface StudentRepo extends JpaRepository<StudentModel, Integer> {

}
