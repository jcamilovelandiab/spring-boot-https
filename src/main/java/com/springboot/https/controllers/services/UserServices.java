package com.springboot.https.controllers.services;

import com.springboot.https.controllers.entities.User;
import com.springboot.https.controllers.repositories.UserRepository;

import java.util.List;

public class UserServices {

    UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String id) throws Exception {
        return userRepository.findById(id);
    }

    public User getUserByEmail(String email) throws Exception {
        return userRepository.findByEmail(email);
    }

}
