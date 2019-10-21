package com.springboot.https.controllers;

import com.springboot.https.entities.User;
import com.springboot.https.services.UserServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserServices userServices;

    @GetMapping("/hello")
    public String hello(){
        return "Hello!. Welcome!!";
    }

    @GetMapping
    public List<User> getUsers(){
        return userServices.getUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) throws ServletException {
        try {
            return userServices.getUserById(userId);
        } catch (Exception e) {
            throw new ServletException("This user does not exist");
        }
    }



}
