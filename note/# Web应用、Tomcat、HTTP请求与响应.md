# Web应用、Tomcat、HTTP请求与响应

## 软件体系结构
### 常用软件体系结构

#### C/S

- C/S即客户端/服务器(Client/Server)，例如QQ；
- 需要编写服务器端程序，以及客户端程序；
- 缺点：软件更新需要同时更新客户端和服务器端；
- 优点：安全性好。

#### B/S

- B/S即浏览器/服务器(Browser/Server)；

- 优点：只需要编写服务器端程序；

- 缺点：安全性差。

### Web资源

#### Web资源介绍

- html：静态资源；(浏览器可以看懂)
- JSP/Servlet：动态资源。(需要先转换成html，浏览器才可以看懂)

  - 可以有变量。

#### 访问Web资源

- 协议名://域名:端口号/路径

###  Web服务器

- 作用：接收客户端的请求，给客户端作出响应。
- Web服务器和JSP/Servlet容器
- 常用服务器：Tomcat（Apache）、JBoss（Redhat红帽）、GlassFish（Oracle）、Resin（Caucho）、Weblogic（Orcale付费）、Websphere（IBM付费）

## Http请求与响应

### Http协议

- 协议：协议的甲乙双方，就是客户端(浏览器)和服务器！理解成双方通信的格式。http协议是无状态协议，请求直接没有联系。

  - 请求协议：
  - 响应协议：
- 请求协议

```
请求行
多个请求头信息：头名称:头值
空行
请求体
```

- 响应协议

```
响应行(协议、版本 状态码 状态码的解析)
响应头：头名称:头值
空格
请求体
```

	- Content-Type：响应内容的MIME类型

## Web Service和Servlet区别

### Servlet

- java专有的服务器端技术。
- servlet定义的是一套Interface，目的是实现java在web上的动态访问(http协议、ftp协议等)。

### WebService

- 与语言无关。
- 一套标准(SOAP/WDDL/WSDL...比API更抽象)，与语言、协议、平台无关，目的是实现service的组件。

