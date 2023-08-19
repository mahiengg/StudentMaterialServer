package com.example.StudentMaterialServer.controllers;



import com.example.StudentMaterialServer.DTOs.RequestTaskDTO;
import com.example.StudentMaterialServer.DTOs.TaskDTO;
import com.example.StudentMaterialServer.entity.RegisterUser;
import com.example.StudentMaterialServer.entity.Task;
import com.example.StudentMaterialServer.entity.UserTask;
import com.example.StudentMaterialServer.repositories.UserRespository;
import com.example.StudentMaterialServer.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TaskController {


    @Autowired
    private TaskService taskService;

    @Autowired
    private UserRespository userRespository;



    @PostMapping("/task")
    public ResponseEntity<?> addTask(Principal principal, @RequestBody RequestTaskDTO task) throws Exception {
        RegisterUser user = userRespository.getUserByEmail(principal.getName());
        TaskDTO taskAdded = taskService.addUserTask(task, user);
        if(taskAdded.getId() == null){
            return new ResponseEntity("Note is not added... try again", HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok(taskAdded);
        }
    }




    @GetMapping("/usertask")
    public List<Task> getUserTask(Principal principal,  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        System.out.println("date at controller " + date);
        System.out.println("userDetails" + principal.getName());
        RegisterUser user = userRespository.getUserByEmail(principal.getName());
       return taskService.getUserTaskByDate(date, user);
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity<?> deleteUserTask(Principal principal, @RequestParam(required = true) Long id) {
        System.out.println("userDetails" + principal.getName());
        RegisterUser user = userRespository.getUserByEmail(principal.getName());
        boolean isDeleted = taskService.deleteUserTask(id);
        if(isDeleted){
            return new ResponseEntity("Task is deleted successfully", HttpStatus.OK);
        }else{
            return new ResponseEntity("Task is not deleted ", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/task")
    public ResponseEntity<?> updateTask(Principal principal,  @RequestParam (required = true) Long taskId, @RequestBody String taskContent) {
        RegisterUser user = userRespository.getUserByEmail(principal.getName());

        TaskDTO taskUpdated = taskService.updateUserTask(taskId, taskContent, user);
        if(taskUpdated.getId() == null){
            return new ResponseEntity("Note is not updated... try again", HttpStatus.BAD_REQUEST);
        }else{
            return ResponseEntity.ok(taskUpdated);
        }
    }

        @GetMapping("/getAll")
        public List<Task> getAllUserTask(Principal principal){
        RegisterUser user = userRespository.getUserByEmail(principal.getName());
        return  taskService.getAllUserTasks(user);}


        @PutMapping("/updateDate")
            public ResponseEntity<?> updateTaskDate(Principal principal,  @RequestParam (required = true) Long taskId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
                RegisterUser user = userRespository.getUserByEmail(principal.getName());

                TaskDTO taskUpdated = taskService.updateUserTaskDate(taskId, date, user);
                if(taskUpdated.getId() == null){
                    return new ResponseEntity("Note date is not updated... try again", HttpStatus.BAD_REQUEST);
                }else{
                    return ResponseEntity.ok(taskUpdated);
                }
            }
    }




