@ECHO OFF

CALL run/check_dependencies.bat

WHERE npm >NUL 2>NUL
IF %ERRORLEVEL% NEQ 0 (
    echo "Command 'npm' does not exist. Install NodeJS including the Node Package Manager (NPM)."
    exit /b %errorlevel%
)

WHERE clang-format >NUL 2>NUL
IF %ERRORLEVEL% NEQ 0 (
    echo"Command 'clang-format' does not exist. Install Clang Format."
    exit /b %errorlevel%
)
