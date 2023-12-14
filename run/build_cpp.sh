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
    visual_studio_versions=("Visual Studio 17 2022" "Visual Studio 16 2019" "Visual Studio 15 2017")
    newest_available=""
    for version in "${visual_studio_versions[@]}"; do
        if cmake --help | grep -q "$version"; then
            newest_available="$version"
            break
        fi
    done

    if [ -n "$newest_available" ]; then
        echo "Newest available Visual Studio generator: $newest_available"

        mkdir ./build_win32
        cmake -H./cpp -B./build_win32 -G "$newest_available" -A Win32
        cmake --build ./build_win32 --config Release --target ALL_BUILD -j 14

        mkdir ./build_x64
        cmake -H./cpp -B./build_x64 -G "$newest_available" -A x64
        cmake --build ./build_x64 --config Release --target ALL_BUILD -j 14
    else
        echo "No Visual Studio generator found."
    fi
else
    mkdir ./build
    cmake -H./cpp -B./build -G "Ninja"
    cmake --build ./build --config Release -j 14
fi

. ./run/copy_to_dist.sh
