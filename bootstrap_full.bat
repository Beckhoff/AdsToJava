CALL run/check_dependencies_full.bat || exit /b %errorlevel%
CALL run/clear.bat || exit /b %errorlevel%

REM CSpell is a NodeJS module
CALL npm install || exit /b %errorlevel%

CALL run/lint.bat || exit /b %errorlevel%

CALL run/build_cpp_full.bat || exit /b %errorlevel%
CALL run/build_java_full.bat || exit /b %errorlevel%
CALL run/build_doc.bat || exit /b %errorlevel%
CALL run/build_samples.bat || exit /b %errorlevel%
