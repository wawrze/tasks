call runcrud.bat

if "%ERRORLEVEL%" == "0" goto runopera
echo.
echo Error in runcrud.bat, aborting...
goto fail

:runopera
c:\PROGRA~2\Opera\54.0.2952.54\opera.exe http://localhost:8090/crud/v1/task/getTasks
goto end

:fail
echo.
echo Not finished - errors occurred

:end
echo.
echo Work done.