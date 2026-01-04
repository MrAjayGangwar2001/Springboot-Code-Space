package com.example.sms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sms.dto.CourseDto;
// import com.example.sms.dto.CourseDto;
import com.example.sms.dto.studentDto;
import com.example.sms.model.Course;
import com.example.sms.model.Student;
import com.example.sms.repositry.CourseRepo;
import com.example.sms.repositry.StudentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class studentService {

    @Autowired
    private StudentRepo repo;
    @Autowired
    private CourseRepo courseRepo;

    public studentDto createStudent(studentDto sDto) {

        Student smodel = Student.builder()
                .firstName(sDto.getFirstName())
                .lastName(sDto.getLastName())
                .email(sDto.getEmail())
                .build();

        repo.save(smodel);

        studentDto SDTOS = new studentDto(smodel.getId(), smodel.getFirstName(), smodel.getLastName(),
                smodel.getEmail());

        return SDTOS;

    }

    public studentDto jointoCourse(Long studentId, Long cousreId) {
        Student student = repo.findById(studentId).orElseThrow(() -> new RuntimeException(" student id not found"));
        Course course = courseRepo.findById(cousreId).orElseThrow(() -> new RuntimeException(" course id not found"));

        student.getCourses().add(course); // ye line likhte hi list bale cousre jo h mere student me waha me ye core de
                                          // jo data find hus h woo push ho jaye

        repo.save(student);

        // yaha tak pe clear the mamala database me

        return new studentDto( // ye reponse likh ke liye likhi
                student.getId(), student.getFirstName(), student.getLastName(), student.getEmail(),
                student.getCourses().stream().map(c -> new CourseDto(c.getId(), c.getCourseName())).toList()

        );
    }

    // feture vitrual thread => csv file uppolder

    // today

    // ONE to many || many to one
    // Normalization =>
    // https://www.geeksforgeeks.org/dbms/introduction-of-database-normalization/

    //

}

// public studentDto joinTOcourse(Long studentId, Long cousreId) {

// Student student = repo.findById(studentId).orElseThrow(()-> new
// RuntimeException("\"studentnot found\"")) ;

// Course course = courseRepo.findById(cousreId).orElseThrow(()-> new
// RuntimeException("course id not foulnd "));

// student.getCourse().add(course);

// repo.save(student);

// return new studentDto(
// student.getId(),student.getFirstName(),student.getLastName(),student.getEmail(),
// student.getCourse().stream().map(c -> new
// CourseDto(c.getId(),c.getCourseName())).toList());

// }