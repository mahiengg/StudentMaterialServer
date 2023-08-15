package com.example.StudentMaterialServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class StudentMaterialServerApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return  new  WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedHeaders("*").allowedOriginPatterns("*")
						.allowedMethods("*").allowCredentials(false);
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(StudentMaterialServerApplication.class, args);





	}

}
