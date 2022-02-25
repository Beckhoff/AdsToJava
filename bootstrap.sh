#!/usr/bin/env bash
set -e

. ./run/check_dependencies.sh
. ./run/clear.sh

. ./run/build_cpp.sh
. ./run/build_java_skip_tests.sh
. ./run/build_doc.sh
. ./run/build_samples.sh
