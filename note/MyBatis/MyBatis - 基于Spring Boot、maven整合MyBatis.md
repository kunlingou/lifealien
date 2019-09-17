## 简单示例

### 表结构及数据

```
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_librarian
-- ----------------------------
DROP TABLE IF EXISTS `t_librarian`;
CREATE TABLE `t_librarian` (
  `id` int(11) DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `position` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_librarian
-- ----------------------------
INSERT INTO `t_librarian` VALUES ('11', 'aa', '123123', '10', '北京');
```

### 依赖和配置文件

- pom.xml

```
<dependency>
   <groupId>org.springframework.boot</groupId>  
   <artifactId>spring-boot-starter-web</artifactId>  
</dependency>
<dependency>
   <groupId>mysql</groupId>  
   <artifactId>mysql-connector-java</artifactId>  
</dependency>
<dependency>
   <groupId>org.mybatis.spring.boot</groupId>  
   <artifactId>mybatis-spring-boot-starter</artifactId>  
   <version>1.3.0</version>  
</dependency>
```

- application.yml

```
# Server
server:
  port: 8000

spring:
  # DB
  datasource:
    driverClassName: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis
    username: root
    password: 123456

# mybatis
mybatis:
  type-aliases-package: com.kunlinr.mybatis.entity
  mapperLocations: classpath:mappers/*.xml
```

### 实体

- Librarian

```
package com.kunlinr.mybatis.entity;

public class Librarian {
	
	private Integer id;
	
	private String userName;
	
	private String password;
	
	private Integer age;
	
	private String position;
	
	public Integer getid() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
```

### 持久层

- LibrarianMapper.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.kunlinr.mybatis.dao.LibrarianMapper">
    <resultMap type="Librarian" id="LibrarianMap">
        <result column="user_name" property="userName" jdbcType="VARCHAR" />
    </resultMap>
    <select id="getById" parameterType="INTEGER" resultMap="LibrarianMap">
        select *
        from t_librarian 
        where 1=1
            and id = #{id,jdbcType=INTEGER}
    </select>
     <select id="getAll" parameterType="INTEGER" resultMap="LibrarianMap">
        select *
        from t_librarian
    </select>
</mapper>
```

- LibrarianMapper

```
package com.kunlinr.mybatis.dao;

import java.util.List;

import com.kunlinr.mybatis.entity.Librarian;

public interface LibrarianMapper {
	
	Librarian getById(Integer id);
	
	List<Librarian> getAll();
}
```

### 服务层

- LibrarianService

```
package com.kunlinr.mybatis.service;

import java.util.List;

import com.kunlinr.mybatis.entity.Librarian;

public interface LibrarianService {
	
	Librarian getById(Integer id);
	
	List<Librarian> getAll();
	
}
```

- LibrarianServiceImpl

```
package com.kunlinr.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunlinr.mybatis.dao.LibrarianMapper;
import com.kunlinr.mybatis.entity.Librarian;

@Service
public class LibrarianServiceImpl implements LibrarianService{
	
	@Autowired
    private LibrarianMapper librarianMapper;
	
	@Override
	public Librarian getById(Integer id) {
		return librarianMapper.getById(id);
	}

	@Override
	public List<Librarian> getAll() {
		return librarianMapper.getAll();
	}
	
}
```

### 控制层

- LibrarianController

```
package com.kunlinr.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kunlinr.mybatis.entity.Librarian;
import com.kunlinr.mybatis.service.LibrarianService;

@RestController
@RequestMapping("api/mybatis")
public class LibrarianController {
	
	@Autowired
	private LibrarianService librarianService;
	
	@RequestMapping("getLibrariaById")
	public Librarian getLibrarianInfo(Integer id) {
		return librarianService.getById(id);
	}
	
	@RequestMapping("getAllLibraria")
	public List<Librarian> getLibrarianInfos() {
		return librarianService.getAll();
	}
	
}

```

### 启动类

```
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
```

