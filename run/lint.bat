REM TODO: format c++ code automatically. see lint.sh for reference.

REM perform spell check on all source files
CALL node_modules\.bin\cspell.cmd cpp\*.h cpp\*.cpp src\**\*.java samples\**\*.java || exit /b %errorlevel%
