package com.example.StudentMaterialServer.services;

import com.example.StudentMaterialServer.DTOs.NotesDTO;
import com.example.StudentMaterialServer.DTOs.RegisterUserDTO;
import com.example.StudentMaterialServer.DTOs.UserDTO;
import com.example.StudentMaterialServer.entity.Notes;
import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.UserMaterialList;

import java.util.List;

public interface NotesService {



    NotesDTO addNotes(NotesDTO notes, RegisterUser user);

    List<Notes> getMaterialNotes(Long materialId);
}
