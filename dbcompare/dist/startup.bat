@echo on
setlocal
title ���ݷ���
set PATH=%PATH%;D:\codeworking\11g_plsql\instantclient_12_2x64
set ROOT_PATH=%~dp0
rem echo %PATH%
echo ���ڲ�ѯ���...
dbcompare.exe
echo ��ѯ��ɣ�·��Ϊ��%ROOT_PATH%result
set /p a=�Ƿ����ɷ������棿(Ĭ��Ϊfalse,����yΪyes)��
if "%a%"=="y" ( goto analysis
) else (goto end)

:analysis
echo �������ɷ�������...
call dataanalysis.exe
echo ������ɣ�
goto end

:end
pause
endlocal