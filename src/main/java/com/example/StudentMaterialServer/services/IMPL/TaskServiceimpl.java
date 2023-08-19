package com.example.StudentMaterialServer.services.IMPL;


import com.example.StudentMaterialServer.DTOs.NotesDTO;
import com.example.StudentMaterialServer.DTOs.RequestTaskDTO;
import com.example.StudentMaterialServer.DTOs.TaskDTO;
import com.example.StudentMaterialServer.entity.*;
import com.example.StudentMaterialServer.repositories.TaskRepository;
import com.example.StudentMaterialServer.repositories.newTaskRepo;
import com.example.StudentMaterialServer.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceimpl implements TaskService {

    @Autowired
    private  newTaskRepo taskRepository;




    @Override
    public TaskDTO addUserTask(RequestTaskDTO task, RegisterUser user) {

          Task addTask = new Task();
            addTask.setTaskDate(task.getTask_date());
            addTask.setTask_content(task.getTask());
            addTask.setUser(user);
            Task taskModified = taskRepository.save(addTask);
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(taskModified.getId());
            taskDTO.setTask_date(taskModified.getTaskDate());
            taskDTO.setTask(taskModified.getTask_content());
            taskDTO.setFirstName(taskModified.getUser().getFirstName());
            taskDTO.setLastName(taskModified.getUser().getLastName());
            taskDTO.setUserName(taskModified.getUser().getEmail());
            return taskDTO;

    }

    @Override
    public boolean deleteUserTask(Long id) {
        Optional<Task> optionalMTask = taskRepository.findById(id);

        if (optionalMTask.isPresent()) {
            Task taskToDelete = optionalMTask.get();
            taskRepository.delete(taskToDelete);
            return true; // Notes deleted successfully
        } else {
            return false; // Notes not found or could not be deleted
        }
    }



    @Override
    public List<Task> getUserTaskByDate(Date date, RegisterUser user) {
        System.out.println("date at service  "+ date);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate startOfWeek = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endOfWeek = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
        System.out.println(localDate.toString() + startOfWeek.toString() + endOfWeek.toString());

        System.out.println(  Date.from(startOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        System.out.println(  Date.from(endOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return taskRepository.findByTaskDateBetweenAndUser(Date.from(startOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant()),Date.from(endOfWeek.atStartOfDay(ZoneId.systemDefault()).toInstant()), user);
    }

    @Override
    public List<Task> getAllUserTasks(RegisterUser user) {
        return  taskRepository.findAllByUser(user);
    }

    @Override
    public TaskDTO updateUserTask(Long id,  String taskContent, RegisterUser user) {

        Task existingTask =  taskRepository.findById(id).get();


        existingTask.setTaskDate(new Date());
        existingTask.setTask_content(taskContent);
        existingTask.setUser(user);
            Task taskModified = taskRepository.save(existingTask);
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(taskModified.getId());
            taskDTO.setTask_date(taskModified.getTaskDate());
            taskDTO.setTask(taskModified.getTask_content());
            taskDTO.setFirstName(taskModified.getUser().getFirstName());
            taskDTO.setLastName(taskModified.getUser().getLastName());
            taskDTO.setUserName(taskModified.getUser().getEmail());
            return taskDTO;

    }

    @Override
    public TaskDTO updateUserTaskDate(Long taskId, Date date, RegisterUser user) {
        Task existingTask =  taskRepository.findById(taskId).get();
            existingTask.setTaskDate(date);
            Task taskModified = taskRepository.save(existingTask);
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(taskModified.getId());
            taskDTO.setTask_date(taskModified.getTaskDate());
            taskDTO.setTask(taskModified.getTask_content());
            taskDTO.setFirstName(taskModified.getUser().getFirstName());
            taskDTO.setLastName(taskModified.getUser().getLastName());
            taskDTO.setUserName(taskModified.getUser().getEmail());
            return taskDTO;
        }
    }

