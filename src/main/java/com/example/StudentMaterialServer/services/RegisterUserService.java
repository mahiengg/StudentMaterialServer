package com.example.StudentMaterialServer.services;

import com.example.StudentMaterialServer.DTOs.RegisterUserDTO;
import com.example.StudentMaterialServer.DTOs.UserDTO;
import org.springframework.stereotype.Service;


public interface RegisterUserService {

    public UserDTO save(RegisterUserDTO user);
}
