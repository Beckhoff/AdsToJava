@ECHO OFF

IF NOT EXIST dist rmdir /q /s dist
mkdir dist || exit /b %errorlevel%

IF EXIST build_x64 (
    xcopy build_x64\Release\*.dll dist\ || exit /b %errorlevel%
) ELSE (
    IF EXIST build_win32 (
        xcopy build_win32\Release\*.dll dist\ || exit /b %errorlevel%
    ) ELSE (
        IF EXIST build (
            xcopy build\Release\*.dll dist\ || exit /b %errorlevel%
        )
    )
)
IF EXIST dist\AdsToJava.dll (
    ren dist\AdsToJava.dll dist\AdsToJava-3.dll || exit /b %errorlevel%
)
