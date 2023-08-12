package com.example.StudentMaterialServer.repositories;

import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.UserMaterialList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface MaterialRepository extends JpaRepository<UserMaterialList, Long> {
    List<UserMaterialList> findByUser(RegisterUser user);

}
