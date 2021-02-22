package com.example.SpringSession.service.impl;

import com.example.SpringSession.dto.MyRequestDTO;
import com.example.SpringSession.service.UserService;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserServiceimpl implements UserService {

    public UserServiceimpl() {
        System.out.println("Inside UserService constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("Inside UserService Postconstruct");
    }

    @Override
    public boolean updateEmployee(MyRequestDTO request, String id) {
        System.out.println("Inside user service " + request + "id " + id);
        return false;
    }
}
