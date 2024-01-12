#!/usr/bin/env bash
set -e

OSCHECK="$(uname -s)"
USE_OPENSOURCE_ADSLIB=false
case "${OSCHECK}" in
    MINGW*) USE_OPENSOURCE_ADSLIB=false ;;
    FreeBSD) USE_OPENSOURCE_ADSLIB=false ;;
    *) USE_OPENSOURCE_ADSLIB=true ;;
esac

if [ "$USE_OPENSOURCE_ADSLIB" = true ] ; then
    cd adslib_for_linux
    cmake  -S . -B build
    cmake --build build
    cd ..
    # cmake -H./adslib_for_linux/ -B./adslib_for_linux/build
    # cmake --build ./adslib_for_linux/build -j 14
fi