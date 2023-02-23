#!/usr/bin/env bash
set -e

mvn spotbugs:check -f "pom.xml"

# this call also runs all the tests and create coverage report
# (https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
mvn package -Dmaven.test.skip -f "pom.xml"

cp ./target/TcJavaToAds-3.0.0.jar ./dist/
