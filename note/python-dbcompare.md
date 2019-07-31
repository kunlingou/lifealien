# python-dbcompare

### 连接数据库

- 安装客户端，设置环境变量：path:D:\codeworking\11g_plsql\instantclient_12_2x64

- pip install cx_Oracle 

- 第二步:查看oracle数据库编码是否也是utf-8
     select userenv('language') from dual; #输出结果是 AMERICAN_AMERICA.AL32UTF8
    第三步:设置客户机的编码环境:
      如果是在windows下 设置环境变量: NLS_LANG

     设置值为: AMERICAN_AMERICA.AL32UTF8
  --------------------- 





<https://blog.csdn.net/ck3207/article/details/78031505> 



### 打包

```
pip install pyinstaller
pyinstaller -F main.py
```

