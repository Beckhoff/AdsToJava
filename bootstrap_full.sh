#!/usr/bin/env bash
set -e

. ./run/check_dependencies_full.sh
. ./run/clear.sh

npm install  # CSpell is a NodeJS module
. ./run/lint.sh

. ./run/build_cpp_full.sh
. ./run/build_java_full.sh
. ./run/build_doc.sh
. ./run/build_samples.sh
