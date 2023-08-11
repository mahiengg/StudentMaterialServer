package com.example.StudentMaterialServer.services;

import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Userservicee  implements UserDetailsService {

    @Autowired
    private UserRespository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegisterUser user = userRepository.findFirstByEmail(username);

        if(user == null){
            throw  new UsernameNotFoundException("User not found");
        }
        String encodedPassword = user.getPassWord();
        System.out.println(encodedPassword);
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassWord(), new ArrayList<>());
    }
}
