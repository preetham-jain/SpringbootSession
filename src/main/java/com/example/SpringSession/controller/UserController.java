package com.example.SpringSession.controller;

import com.example.SpringSession.dto.MyRequestDTO;
import com.example.SpringSession.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
        System.out.println("Inside UserController constructor ");
    }

    @PostConstruct
    public void init() {
        System.out.println("Inside UserController PostConstruct");
    }

    @Autowired
    private UserService userService;

    @PostMapping(path = "/update/employee/{id}")
    public boolean UpdateEmployee(@PathVariable String id, @RequestBody MyRequestDTO request) {
        return userService.updateEmployee(request, id);
    }
}
