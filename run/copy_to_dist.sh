#!/usr/bin/env bash
set -e

if [ -d "./dist" ]; then
    rm -rd ./dist
fi
mkdir ./dist

if [ -d "./build_x64" ]; then
    cp ./build_x64/Release/*.dll ./dist/
elif [ -d "./build_win32" ]; then
    cp ./build_win32/Release/*.dll ./dist/
elif [ -d "./build" ]; then
    cp ./build/*.so ./dist/
    if [ -d "./build/Release" ]; then
        cp ./build/Release/*.dll ./dist/
    fi

    OSCHECK="$(uname -s)"
    USE_OPENSOURCE_ADSLIB=false
    case "${OSCHECK}" in
        MINGW*) USE_OPENSOURCE_ADSLIB=false ;;
        FreeBSD) USE_OPENSOURCE_ADSLIB=false ;;
        *) USE_OPENSOURCE_ADSLIB=true ;;
    esac

    if [ "$USE_OPENSOURCE_ADSLIB" = true ] ; then
        cp ./adslib_for_linux/build/AdsLib/*.so ./dist/
    fi
fi
