### 安装（5.7解压版）

#### 下载

- 下载地址：<http://dev.mysql.com/downloads/mysql/>  

- msi是安装包

- 解压（假设解压后根路径为D:\ide\mysql-5.7.19-winx64）

#### 添加环境系统变量

- path 增加D:\ide\mysql-5.7.19-winx64\bin

#### 配置my.ini

- 将该文件放到D:\ide\mysql-5.7.19-winx64\bin下

```
[mysql]
# 设置mysql客户端默认字符集
default-character-set=utf8 
[mysqld]
#设置3306端口
port = 3306 
# 设置mysql的安装目录
basedir=D:\ide\mysql-5.7.19-winx64
# 设置mysql数据库的数据的存放目录
datadir=D:\ide\mysql-5.7.19-winx64\data
# 允许最大连接数
max_connections=200
# 服务端使用的字符集默认为8比特编码的latin1字符集
character-set-server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
```

#### 安装

- mysqld -install mysql --defaults-file=D:\ide\mysql-5.7.19-winx64\bin\my.ini

- mysqld -install mysql --defaults-file=E:\software\mysql-5.7.19-winx64\bin\my.ini

#### 卸载

- mysqld -remove [服务名]

#### 初始化

- mysqld --initialize-insecure --user=mysql 
- mysqld --initialize --user=mysql 
- 保存打印出的初始密码

#### 启动

- net start mysql
- mysql -u root -p
- set password = password("新密码")
#### bianry

- INSERT INTO test_bin(bin_id) VALUES(UNHEX('FA34E10293CB42848573A4E39937F479')); 
- INSERT INTO test_bin(bin_id) VALUES(UNHEX(?)); 或 
- INSERT INTO test_bin(bin_id) VALUES(x'FA34E10293CB42848573A4E39937F479'); 

- SELECT HEX(bin_id) AS bin_id FROM test_bin;
- SELECT HEX(bin_id) AS bin_id FROM test_bin WHERE bin_id = UNHEX('FA34E10293CB42848573A4E39937F479'); 
- SELECT HEX(bin_id) AS bin_id FROM test_bin WHERE bin_id = UNHEX(?); 
- 
SELECT HEX(bin_id) AS bin_id FROM test_bin WHERE bin_id = x'FA34E10293CB42848573A4E39937F479';   

- CREATE FUNCTION uu_id() RETURNS binary(16) RETURN UNHEX(REPLACE(UUID(),'-','')); 或
-  CREATE FUNCTION uu_id() RETURNS binary(16) RETURN UNHEX(REVERSE(REPLACE(UUID(),'-',''))); 使用： INSERT INTO test_bin(bin_id) VALUES(uu_id()); 

### 公式

#### 随机生成一个uuid

- replace(uuid(),'-','')

#### HEX和UNHEX

- unhex("16进制字符串")
- hex("二进制")