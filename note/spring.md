### 理念

- 使现有技术更加实用。

- spring security 、shiro

### 优点

- 轻量级框架
- IOC容器--控制反转
- AOP面向切面编程
- 对事物的支持
- 对框架的支持

### 设置配置文件路径

- program arguments
  - --spring.config.location=D:\work\np\lastest\application.yml
  - --spring.conﬁg.additionallocation=D:\workspace\np_gams2\backend\application-dev.yml

### IOC 控制反转（依赖注入）

- 对象由spring创建。
- 对象属性由spring容器设置。
- 反转：程序变为被动接收spring创建好的对象。
- IOC是一种编程思想。
- IOC容器：BeanFactory

### 配置文件

```
<import resource="entity.xml">
```

