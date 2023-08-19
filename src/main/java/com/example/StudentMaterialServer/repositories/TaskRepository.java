package com.example.StudentMaterialServer.repositories;

import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@EnableJpaRepositories
@Repository
public interface TaskRepository  extends JpaRepository<UserTask, Long>  {
    UserTask findById(int Id);

    UserTask findByUser(RegisterUser user);

   // List<UserTask> findByTaskDate(Date task_date);


}





