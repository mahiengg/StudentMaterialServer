package com.example.StudentMaterialServer;

import com.example.StudentMaterialServer.entity.User;
import com.example.StudentMaterialServer.repositories.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class StudentMaterialServerApplication {

	@Autowired
	private UserRespository repository;

	public static void main(String[] args) {
		SpringApplication.run(StudentMaterialServerApplication.class, args);



	}

}
