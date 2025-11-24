package com.crud.CRUD.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crud.CRUD.DTO.Studentdto;
import com.crud.CRUD.Model.StudentModel;
import com.crud.CRUD.Repository.StudentRepo;

@Service
public class StudentService {

    @Autowired
    StudentRepo SRepo;

    public Studentdto PostService(Studentdto studentdto){


        // There are Four Step For Post Service
        // We will Write Logic in these Four Steps

        // STEP 1. We will give Studentdto Data To StudentModel.

        StudentModel SModel = new StudentModel();
        // SModel.setId(studentdto.getId());
        SModel.setName(studentdto.getName());
        SModel.setEmail(studentdto.getEmail());

        // STEP 2. We will Save StudentModel Data in Database.
        // Functionality  => For Post => Save

        SRepo.save(SModel);


        // STEP 3. We will give StudentModel Data To Studentdto.

        Studentdto Sdto = new Studentdto(SModel.getId(), SModel.getName(), SModel.getEmail());

        // STEP 4. Return Studentdto.

        return Sdto;
    }

    // ---------------------------------------------------To Get All Data---------------------------------------------
    public List<Studentdto> getAllStudents(){
        // STEP 0. 
        List<StudentModel> StuModel = SRepo.findAll();

        // STEP .1 To get Model(StudentModel) Data into dto
        List<Studentdto> studentdto = StuModel.stream().map(sm -> new Studentdto(sm.getId(), sm.getName(), sm.getEmail())).toList();       // toList() is used to convertData into List
        // STET 2.  Will get dto Response
        // STEP 3. 

        return studentdto;
    }

    // To Get Data By ID

    // ----------------------------------------------------------------Method 1-----------------------
    // public List<Studentdto> getStudentById(Integer id){

    //     Optional<StudentModel> StuMdl = SRepo.findById(3);

    //     List<Studentdto> Stdntdto = StuMdl.stream().map(sm -> new Studentdto(sm.getId(), sm.getName(), sm.getEmail())).toList();


    //     return Stdntdto;
    // }

    // -----------------------------------------------------Method 2------------------------------

    public Studentdto getById(Integer id) throws Exception{


        // To get Data from database to Data Model
        Optional<StudentModel> smodel =  SRepo.findById(id);

        if (smodel.isPresent()) {
            StudentModel sm = smodel.get();

            // StudentModel Values ko Studentdto me lana
            Studentdto sdto = new Studentdto(sm.getId(), sm.getName(), sm.getEmail());
            return sdto;

        }else{
            throw new Exception("Id Not Found : "+id);
        }



    }

    // ------------------------------------------------------------Delete Methods------------------------------------------
    // Delete By Id 
    public Studentdto DelById(Integer id) throws Exception{


        // To get Data from database to Data Model
        Optional<StudentModel> smodel =  SRepo.findById(id);

        if (smodel.isPresent()) {
            StudentModel sm = smodel.get();

            SRepo.delete(sm);

            // StudentModel Values ko Studentdto me lana
            Studentdto sdto = new Studentdto(sm.getId(), sm.getName(), sm.getEmail());
            return sdto;

        }else{
            throw new Exception("Id Not Found : "+id);
        }



    }


    // To Update Student Data By Using Put Method
    // Put Method Update complete data of a Specific Entity

    public Studentdto PutMUpdateStuData(Studentdto studentdto,  Integer id) throws Exception{
        Optional<StudentModel> SmPut = SRepo.findById(id);

        if (SmPut.isPresent()) {
            
            StudentModel smPut = SmPut.get();

            smPut.setName(studentdto.getName());
            smPut.setEmail(studentdto.getEmail());

            SRepo.save(smPut);

            Studentdto studentDetailsPrint = new Studentdto(smPut.getId(), smPut.getName(), smPut.getEmail());

            return studentDetailsPrint;
        }else{
            throw new Exception("We Can Not Find By Id : "+id);
        }
    }

    public Studentdto PatchReqMethod(Studentdto studentdto, Integer id) throws Exception{
        Optional<StudentModel> PSM = SRepo.findById(id);

        if (PSM.isPresent()) {
            
            StudentModel psm = PSM.get();

            if (studentdto.getName() != null) {
                    psm.setName(studentdto.getName());
                }
            if (studentdto.getEmail() != null) {
                    psm.setEmail(studentdto.getEmail());
                }

            // try {
            //     // if (studentdto.getName() != null) {
            //     //     psm.setName(studentdto.getName());
            //     // }
            //     psm.setName(studentdto.getName());
            // } catch (Exception e) {
                
            //     System.out.println(e+ " :Either Student Name is Not Available or Null");
            // }
            // try {
            //     psm.setEmail(studentdto.getEmail());
            // } catch (Exception e) {
            //     System.out.println(e+ ": Either Email is not Available or Null");

            // }

            SRepo.save(psm);

            Studentdto stdtdto = new Studentdto(psm.getId(), psm.getName(), psm.getEmail());
            return stdtdto;
        }else{
            throw new Exception("We Could not Found Your Id Please Try With Correct Id !");
        }

    }
}
