package com.example.StudentMaterialServer.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class JwtResponseDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String jwtToken;
    private String refreshToken;
}
