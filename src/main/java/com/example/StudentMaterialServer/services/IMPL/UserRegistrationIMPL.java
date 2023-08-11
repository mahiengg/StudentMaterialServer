package com.example.StudentMaterialServer.services.IMPL;

import com.example.StudentMaterialServer.DTOs.RegisterUserDTO;
import com.example.StudentMaterialServer.DTOs.UserDTO;
import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.repositories.RegisterNewUser;
import com.example.StudentMaterialServer.services.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationIMPL implements RegisterUserService {

    @Autowired
    private RegisterNewUser registerNewUser;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDTO save(RegisterUserDTO user) {

        RegisterUser newUser = new RegisterUser();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));


        RegisterUser cretaedUser = registerNewUser.save(newUser);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(cretaedUser.getId());
        userDTO.setFirstName(cretaedUser.getFirstName());
        userDTO.setLastName(cretaedUser.getLastName());
        userDTO.setEmail(cretaedUser.getEmail());

        return userDTO;
    }
}
