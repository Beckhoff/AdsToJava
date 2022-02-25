#!/usr/bin/env bash
set -e

if ! command -v java &> /dev/null
then
    >&2 echo "Command 'java' does not exist. Install OpenJDK or some other Java Development Kit."
    exit 1
fi

if ! command -v javac &> /dev/null
then
    >&2 echo "Command 'javac' does not exist. Install OpenJDK or some other Java Development Kit."
    exit 1
fi

if ! command -v jar &> /dev/null
then
    >&2 echo "Command 'jar' does not exist. Install OpenJDK or some other Java Development Kit."
    exit 1
fi

if ! command -v javadoc &> /dev/null
then
    >&2 echo "Command 'javadoc' does not exist. Install OpenJDK or some other Java Development Kit."
    exit 1
fi

if ! command -v mvn &> /dev/null
then
    >&2 echo "Command 'mvn' does not exist. Install Apache Maven."
    exit 1
fi

if ! command -v cmake &> /dev/null
then
    >&2 echo "Command 'cmake' does not exist. Install CMake."
    exit 1
fi
