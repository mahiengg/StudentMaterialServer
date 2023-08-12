package com.example.StudentMaterialServer.controllers;

import com.example.StudentMaterialServer.DTOs.AuthenticateDTO;
import com.example.StudentMaterialServer.DTOs.RegisterUserDTO;
import com.example.StudentMaterialServer.DTOs.UserDTO;
import com.example.StudentMaterialServer.Exceptions.UserAlreadyExistsException;
import com.example.StudentMaterialServer.entity.AuthenticateUser;
import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.repositories.UserRespository;
import com.example.StudentMaterialServer.services.RegisterUserService;
import com.example.StudentMaterialServer.services.Userservicee;
import com.example.StudentMaterialServer.util.jwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private jwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Userservicee userservicee;

    @Autowired
    private UserRespository userRespository;


    @GetMapping("/")
    public String welcome(){
        return  "welcome to java ";
    }

    @GetMapping("/welcome")
    public String welcome2(){
        return  "welcome to java ";
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDTO newUser) throws Exception {
        System.out.println(newUser);
        try {
            UserDTO user = this.registerUserService.save(newUser);
            System.out.println(user);
            if (user == null) {
                return new ResponseEntity("User not created... try again", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity("User created successfully", HttpStatus.CREATED);
        }catch (UserAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }




    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthenticateDTO authUser) throws Exception {
        System.out.println("hshhshs "+ authUser.getUserName());
        System.out.println("hshhshs "+ authUser.getPassWord());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authUser.getUserName(), authUser.getPassWord())
            );
        } catch (Exception e) {
            throw new Exception("Invalid User");
        }
        final UserDetails userDetails = userservicee.loadUserByUsername(authUser.getUserName());
        RegisterUser user = userRespository.findFirstByEmail(authUser.getUserName());
        System.out.println("UserName "+ userDetails.getUsername());

       // final String jwt = jwtUtil.generateToken(userDetails);
        return jwtUtil.generateToken(userDetails.getUsername());


}
}
