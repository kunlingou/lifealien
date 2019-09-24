package com.kunlinr.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.@MapperScan:该注解配置后不需要在Mapper类上增加注解@Mapper
 * 
 * @author kunlingou
 * @date 2019/09/24
 */
@SpringBootApplication
@MapperScan("com.kunlinr.mybatis.dao")
public class MybatisApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
