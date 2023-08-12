package com.example.StudentMaterialServer.DTOs;


import lombok.Data;

@Data
public class NotesDTO {

    private Long id;
    private String noteTitle;
    private String noteContent;
    private Long materialId;
}
