package com.springboot.https.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;

import javax.servlet.ServletException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * This class has mathServices which is the service from the other API.
 */
@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    RestTemplate mathServices;

    @GetMapping
    public String hello(){
        return "Welcome!!. This is an API!";
    }

    @GetMapping("/sum/{numberA}/{numberB}")
    public String sumInteger(@PathVariable Integer numberA, @PathVariable Integer numberB) throws ServletException {
        try {
            String channel = "https://localhost:8444/operation";
            System.out.println("CHANNEL COMMUNICATION: "+channel);
            return mathServices.getForObject(new URI(channel+"/sum/"+numberA+"/"+numberB), String.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new ServletException("An unexpected error has occurred. Please try again!");
        }
    }

}
