#!/usr/bin/env bash
set -e

if [ -d "./dist" ]; then
    rm -rd ./dist
fi

if [ -d "./build" ]; then
    rm -rd ./build
fi
if [ -d "./build_win32" ]; then
    rm -rd ./build_win32
fi
if [ -d "./build_x64" ]; then
    rm -rd ./build_x64
fi

if [ -d "./target" ]; then
    rm -rd ./target
fi
