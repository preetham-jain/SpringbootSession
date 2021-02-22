package com.example.SpringSession.controller;

import com.example.SpringSession.dto.MyRequestDTO;
import com.example.SpringSession.dto.MyResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

    @GetMapping(path = "/hello")
    public String HelloWorld() {
        return "success";
    }

    @PostMapping(path = "/hello-post")
    public String HelloWorldPost() {
        return "success-post";
    }

    @PostMapping(path = "hello-query")
    public String HelloQuery(@RequestParam String query) {
        return "query " + query;
    }

    @PostMapping("register")
    public String HelloRegister(@RequestBody MyRequestDTO request) {
        return request.getFirstName();
    }

    @GetMapping(path = "/employee/{employeeid}")
    public MyResponseDTO GetEmployeeDetails(@PathVariable String employeeid) {
        MyResponseDTO response = new MyResponseDTO();
        response.setId(employeeid);
        return response;
    }
}
