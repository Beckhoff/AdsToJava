#!/usr/bin/env bash
set -e

. ./run/check_dependencies.sh

if ! command -v npm &> /dev/null
then
    >&2 echo "Command 'npm' does not exist. Install NodeJS including the Node Package Manager (NPM)."
    exit 1
fi

if ! command -v clang-format &> /dev/null
then
    >&2 echo "Command 'clang-format' does not exist. Install Clang Format."
    exit 1
fi
