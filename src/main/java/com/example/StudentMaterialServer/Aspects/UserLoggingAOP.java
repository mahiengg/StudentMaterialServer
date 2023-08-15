package com.example.StudentMaterialServer.Aspects;

import com.example.StudentMaterialServer.DTOs.AuthenticateDTO;
import com.example.StudentMaterialServer.DTOs.JwtResponseDTO;
import com.example.StudentMaterialServer.DTOs.UserDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class UserLoggingAOP {

    @Before(value = "execution(* com.example.StudentMaterialServer.controllers.UserController.*(..))")
    public void beforeAdvice(JoinPoint joinPoint){
        System.out.println("Request to "+ joinPoint.getSignature() + " is invoked at  "+ new Date());
    }

    @After(value = "execution(* com.example.StudentMaterialServer.controllers.UserController.registerUser(..))")
    public void afterSaveUserAdvice(JoinPoint joinPoint){
        System.out.println("User " + " is saved successfully at  "+ new Date());
    }

    @After(value = "execution(* com.example.StudentMaterialServer.controllers.UserController.generateToken(..))")
    public void afterLoginAuthenticatedAdvice(JoinPoint joinPoint){
        Object[] lArgs = joinPoint.getArgs();
        AuthenticateDTO authUser = (AuthenticateDTO) lArgs[0];
        System.out.println("User "+ authUser.getUserName() + " token is generated at  "+ new Date());
    }

    @After(value = "execution(* com.example.StudentMaterialServer.controllers.UserController.*(..))")
    public void afterAdvice(JoinPoint joinPoint){
        System.out.println("Request to "+ joinPoint.getSignature() + " is ended at  "+ new Date());
    }
}
