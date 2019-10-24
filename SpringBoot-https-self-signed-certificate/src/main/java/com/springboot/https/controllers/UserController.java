package com.springboot.https.controllers;

import com.springboot.https.entities.User;
import com.springboot.https.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserServices userServices;

    @GetMapping("/hello")
    public String hello(){
        return "Welcome!!. This is the test page";
    }

    @GetMapping(value={"/","/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value="/signup")
    public ModelAndView signupGet(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signup");
        return modelAndView;
    }

    @PostMapping(value = "/signup")
    public ModelAndView signupPost(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (userServices.findUserByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.user", "This email has been already taken");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");
        } else {
            userServices.saveUser(user);
            modelAndView.addObject("successMessage", "User has been signed up successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("signup");
        }
        return modelAndView;
    }

    @GetMapping(value="/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userServices.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content available to administrators only");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
}
