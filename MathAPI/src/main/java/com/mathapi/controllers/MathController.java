package com.mathapi.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
public class MathController {

    @GetMapping("/sum/{numberA}/{numberB}")
    public String sum(@PathVariable Integer numberA,@PathVariable Integer numberB){
        return String.valueOf(numberA+numberB);
    }

}
