package com.springboot.https.controllers;

import com.springboot.https.entities.Role;
import com.springboot.https.entities.User;
import com.springboot.https.repositories.RoleRepository;
import com.springboot.https.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/hello")
    public String hello(){
        return "Welcome!!. This is the test page";
    }

    @GetMapping(value={"/","/login"})
    public ModelAndView loginGet(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping(value="/login")
    public ModelAndView loginPost(@Valid User user, BindingResult bindingResult){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User userdb = userServices.findUserByEmail(user.getEmail());
        if (userdb == null || !userdb.getPassword().equals(user.getPassword())) {
            bindingResult.rejectValue("email", "error.user", "Invalid Login");
        }
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage", "Invalid login!");
            modelAndView.setViewName("login");
        } else {
            modelAndView.addObject("successMessage", "Login was successful!");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("login");
        }
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
    public ModelAndView signupPost(@Valid User user, BindingResult bindingResult) throws Exception {

        Role roleAdmin = new Role(new Long(1), "ADMIN");
        if(roleRepository.findByRole("ADMIN")==null){
            roleRepository.save(roleAdmin);
        }

        ModelAndView modelAndView = new ModelAndView();
        if (userServices.findUserByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.user", "This email has been already taken");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("errorMessage", "This email has been already taken!");
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
