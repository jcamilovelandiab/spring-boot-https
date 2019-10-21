package com.springboot.https.controllers.entities;

public class User {
    String name;
    String email;
    String password;

    public User(){}
    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
