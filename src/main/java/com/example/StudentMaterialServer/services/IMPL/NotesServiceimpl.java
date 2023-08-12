package com.example.StudentMaterialServer.services.IMPL;


import com.example.StudentMaterialServer.DTOs.NotesDTO;
import com.example.StudentMaterialServer.entity.Notes;
import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.repositories.MaterialRepository;
import com.example.StudentMaterialServer.repositories.NotesRepository;
import com.example.StudentMaterialServer.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceimpl implements NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private MaterialRepository materialRepository;



    @Override
    public NotesDTO addNotes(NotesDTO notes, RegisterUser user) {



        Notes addNote = new Notes();
        addNote.setId(notes.getId());
        addNote.setNoteTitle(notes.getNoteTitle());
        addNote.setNoteContent(notes.getNoteContent());
        addNote.setUser(user);
        addNote.setMaterialId(notes.getMaterialId());

        Notes newNoteAdded = notesRepository.save(addNote);

        NotesDTO notesDTO = new NotesDTO();
        notesDTO.setId(newNoteAdded.getId());
        notesDTO.setNoteContent(newNoteAdded.getNoteContent());
        notesDTO.setNoteTitle(newNoteAdded.getNoteTitle());
        notesDTO.setMaterialId(newNoteAdded.getMaterialId());
        return notesDTO;
    }

    @Override
    public List<Notes> getMaterialNotes(Long materialId) {


         return notesRepository.findByMaterialId(materialId);
    }
}
