#!/usr/bin/env bash
set -e

export PATH=$(pwd)/dist:$PATH
export LD_LIBRARY_PATH=$(pwd)/dist:$LD_LIBRARY_PATH

# apply patches depending on the installed java version
java_version=$(java -version 2>&1)
echo "Java version: $java_version"
if [[ "$java_version" == *" 1.8"* ]]; then
    echo "Applying patch: we need to use isAccessible before Java 9"
    sed -i '' -e 's/canAccess(null)/isAccessible()/' './src/test/java/de/beckhoff/jni/tcads/amsaddr/test/AmsAddrTest.java'
    function cleanup {
        echo "Removing patch: prefer the newer canAccess method instead of isAccessible"
        sed -i '' -e 's/isAccessible()/canAccess(null)/' './src/test/java/de/beckhoff/jni/tcads/amsaddr/test/AmsAddrTest.java'
    }
    trap cleanup EXIT
fi

java -XshowSettings:properties -version

mvn spotbugs:check -f "pom.xml"

# this call also runs all the tests and create coverage report
# (https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
mvn package -f "pom.xml"

cp ./target/TcJavaToAds-3.0.0.jar ./dist/
