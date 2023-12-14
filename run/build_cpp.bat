@ECHO OFF

IF EXIST build (
    rmdir /q /s build || exit /b %errorlevel%
)
IF EXIST build_win32 (
    rmdir /q /s build_win32 || exit /b %errorlevel%
)
IF EXIST build_x64 (
    rmdir /q /s build_x64 || exit /b %errorlevel%
)

setlocal enabledelayedexpansion

set vs_versions="Visual Studio 17 2022" "Visual Studio 16 2019" "Visual Studio 15 2017" "Visual Studio 14 2015"
set newest_available=
for %%v in (%vs_versions%) do (
    cmake --help | findstr /C:%%v > nul
    if not errorlevel 1 (
        set newest_available=%%v
        goto version_found
    )
)
:version_found

if not %newest_available% == "" (
    echo Newest available Visual Studio generator: %newest_available%

    mkdir build_win32 || exit /b %errorlevel%
    cmake -Hcpp -Bbuild_win32 -G %newest_available% -A Win32 || exit /b %errorlevel%
    cmake --build build_win32 --config Release --target ALL_BUILD -j 14|| exit /b %errorlevel%

    mkdir build_x64|| exit /b %errorlevel%
    cmake -Hcpp -Bbuild_x64 -G %newest_available% -A x64 || exit /b %errorlevel%
    cmake --build build_x64 --config Release --target ALL_BUILD -j 14 || exit /b %errorlevel%

    CALL run\copy_to_dist.bat
) else (
    echo No Visual Studio generator found.
)
