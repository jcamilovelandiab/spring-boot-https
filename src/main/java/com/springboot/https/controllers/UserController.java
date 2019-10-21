package com.springboot.https.controllers;

import com.springboot.https.controllers.entities.User;
import com.springboot.https.controllers.services.UserServices;
import org.springframework.web.bind.annotation.*;

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
    public User getUser(@PathVariable String userId) throws Exception {
        return userServices.getUserById(userId);
    }

}
