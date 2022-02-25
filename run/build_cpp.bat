IF EXIST build (
    rmdir /q /s build || exit /b %errorlevel%
)
IF EXIST build_win32 (
    rmdir /q /s build_win32 || exit /b %errorlevel%
)
IF EXIST build_x64 (
    rmdir /q /s build_x64 || exit /b %errorlevel%
)

mkdir build_win32 || exit /b %errorlevel%
cmake -Hcpp -Bbuild_win32 -G "Visual Studio 17 2022" -A Win32 || exit /b %errorlevel%
cmake --build build_win32 --config Release --target ALL_BUILD -j 14|| exit /b %errorlevel%

mkdir build_x64|| exit /b %errorlevel%
cmake -Hcpp -Bbuild_x64 -G "Visual Studio 17 2022" -A x64 || exit /b %errorlevel%
cmake --build build_x64 --config Release --target ALL_BUILD -j 14 || exit /b %errorlevel%

CALL run\copy_to_dist.bat
