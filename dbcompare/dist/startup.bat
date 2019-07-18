@echo off
setlocal
title 数据分析
set PATH=".;"%PATH%";D:\codeworking\11g_plsql\instantclient_12_2x64"
call dbcompare.exe
echo 查询完成，是否生成分析报告？
pause
endlocal