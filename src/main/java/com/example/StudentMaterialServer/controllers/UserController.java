package com.example.StudentMaterialServer.controllers;

import com.example.StudentMaterialServer.DTOs.*;
import com.example.StudentMaterialServer.Exceptions.UserAlreadyExistsException;
import com.example.StudentMaterialServer.entity.RefreshToken;
import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.UserMaterialList;
import com.example.StudentMaterialServer.repositories.UserRespository;
import com.example.StudentMaterialServer.services.RefreshTokenService;
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
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
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

    @Autowired
    private RefreshTokenService refreshTokenService;




    @GetMapping("/")
    public String welcome() {
        return "welcome to java ";
    }

    @GetMapping("/welcome")
    public String welcome2(Principal principal) {
        System.out.println("userDetails" + principal.getName());
        return "welcome to java ";
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
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @PostMapping("/authenticate")
    public JwtResponseDTO generateToken(@RequestBody AuthenticateDTO authUser) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authUser
                    .getUserName(), authUser.getPassWord()));
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authUser
                    .getUserName());

            final UserDetails userDetails = userservicee.loadUserByUsername(authUser.getUserName());
            RegisterUser user = userRespository.findFirstByEmail(authUser.getUserName());
           String accessToken =  jwtUtil.generateToken(userDetails.getUsername());
            JwtResponseDTO jwtResponseDTO = new JwtResponseDTO();
            jwtResponseDTO.setUserName(user.getEmail());
            jwtResponseDTO.setFirstName(user.getFirstName());
            jwtResponseDTO.setLastName(user.getLastName());
            jwtResponseDTO.setJwtToken(accessToken);
            jwtResponseDTO.setRefreshToken(refreshToken.getToken());
            return jwtResponseDTO;
        } catch (Exception e) {
            throw new Exception("Invalid User");
        }

    }


    @PostMapping("/refreshToken")
    public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshToken) {
        return refreshTokenService.findByToken(refreshToken.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getRegisterUser)
                .map(userInfo -> {
                    String accessToken = jwtUtil.generateToken(userInfo.getEmail());
                    return JwtResponseDTO.builder()
                            .jwtToken(accessToken)
                            .firstName(userInfo.getFirstName())
                            .lastName(userInfo.getLastName())
                            .userName(userInfo.getEmail())
                            .refreshToken(refreshToken.getRefreshToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException(
                        "Refresh token is not in database!"));
    }
}
