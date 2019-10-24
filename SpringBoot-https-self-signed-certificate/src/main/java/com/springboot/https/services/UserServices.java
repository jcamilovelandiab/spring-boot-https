package com.springboot.https.services;

import com.springboot.https.entities.Role;
import com.springboot.https.entities.User;
import com.springboot.https.repositories.RoleRepository;
import com.springboot.https.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * A class used to represent the service the login and register
 */
@Service("userService")
public class UserServices {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    /**
     * @param userRepository user repository injection
     * @param roleRepository role repository injection
     * @param bCryptPasswordEncoder Injection class that allows us to encrypt the password
     */
    @Autowired
    public UserServices(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    /**
     *
     * @param email   that will be consulted to recognize if there are db
     * @return User that has a email equals that param email
     */
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * @param user
     */
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setIdRole(userRole.getId());
        userRepository.save(user);
    }

}
