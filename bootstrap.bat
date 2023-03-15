CALL run/check_dependencies.bat || exit /b %errorlevel%
CALL run/clear.bat || exit /b %errorlevel%

CALL run/build_cpp.bat || exit /b %errorlevel%
CALL run/build_java.bat || exit /b %errorlevel%
CALL run/build_doc.bat || exit /b %errorlevel%
CALL run/build_samples.bat || exit /b %errorlevel%
