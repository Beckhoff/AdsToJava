#!/usr/bin/env bash
set -e

if [ -d "./build" ]; then
    rm -rd ./build
fi
if [ -d "./build_win32" ]; then
    rm -rd ./build_win32
fi
if [ -d "./build_x64" ]; then
    rm -rd ./build_x64
fi

if [[ "$OSTYPE" == "cygwin" ]] || [[ "$OSTYPE" == "msys" ]]; then
    mkdir ./build_win32
    cmake -H./cpp -B./build_win32 -G "Visual Studio 17 2022" -A Win32 -DSTRICT_WARNINGS=ON
    cmake --build ./build_win32 --config Release --target ALL_BUILD -j 14

    mkdir ./build_x64
    cmake -H./cpp -B./build_x64 -G "Visual Studio 17 2022" -A x64 -DSTRICT_WARNINGS=ON
    cmake --build ./build_x64 --config Release --target ALL_BUILD -j 14
else
    mkdir ./build
    cmake -H./cpp -B./build -G "Ninja" -DSTRICT_WARNINGS=ON
    cmake --build ./build --config Release -j 14
fi

. ./run/copy_to_dist.sh
