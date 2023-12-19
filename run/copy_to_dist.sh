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
fi
