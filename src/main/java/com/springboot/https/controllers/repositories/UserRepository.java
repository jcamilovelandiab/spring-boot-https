package com.springboot.https.controllers.repositories;

import com.springboot.https.controllers.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    Map<String, User> users = new HashMap<String,User>();

    public List<User> findAll(){
        return (List<User>) users.values();
    }

    public User findById(String id) throws Exception{
        if(!users.containsKey(id)){
            throw new Exception("This user does not exist.");
        }
        return users.get(id);
    }

    public User findByEmail(String email) throws Exception{
        for (Map.Entry<String, User> entry: users.entrySet()) {
            User user = entry.getValue();
            if(user.getEmail().equals(email)){
                return user;
            }
        }
        throw new Exception("This email is not registered");
    }

}
