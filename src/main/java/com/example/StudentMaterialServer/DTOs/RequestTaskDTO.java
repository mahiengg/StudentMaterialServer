package com.example.StudentMaterialServer.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestTaskDTO {


    private String task;
    private Date task_date;
}
