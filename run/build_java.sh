#!/usr/bin/env bash
set -e

# this call also runs all the tests and create coverage report
# (https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
mvn package -Dmaven.test.skip -f "pom.xml"

cp ./target/TcJavaToAds-3.1.0.jar ./dist/
