package com.Test.testUser.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Test.testUser.DTO.UserDto;
import com.Test.testUser.Service.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/testuser")
public class UserControl {

    @Autowired
    private UserService us;

    @PostMapping()
    public UserDto PostData(@Valid @RequestBody UserDto userDto) {

        return us.PostData(userDto);
    }

    @GetMapping()
    public List<UserDto> GetAllData() {
        return us.GetAllData();
    }

    @GetMapping("/{id}")
    public UserDto getDataById(@PathVariable Integer id) throws Exception {
        return us.getDataById(id);
    }
    // @GetMapping("/{id}")                        // WithoutException Handling
    // public UserDto getDataById(@PathVariable Integer id) {
    //     return us.getDataById(id);
    // }

    @PutMapping("/{id}")
    public UserDto UpdateDataById(@PathVariable Integer id, @RequestBody UserDto userDto) throws Exception {
        return us.UpdateDataById(userDto, id);
    }

    

}
