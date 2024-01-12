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

if [ -n "$JAVA_HOME" ]; then
  echo "JAVA_HOME is $JAVA_HOME"
else
  java_version=$(java -version 2>&1)
  echo "Java version: $java_version"
  if [[ "$java_version" == *" 1.8"* ]] && [[ -d /usr/local/openjdk8 ]]; then
    export JAVA_HOME=/usr/local/openjdk8
    echo "The JAVA_HOME environment variable is not set. Automatically setting it to $JAVA_HOME."
  else
    java_major_version=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F '.' '{sub("^$", "0", $2); print $1}')
    if [[ -d "/usr/lib/jvm/java-$java_major_version-openjdk/" ]]; then
      export JAVA_HOME="/usr/lib/jvm/java-$java_major_version-openjdk/"
      echo "The JAVA_HOME environment variable is not set. Automatically setting it to $JAVA_HOME."
    else
      echo "Please set the JAVA_HOME environment variable."
    fi
  fi
fi