package com.example.StudentMaterialServer.controllers;


import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.UserMaterialList;
import com.example.StudentMaterialServer.repositories.UserRespository;
import com.example.StudentMaterialServer.services.UserMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class MaterialController {

    @Autowired
    private UserMaterialService userMaterialService;

    @Autowired
    private UserRespository userRespository;

    @PostMapping("/addMaterial")
    public ResponseEntity<UserMaterialList> uploadPDFMaterial(Principal principal, @RequestBody UserMaterialList pdfMaterial) {
        System.out.println("userDetails" + principal.getName());
        RegisterUser user = userRespository.getUserByEmail(principal.getName()); //

        System.out.println("userDetails" + user.getEmail());
        System.out.println("pdfMaterial" + pdfMaterial);

        UserMaterialList UploadedPDF  = userMaterialService.uploadPDFMaterial(pdfMaterial, user);
        if(UploadedPDF.getId() == null){
            return new ResponseEntity("PDF is not created... try again", HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok(UploadedPDF);
        }
    }

    @DeleteMapping("/deletePdf")
    public ResponseEntity<?> deleteUserPDFMaterial(Principal principal, @RequestParam(required = false) Long id) {
        System.out.println("userDetails" + principal.getName());
        RegisterUser user = userRespository.getUserByEmail(principal.getName());
        boolean isDeleted = userMaterialService.deleteUserPDFMaterial(id);
        if(isDeleted){
            return new ResponseEntity("Material is deleted successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity("Material is not deleted ", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/materials")
    public List<UserMaterialList> getUserList(Principal principal) {
        RegisterUser user = userRespository.getUserByEmail(principal.getName());
        return userMaterialService.getUserMaterial(user);
    }
}
