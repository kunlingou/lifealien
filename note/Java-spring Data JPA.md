### Spring Data JPA - Spring Boot

#### 如何保证服务启动时自动构建表结构

- 实体类有注解@Entity，属性有注解@Column。

```java
package com.kunlinr.lifealien.login;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	@Column
    private String userUuid;   //用户UUID
	...
}
```

- 能被扫描到，默认扫描路径为启动包下的所有子包及文件，自定义扫描路径方法如下。

```java
@Configuration
@ComponentScan({
	"com.kunlinr.lifealien.login"
})
@EntityScan({
	"com.kunlinr.lifealien.login"
})
public class LoginConfiguration {}
```

- 启动配置文件：generate-ddl: true

```yaml
spring:
  ...
  # JPA
  jpa:
    database: mysql
    show-sql: true
    generate-ddl: true
    hibernate:
      ddl-auto: update
      naming_strategy: org.hibernate.cfg.ImprovedNamingStrategy
      dialect: org.hibernate.dialect.MySQL5Dialect
```

#### 开启JPA存储库扫描

```java
@EnableJpaRepositories( 
	basePackages = {"com.kunlinr.lifealien.login"}
)
```

#### @EnableJpaRepositories

- 参考：<https://blog.csdn.net/qq_15071263/article/details/79816349> 

```java
@EnableJpaRepositories(
        // basePackages 支持多包扫描，用文本数组的形式就可以
        // 比如这样 {"com.simply.zuozuo.repo","com.simply.zuozuo.mapper"}
        basePackages = {
                "com.simply.zuozuo.repo"
        },
        value = {},
        // 指定里面的存储库类
        basePackageClasses = {},
        // 包含的过滤器
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Repository.class)
        },
        // 不包含的过滤器
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Service.class),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)
        },
        // 通过什么后缀来命名实现类，比如接口A的实现，名字叫AImpl
        repositoryImplementationPostfix = "Impl",
        // named SQL存放的位置，默认为META-INF/jpa-named-queries.properties
        namedQueriesLocation = "",
        // 枚举中有三个值，
        // CREATE_IF_NOT_FOUND，先搜索用户声明的，不存在则自动构建
        // USE_DECLARED_QUERY，用户声明查询
        // CREATE，按照接口名称自动构建查询
        queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND,
        // 指定Repository的工厂类
        repositoryFactoryBeanClass = JpaRepositoryFactoryBean.class,
        // 指定Repository的Base类
        repositoryBaseClass = DefaultRepositoryBaseClass.class,
        // 实体管理工厂引用名称，对应到@Bean注解对应的方法
        entityManagerFactoryRef = "entityManagerFactory",
        // 事务管理工厂引用名称，对应到@Bean注解对应的方法
        transactionManagerRef = "transactionManager",
        // 是否考虑嵌套存储库
        considerNestedRepositories = false,
        // 开启默认事务
        enableDefaultTransactions = true
)
```

