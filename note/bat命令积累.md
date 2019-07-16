# bat命令积累

##  实例

### 代码的批量拉取、构建和提交

```bash
@echo off
setlocal
title 代码重构

goto np

:np
echo 模块名称:np
cd ./np
git pull
call ./gradleEclipse.bat
goto backend

:backend
echo 模块名称:backend
cd ../gams2-backend
git pull
call ./gradleEclipse.bat
call ./gams2.war/gradleEclipse.bat
set /p a=是否提交backend代码(默认为false,输入y为yes)：
if "%a%"=="y" ( goto pushbackend
) else (goto metadata)

:pushbackend
set /p backendcomment=请输入备注信息：
git add ./gams2.datatrans
git commit -m "%date:~0,4%%date:~5,2%%date:~8,2%-数据迁移-%backendcomment%"
git push
goto metadata

:metadata
echo 模块名称:backend
cd ../gams2-metadata
git pull
goto cli

:cli
echo 模块名称:cli
cd ../gams2-cli
git pull
goto front

:front
echo 模块名称:front
cd ./src/code/normal
git pull
goto end

:end
pause
endlocal
```

### 文件夹合并

- 大佬代码

```bash
@echo on
setlocal
title "元数据合并"

set DATA_HOME=%~dp0
if "%DATA_HOME:~-1,1%" == "\" set PROJ_HOME=%DATA_HOME:~0,-1%


set DATADIR=%DATA_HOME%np_metadata

if exist %DATADIR% (
   rd/s/q %DATADIR%
)

mkdir %DATADIR%

xcopy /y /c /e %DATA_HOME%gams2-metadata %DATADIR%
xcopy /y /c /e %DATA_HOME%gams2-jy-shglxy-metadata %DATADIR%

echo & pause
```

### 新建日志

```
rem 新建日志
@echo off
set fileName=工作清单-苟坤林-%date:~0,4%%date:~5,2%%date:~8,2%.txt
more +6 %~nx0>%cd%\%fileName%
goto :eof
--------------------
.LOG
```
- 效果（日期会在每次点击文件时自动生成）
```
.LOG

9:17 2019/5/27
1.调整数据库jdbc连接方式为spring JDBC。
2.spring JDBC中使用c3p0连接池。
19:33 2019/5/27
人大需求：
--一级公房管理员
1.输入中文名，显示公房和住房（王利明）。
-2.待办事项首页显示太大，调整为图标(您有？条事项待处理)
3.住房房源（输入静园01楼出现两个）--删掉周转房
-- 二级公房管理员
4.信息维护放到系统选项中。
5.人员用房信息(工作证号为职工号)
6.现产权人/使用人，删除该字段。
7.删除二级管理员部分功能
8.预警管理移动到二级管理员-系统管理。
-9.可维护个人信息，在第一次登录时，必须维护个人信息、电话、新旧密码(默认旧密码为123456)。
-10.二级管理员可直接修改密码。不需要旧密码。
-11.业务日期不准确。
-12.手机可以访问该网页，可以进行简单的查询操作。
-13.所有列表内容都居中显示，包括查询。
-14.所有列表需要用户可以翻页和调整每页显示行数。
寄东西-XX
数据迁移流水号有问题(日期)
9:42 2019/5/30

10:57 2019/7/16
```
### 启动服务

```
@echo off
setlocal
title "np_backend"

:Init
set JAVA_CMD="D:\Program Files\Java\JDK1.8\bin\java.exe"
if exist %JAVA_CMD% (
   set VAR_CMD=%JAVA_CMD%
) else set VAR_CMD=java

set VAR_VM_ARGUMENTS="-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=7777"

:Run
%VAR_CMD% %VAR_VM_ARGUMENTS:~1,-1% -jar "%~dp0gams2.war" 

:End
endlocal
```

