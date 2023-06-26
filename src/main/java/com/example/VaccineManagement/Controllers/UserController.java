package com.example.VaccineManagement.Controllers;

import com.example.VaccineManagement.Dtos.UpdateEmailDto;
import com.example.VaccineManagement.Models.User;
import com.example.VaccineManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add-user")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);

    }

    @GetMapping("/get-vaccinationDate")
    public Date getVaccinationDate(@RequestParam("userId") Integer userId){
        return userService.getVaccinationDate(userId);
    }
    @PutMapping("/updateEmail")
    public String updateEmail(@RequestBody UpdateEmailDto updateEmailDto){
        return userService.updateEmail(updateEmailDto);
    }
    @GetMapping("/getUserByEmail/{emailId}")
    public User getByEmail(@PathVariable("emailId")String email){
        return userService.getUserByEmail(email);
    }
}