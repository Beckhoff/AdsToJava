@ECHO OFF

IF EXIST dist (
    rmdir /q /s dist || exit /b %errorlevel%
)
IF EXIST build (
    rmdir /q /s build || exit /b %errorlevel%
)
IF EXIST build_win32 (
    rmdir /q /s build_win32 || exit /b %errorlevel%
)
IF EXIST build_x64 (
    rmdir /q /s build_x64 || exit /b %errorlevel%
)
IF EXIST target (
    rmdir /q /s target || exit /b %errorlevel%
)
