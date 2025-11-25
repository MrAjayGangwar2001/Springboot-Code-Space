package com.Test.testUser.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.Test.testUser.DTO.UserDto;
import com.Test.testUser.Exception.IdNotFoundException;
import com.Test.testUser.Model.UserModel;
import com.Test.testUser.Repository.UserRepo;

@Service
public class UserService {


    @Autowired
    private UserRepo ur;

    public UserDto PostData(UserDto userDto){

        // STEP 1. 

        UserModel um = new UserModel();
        um.setEmail(userDto.getEmail());
        um.setFirstName(userDto.getFirstName());
        um.setLastName(userDto.getLastName());
        
        // STEP 2. 
        ur.save(um);

       // STEP 3. 

       UserDto udto =  new UserDto(um.getId(), um.getFirstName(), um.getLastName(), um.getEmail());

       return udto;


    }


    public List<UserDto> GetAllData(){

        List<UserModel> um = ur.findAll();

        List<UserDto> sdto = um.stream().map((x) -> new UserDto(x.getId(), x.getFirstName(), x.getLastName(), x.getEmail())).toList();
        return sdto;

        // return um;

    }


    public UserDto getDataById(Integer id) throws Exception{

        Optional<UserModel> um = ur.findById(id);

        if (um.isPresent()) {
            UserModel umdl = um.get();

            UserDto udto = new UserDto(umdl.getId(), umdl.getFirstName(), umdl.getLastName(), umdl.getEmail());
            return udto;

        } else {
            // throw new Exception("We Could Not Found The User ID !");
            throw new IdNotFoundException("User ID Database Me nhi Hai");            // package com.Test.testUser.Exception ==> Custom Exception
        }
    }
    // public UserDto getDataById(Integer id){

    //     Optional<UserModel> um = ur.findById(id);

       
    //         UserModel umdl = um.get();

    //         UserDto udto = new UserDto(umdl.getId(), umdl.getFirstName(), umdl.getLastName(), umdl.getEmail());
    //         return udto;

       
    // }


    public UserDto UpdateDataById(UserDto userDto, Integer id) throws Exception{
        Optional<UserModel> um = ur.findById(id);

        if (um.isPresent()) {
            UserModel umdl = um.get();
            umdl.setFirstName(userDto.getFirstName());
            umdl.setLastName(userDto.getLastName());
            umdl.setEmail(userDto.getEmail());

            ur.save(umdl);


            UserDto usd = new UserDto(umdl.getId(), umdl.getFirstName(), umdl.getLastName(), umdl.getEmail());

            return usd;

        } else {
            throw new Exception("Sorry ! We Could not found the id : " +id);
        }
    }


}
