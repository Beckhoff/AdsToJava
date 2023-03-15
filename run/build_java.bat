SET PATH=%cd%\dist\;%PATH%

REM this call also runs all the tests and create coverage report
REM (https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
CALL mvn.cmd package -Dmaven.test.skip -f "pom.xml" || exit /b %errorlevel%

xcopy target\TcJavaToAds-3.0.0.jar dist\ || exit /b %errorlevel%
