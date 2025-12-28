package com.user.UserCrud.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.UserCrud.DTO.Userdto;
import com.user.UserCrud.Model.UserModel;
import com.user.UserCrud.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository URepo;

    // ------------------------------------------To Post User Data
    
     public Userdto PostMethod(Userdto userdto) {
    // public String PostMethod(Userdto userdto) {                  // Let's try with return type String

        // Step 1. To Make UserModel Object

        UserModel usermodel = new UserModel();
        usermodel.setfirstName(userdto.getfirstName());
        usermodel.setlastName(userdto.getlastName());
        // usermodel.setemail(email);
        usermodel.setemail(userdto.getemail());

        // Step 2. Data Will be save in database through Repo

        URepo.save(usermodel);

        // Step 3. Now We will give Data from UserModel to userdto.

        Userdto udto = new Userdto(usermodel.getemail(), usermodel.getfirstName(), usermodel.getlastName(),
                usermodel.getId());

        // Step 4. We will return the object of userdto

        return udto;
       // return "Data Has been Submitted"+udto;
    }

    // ---------------------------------------To Get User Data from DataBase -----------------------
    

    public List<Userdto> AllUserData() {

        // Step 1. Model will get UserData from Database through Repo.

        List<UserModel> um = URepo.findAll();

        // Step 2. Now Userdto will get Data from UserModel
        
        List<Userdto> udto = um.stream().map(x -> new Userdto(x.getemail(), x.getfirstName(), x.getlastName(), x.getId())).toList();

        // Step 3. Now Userdto Data will be Return

        return udto;
    }


    // -----------------------------------------To get Data By Id ---------------------

    public Userdto GetDataById(Integer id) throws Exception{

        // To find id through repo
        Optional<UserModel> UM = URepo.findById(id);
        // UserModel UM = URepo.getOne(id);

        if (UM.isPresent()) {
            UserModel um = UM.get();

            Userdto UDto = new Userdto(um.getemail(),um.getfirstName(), um.getlastName(), um.getId());

            return UDto;
        } else {
            throw new Exception("Sorry! We could not find ID.");
        }
    }

    public Userdto DeleteById(Integer id) throws Exception{

        Optional<UserModel> umdl = URepo.findById(id);

        if (umdl.isPresent()) {
            UserModel um = umdl.get();
            URepo.delete(um);
            // Till Here Deletion completed

            Userdto ud = new Userdto(um.getemail(), um.getfirstName(), um.getlastName(), um.getId());
            return ud;

        } else {
            throw new Exception("Could not find ID !");
        }
    }


    // -------------------------------------To Update Data ----------------------------

    // Put Method => It Will Updat complete data of User

    public Userdto UpdateByPut( Userdto userdto, Integer id) throws Exception{

        Optional<UserModel> umdl = URepo.findById(id);

        if (umdl.isPresent()) {
            UserModel um = umdl.get();

            um.setemail(userdto.getemail());
            um.setfirstName(userdto.getfirstName());
            um.setlastName(userdto.getlastName());

            URepo.save(um);

            Userdto udto = new Userdto(um.getemail(), um.getfirstName(), um.getlastName(), um.getId());
            return udto;
        } else {
            throw new Exception("We could not find the ID : "+id);
        }
    }


    // ----------------------------------------------To update Partial Data------------------------------

    // By Using Patch Method

    public Userdto UpdateByPatch(Userdto userdto, Integer id) throws Exception{

        Optional<UserModel> usrm = URepo.findById(id);

        if (usrm.isPresent()) {
            UserModel umdl = usrm.get();

            umdl.setemail(userdto.getemail());
            umdl.setfirstName(userdto.getfirstName());
            umdl.setlastName(userdto.getlastName());

            URepo.save(umdl);

            Userdto ud = new Userdto(umdl.getemail(), umdl.getfirstName(), umdl.getlastName(), umdl.getId());
            return ud;
        } else {
            throw new Exception("Sorry! ID Not Found.");
        }

    }

}