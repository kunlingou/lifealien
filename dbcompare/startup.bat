@echo on
setlocal
title 数据分析
set PATH=%PATH%;D:\codeworking\11g_plsql\instantclient_12_2x64
set ROOT_PATH=%~dp0
rem echo %PATH%
echo 正在查询结果...
dbcompare.exe
echo 查询完成！路径为：%ROOT_PATH%result
set /p a=是否生成分析报告？(默认为false,输入y为yes)：
if "%a%"=="y" ( goto analysis
) else (goto end)

:analysis
echo 正在生成分析报告...
call dataanalysis.exe
echo 生成完成！
goto end

:end
pause
endlocal