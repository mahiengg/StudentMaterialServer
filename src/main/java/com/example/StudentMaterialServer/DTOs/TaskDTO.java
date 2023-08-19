package com.example.StudentMaterialServer.DTOs;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDTO {

    private Long id;
    private String task;
    private Date task_date;
    private String userName;
    private String firstName;
    private String lastName;

}
