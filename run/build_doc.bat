IF NOT EXIST dist (
    mkdir dist || exit /b %errorlevel%
)

IF EXIST dist\doc (
    rmdir /q /s dist\doc || exit /b %errorlevel%
)
mkdir dist\doc || exit /b %errorlevel%

SET PATH=%cd%\dist\;%PATH%

javadoc -d dist\doc -sourcepath src\main\java -subpackages de.beckhoff.jni || exit /b %errorlevel%
