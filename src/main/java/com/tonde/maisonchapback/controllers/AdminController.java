package com.tonde.maisonchapback.controllers;


import org.springframework.web.bind.annotation.RequestMapping;

public class AdminController {


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

}
