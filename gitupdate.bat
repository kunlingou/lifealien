@echo off
setlocal
title 代码重构

goto lifealien

:lifealien
echo 模块名称:lifealien
git pull
set /p a=是否提交backend代码(默认为false,输入y为yes)：
if "%a%"=="y" ( goto pushlifealien
) else (goto end)

:pushlifealien
set /p lifealiencomment=请输入备注信息：
git add .
git commit -m "%date:~0,4%%date:~5,2%%date:~8,2%-%lifealiencomment%"
git push
goto end

:end
pause
endlocal