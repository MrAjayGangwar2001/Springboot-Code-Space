package com.example.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sms.dto.studentDto;
import com.example.sms.service.studentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private studentService service;

    @PostMapping()
    public studentDto createStudent(@RequestBody studentDto sDto) {

        return service.createStudent(sDto);
    }

    // join to course

//    @PostMapping("/{studentId}/course/{cousreId}")
    public studentDto jointoCourse(@PathVariable Long studentId, @PathVariable Long cousreId)

    {

        return service.jointoCourse(studentId, cousreId);

    }

}

// @PostMapping("/{studentId}/course/{cousreId}")
// public studentDto joinTOcourse(@PathVariable Long studentId, @PathVariable
// Long cousreId ){

// return service.joinTOcourse(studentId,cousreId);

// }
