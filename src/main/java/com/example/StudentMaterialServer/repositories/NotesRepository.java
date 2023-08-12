package com.example.StudentMaterialServer.repositories;

import com.example.StudentMaterialServer.entity.Notes;
import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.UserMaterialList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@EnableJpaRepositories
@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
    List<Notes> findById(Notes notes);

    List<Notes> findByMaterialId(Long materialId);
}
