package com.springboot.https.services;

import com.springboot.https.entities.User;
import com.springboot.https.repositories.UserRepository;

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

    public User getUserByEmailAndPassword(String email, String password) throws Exception{
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User saveUser(User user) throws Exception {
        return userRepository.save(user);
    }

}
