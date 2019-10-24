package com.springboot.https.services;

import com.springboot.https.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

@Service("userServices")
public interface UserServices extends UserDetailsService {
    User findUserByEmail(String email);
    User saveUser(User user);
}
