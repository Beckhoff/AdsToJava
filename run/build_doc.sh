#!/usr/bin/env bash
set -e

if ! [ -d "./dist" ]; then
    mkdir ./dist
fi

if [ -d "./dist/doc" ]; then
    rm -rd ./dist/doc
fi
mkdir ./dist/doc

javadoc -d ./dist/doc -sourcepath ./src/main/java -subpackages de.beckhoff.jni
