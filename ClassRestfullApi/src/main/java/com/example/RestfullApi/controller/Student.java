package com.example.RestfullApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RestfullApi.Dto.StudentDto;
import com.example.RestfullApi.service.logic.StudentService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController  //=> component   =>  es ke object aap ko ye filne or config
//   component = > restcontroller  , service  resportiy
//  rest api  => api 
public class Student {

    @Autowired
    private StudentService service;

    // => get 
    @GetMapping("/student")
    public StudentDto Getmethod(){
        StudentDto studentDto = new StudentDto(1l, "adarsh","email@mail.com");
        return  studentDto ;
    }




    @PostMapping("path")
    public StudentDto postMethodName(@RequestBody StudentDto studentDto) {
        
        return  service.createStudent(studentDto);
    }
    



}

//  localHost:8080/student

    // controller  DTO 
    //  string  => string 
    //  DTO=>  studentDto  =>  return StudentDto

    //  get  => response  => DTO  => entiye 