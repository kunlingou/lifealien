## Win10 OS安装（配置）MySQL 5.7（解压版）

### 下载及解压

- 下载地址：<https://dev.mysql.com/downloads/mysql/5.7.html#downloads>  

- 文件名：mysql-5.7.27-win32.zip

- zip是解压版，msi是安装版，本教程仅说明zip格式的配置方法。

- 解压（假设解压后根路径为D:\ide\mysql-5.7.27-win32）

- 相关截图

![1567298466680](https://img2018.cnblogs.com/blog/1722725/201909/1722725-20190901093659726-1161363465.png)

![1567298515431](https://img2018.cnblogs.com/blog/1722725/201909/1722725-20190901093735926-176880804.png)

### 添加环境系统变量

- [Windows-如何打开环境变量管理界面](https://www.cnblogs.com/kunlingou/p/11441389.html)

- path 增加D:\ide\mysql-5.7.27-win32\bin

### 配置my.ini

- 新建文件my.ini，内容如下，将该文件放到D:\ide\mysql-5.7.27-win32下。
- 注：需修改文件中的basedir、datadir值；路径中斜杠改为左斜杠“/”。

```
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8 
[mysqld]
#设置3306端口
port = 3306 
# 设置mysql的安装目录
basedir=D:/ide/mysql-5.7.27-win32
# 设置mysql数据库的数据的存放目录
datadir=D:/ide/mysql-5.7.27-win32/data
# 允许最大连接数
max_connections=200
# 服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
# 默认时区
default-time-zone='+08:00'
```

### 安装

- 以管理员身份打开cmd窗口,执行以下命令：

```
mysqld -install mysql
```

- 显示`Service successfully installed.`则表示安装成功。
- 如果报错**缺少相关dll文件**，安装下面安装包即可。(微软常用运行库合集)
  - 链接：https://pan.baidu.com/s/1dR6AGvG0piym7gurBkrHXA  提取码：w2rv
- 其他命令：

```
mysqld -install mysql --defaults-file=D:\ide\mysql-5.7.27-win32\my.ini
mysqld -install mysql --defaults-file=D:\ide\mysql-5.7.27-win32\my.ini
mysqld --install --defaults-file=D:\ide\mysql-5.7.27-win32\my.ini
```

### 初始化

```
mysqld --initialize-insecure --user=mysql 
```

- 其他命令

```
mysqld --initialize-insecure --user=mysql 
mysqld --initialize --user=mysql 
mysqld –initialize –console
```


### 启动

```
net start mysql
```
- 启动成功显示内容
```
mysql 服务正在启动 .
mysql 服务已经启动成功。
```
- 登录mysql并修改密码，密码默认为空。
```
mysql -u root -p
set password = password("新密码");
```

### 卸载

- mysqld -remove [服务名]

```
mysqld -remove mysql
```

### 其他问题

- The server time zone value 'ÖÐ¹ú±ê×¼Ê±¼ä' is unrec

```
SHOW VARIABLES LIKE '%time_zone%';
SET GLOBAL time_zone='+8:00';
```

