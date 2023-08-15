package com.example.StudentMaterialServer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class RefreshToken {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String token;
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private RegisterUser registerUser;
}
