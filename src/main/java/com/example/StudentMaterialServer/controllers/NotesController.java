package com.example.StudentMaterialServer.controllers;


import com.example.StudentMaterialServer.DTOs.NotesDTO;
import com.example.StudentMaterialServer.entity.Notes;
import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.repositories.UserRespository;
import com.example.StudentMaterialServer.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class NotesController {


    @Autowired
    NotesService notesService;

    @Autowired
    private UserRespository userRespository;

    @PostMapping("/addNotes")
    public NotesDTO addNotes(Principal principal, @RequestBody NotesDTO notes) {
        System.out.println("userDetails" + principal.getName());
        RegisterUser user = userRespository.getUserByEmail(principal.getName()); //

        System.out.println("userDetails" + user.getEmail());
        System.out.println("notes"+ notes);

        return notesService.addNotes(notes, user);
    }

    @GetMapping("/notes")
    public List<Notes> getMaterialNotes(@RequestParam(required = false) Long id){

        return notesService.getMaterialNotes(id);
    }
}
