package com.example.SpringSession.service;

import com.example.SpringSession.dto.MyRequestDTO;

public interface UserService {
    boolean updateEmployee(MyRequestDTO request, String id);
}
