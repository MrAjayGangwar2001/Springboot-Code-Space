package com.crud.CRUD.Repository;

import com.crud.CRUD.Model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentModel, Integer> {

}
