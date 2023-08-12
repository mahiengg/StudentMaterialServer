package com.example.StudentMaterialServer.controllers;


import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.UserMaterialList;
import com.example.StudentMaterialServer.repositories.UserRespository;
import com.example.StudentMaterialServer.services.UserMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class MaterialController {

  @Autowired
  private UserMaterialService userMaterialService;

  @Autowired
  private UserRespository userRespository;

    @PostMapping("/addMaterial")
    public UserMaterialList uploadPDFMaterial(Principal principal, @RequestBody UserMaterialList pdfMaterial) {
         System.out.println("userDetails" + principal.getName());
        RegisterUser user = userRespository.getUserByEmail(principal.getName()); //

        System.out.println("userDetails" + user.getEmail());
        System.out.println("pdfMaterial"+ pdfMaterial);

        return userMaterialService.uploadPDFMaterial(pdfMaterial, user);
    }

    @GetMapping ("/materials")
    public List<UserMaterialList> getuserList(Principal principal){
        RegisterUser user = userRespository.getUserByEmail(principal.getName());
       return userMaterialService.getUserMaterial(user);
    }
}
