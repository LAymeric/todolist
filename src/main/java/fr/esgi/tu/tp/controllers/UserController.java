package fr.esgi.tu.tp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private String testV = "Hello World !";

    @GetMapping("/test")
    public String test(){
        return  testV;
    }



}
