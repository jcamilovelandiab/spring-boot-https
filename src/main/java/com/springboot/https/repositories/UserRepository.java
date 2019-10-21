package com.springboot.https.repositories;

import com.springboot.https.entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserRepository {
    Map<String, User> users = new HashMap<String, User>();
    Map<String, String> EmailToId = new HashMap<String, String>();

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
        if(!EmailToId.containsKey(email)) throw new Exception("This user is not registered");
        String id = EmailToId.get(email);
        if(!users.containsKey(id)) throw new Exception("This user is not registered");
        return users.get(id);
    }

    public User findByEmailAndPassword(String email, String password) throws Exception {
        if(!EmailToId.containsKey(email)) throw new Exception("This user is not registered");
        String id = EmailToId.get(email);
        if(!users.containsKey(id)) throw new Exception("This user is not registered");
        User user = users.get(id);
        if(user.getEmail().equals(email) && user.getPassword().equals(password)){
            return user;
        }
        throw new Exception("This user is not registered");
    }

    public User save(User user) throws Exception {
        if(EmailToId.containsKey(user.getEmail())){
            throw new Exception("This email does already exist");
        }
        String uniqueID = UUID.randomUUID().toString();
        user.setId(uniqueID);
        if(users.containsKey(uniqueID)){
            throw new Exception("An error was occurred. Please try again.");
        }

        users.put(user.getId(), user);
        EmailToId.put(user.getEmail(), user.getId());
        return users.get(user.getId());
    }

}
