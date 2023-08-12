package com.example.StudentMaterialServer.services;


import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.UserMaterialList;

import java.util.List;

public interface UserMaterialService {

    public UserMaterialList uploadPDFMaterial(UserMaterialList pdfMaterial, RegisterUser user);

    public List<UserMaterialList> getUserMaterial( RegisterUser user);
}
