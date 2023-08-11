package com.example.StudentMaterialServer.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateDTO {
    private String userName;
    private String passWord;
}
