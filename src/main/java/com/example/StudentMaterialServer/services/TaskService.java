package com.example.StudentMaterialServer.services;


import com.example.StudentMaterialServer.DTOs.RequestTaskDTO;
import com.example.StudentMaterialServer.DTOs.TaskDTO;
import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.Task;
import com.example.StudentMaterialServer.entity.UserTask;

import java.util.Date;
import java.util.List;

public interface TaskService {
    TaskDTO addUserTask(RequestTaskDTO task, RegisterUser user);

    boolean deleteUserTask(Long id);

    List<Task> getUserTaskByDate(Date date, RegisterUser user);

    List<Task> getAllUserTasks(RegisterUser user);


    TaskDTO updateUserTask(Long id, String taskContent, RegisterUser user);

    TaskDTO updateUserTaskDate(Long taskId, Date date, RegisterUser user);
}
