@echo off
setlocal
title �����ع�

goto lifealien

:lifealien
echo ģ������:lifealien
git pull
set /p a=�Ƿ��ύbackend����(Ĭ��Ϊfalse,����yΪyes)��
if "%a%"=="y" ( goto pushlifealien
) else (goto end)

:pushlifealien
set /p lifealiencomment=�����뱸ע��Ϣ��
git add .
git commit -m "%date:~0,4%%date:~5,2%%date:~8,2%-%lifealiencomment%"
git push
goto end

:end
pause
endlocal