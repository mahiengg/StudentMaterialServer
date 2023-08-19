package com.example.StudentMaterialServer.services.IMPL;

import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.UserMaterialList;
import com.example.StudentMaterialServer.repositories.MaterialRepository;
import com.example.StudentMaterialServer.services.UserMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserMaterialServiceimpl implements UserMaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    public UserMaterialList uploadPDFMaterial(UserMaterialList pdfMaterial, RegisterUser user) {
        System.out.println(user.getEmail());
        System.out.println(pdfMaterial);
        pdfMaterial.setUser(user);
        return materialRepository.save(pdfMaterial);
    }

    @Override
    public boolean deleteUserPDFMaterial(Long id) {
        Optional<UserMaterialList> optionalMaterial = materialRepository.findById(id);

        if (optionalMaterial.isPresent()) {
            UserMaterialList notesToDelete = optionalMaterial.get();
            materialRepository.delete(notesToDelete);
            return true; // Notes deleted successfully
        } else {
            return false; // Notes not found or could not be deleted
        }

    }

    @Override
    public List<UserMaterialList> getUserMaterial( RegisterUser user) {
        return materialRepository.findByUser(user);
    }
}
