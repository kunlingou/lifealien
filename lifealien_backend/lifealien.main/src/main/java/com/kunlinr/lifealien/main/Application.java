package com.kunlinr.lifealien.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kunlinr.lifealien")
@MapperScan("com.kunlinr.lifealien")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
