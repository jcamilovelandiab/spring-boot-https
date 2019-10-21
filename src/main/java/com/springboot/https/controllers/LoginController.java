package com.springboot.https.controllers;

import com.springboot.https.entities.User;
import com.springboot.https.services.UserServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;

public class LoginController {

    UserServices userServices;

    @PostMapping("/login")
    public Object Login(@RequestParam User login) throws ServletException {
        if(login.getEmail()==null || login.getPassword()==null){
            throw new ServletException("Please, fill in email and password!");
        }
        User user;
        try {
            user = userServices.getUserByEmailAndPassword(login.getEmail(), login.getPassword());
        } catch (Exception e) {
            throw new ServletException("Invalid Login. User or password is incorrect!");
        }
        return "Valid login. Welcome!";
    }

    @PostMapping("/signup")
    public Object SignUp(@RequestParam User user) throws ServletException {
        try {
            User saveUser = userServices.saveUser(user); saveUser.setPassword(null);
            return saveUser;
        } catch (Exception e) {
            if(e.getMessage().equals("This email does already exist")){
                throw new ServletException("This email has already taken");
            }
            throw new ServletException("An error was occurred. Please try again!");
        }
    }

}
