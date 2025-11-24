package com.crud.CRUD.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.CRUD.DTO.Studentdto;
import com.crud.CRUD.Service.impl.StudentService;

@RestController
@RequestMapping("/Student") // It Map by Keywords and Auto add End of Page Url For Every Page
public class StudentController {

    @Autowired
    private StudentService Service;

    // @PostMapping("/Student") // It Not Required means Mapping (/Student) Not
    // required becos We already Used @RequestMapping("/Student")
    @PostMapping()
    public Studentdto StudentPost(@RequestBody Studentdto studentdto) {

        return Service.PostService(studentdto);
    }

    // @GetMapping("/Student")
    @GetMapping()
    public List<Studentdto> getAllStudent() {
        return Service.getAllStudents();
    }

    // @GetMapping("/student")
    // public List<Studentdto> getStudentById() {
    // return Service.getStudentById(3);
    // }

    @GetMapping("/{id}")
    public Studentdto getById(@PathVariable Integer id) throws Exception {
        return Service.getById(id);
    }

    // To Delete Data
    @DeleteMapping("/{id}")
    public Studentdto DelById(@PathVariable Integer id) throws Exception {
        return Service.DelById(id);
    }

    // To Update Data
    @PutMapping("/{id}")
    public Studentdto PutMUpdateStuData(@RequestBody Studentdto studentdto, @PathVariable Integer id) throws Exception {
        // process PUT request

        return Service.PutMUpdateStuData(studentdto, id);
    }

    // To Update Partial Data
    @PatchMapping("/{id}")
    public Studentdto PatchReqMethod(@RequestBody Studentdto studentdto, @PathVariable Integer id) throws Exception{

        return Service.PatchReqMethod(studentdto, id);
    }

}
