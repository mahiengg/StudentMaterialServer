package com.example.StudentMaterialServer.repositories;

import com.example.StudentMaterialServer.entity.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserRespository extends JpaRepository<RegisterUser, Integer> {


    RegisterUser findFirstByEmail(String username);
}
