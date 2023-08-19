package com.example.StudentMaterialServer.repositories;

import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.Task;
import com.example.StudentMaterialServer.entity.UserTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@EnableJpaRepositories
@Repository
public interface newTaskRepo  extends JpaRepository<Task, Long>  {
    Task findById(int Id);

    List<Task> findAllByUser(RegisterUser user);

    List<Task> findByTaskDate(Date task_date);

    List<Task> findByTaskDateBetween(Date startDate, Date endDate);

    List<Task> findByTaskDateBetweenAndUser(Date startDate, Date endDate, RegisterUser user);


}





