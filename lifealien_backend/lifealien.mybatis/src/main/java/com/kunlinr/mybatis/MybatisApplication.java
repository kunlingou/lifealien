package com.kunlinr.mybatis;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kunlinr.mybatis.dao") //该注解配置后不需要在Mapper类上增加注解@Mapper
public class MybatisApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}
	
}
