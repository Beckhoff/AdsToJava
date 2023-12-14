@ECHO OFF

WHERE java >NUL 2>NUL
IF %ERRORLEVEL% NEQ 0 (
    echo "Command 'java' does not exist. Install OpenJDK or some other Java Development Kit."
    exit /b %errorlevel%
)

WHERE javac >NUL 2>NUL
IF %ERRORLEVEL% NEQ 0 (
    echo "Command 'javac' does not exist. Install OpenJDK or some other Java Development Kit."
    exit /b %errorlevel%
)

WHERE jar >NUL 2>NUL
IF %ERRORLEVEL% NEQ 0 (
    echo "Command 'jar' does not exist. Install OpenJDK or some other Java Development Kit."
    exit /b %errorlevel%
)

WHERE javadoc >NUL 2>NUL
IF %ERRORLEVEL% NEQ 0 (
    echo "Command 'javadoc' does not exist. Install OpenJDK or some other Java Development Kit."
    exit /b %errorlevel%
)

WHERE mvn >NUL 2>NUL
IF %ERRORLEVEL% NEQ 0 (
    echo "Command 'mvn' does not exist. Install Apache Maven."
    exit /b %errorlevel%
)

WHERE cmake >NUL 2>NUL
IF %ERRORLEVEL% NEQ 0 (
    echo "Command 'cmake' does not exist. Install CMake."
    exit /b %errorlevel%
)
