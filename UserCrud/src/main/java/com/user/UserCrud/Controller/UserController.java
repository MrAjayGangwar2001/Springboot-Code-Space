package com.user.UserCrud.Controller;

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

import com.user.UserCrud.DTO.Userdto;
import com.user.UserCrud.Model.UserModel;
import com.user.UserCrud.Repository.UserRepository;
import com.user.UserCrud.Service.UserService;



@RestController
@RequestMapping("/user")
public class UserController{
    
    @Autowired
    private UserService us;

    // To Post data in database
    @PostMapping()
     public Userdto UserPostMethod(@RequestBody Userdto userdto){
    // public String UserPostMethod(@RequestBody Userdto userdto){

        return us.PostMethod(userdto);
    }


    // To Fetch ot Get User data From Database

    // By Using produces property we can define the data Which we want by using jackson Dependency
    // @GetMapping(produces = {"application/xml"})
    @GetMapping()
    public List<Userdto> AllUserData(){
        return us.AllUserData();
    }

    // OR BY OTHER METHOD

    // This Method Also works Without Service Layer as same as Upper Works 
    @Autowired
    UserRepository urepo;
    @GetMapping("/data")         
    public List<UserModel> GetAllUserData(){

        List<UserModel> users = urepo.findAll();
        return users;
    }



    // To Get Data By Id

    @GetMapping("/{id}")
    public Userdto GetDataById(@PathVariable Integer id) throws Exception{
        return us.GetDataById(id);
    }

    // To Delete By Id 

    @DeleteMapping("/{id}")
    public Userdto DeleteById(@PathVariable Integer id) throws Exception {
        return us.DeleteById(id);
    }

    // To Updte Data By Put Method

    @PutMapping("/{id}")
    public Userdto UpdateByPut(@RequestBody Userdto userdto, @PathVariable Integer id ) throws Exception {
        
        return us.UpdateByPut(userdto, id);
    }

    // To Update Partial Data of User

    @PatchMapping("/{id}")
    public Userdto UpdateByPatch(@RequestBody Userdto userdto, @PathVariable Integer id) throws Exception{

        return us.UpdateByPatch(userdto, id);
    }
    
    
}