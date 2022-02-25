#!/usr/bin/env bash
set -e

# format c++ code automatically using https://clang.llvm.org/docs/ClangFormat.html
find ./cpp/ -maxdepth 1 -type f -iname *.h -o -iname *.cpp | xargs clang-format -i
find ./src/ -type f -iname *.java | xargs clang-format -i
find ./samples/[0-9][0-9]_*/ -type f -iname *.java | xargs clang-format -i

# perform spell check on all source files
./node_modules/.bin/cspell ./cpp/*.h ./cpp/*.cpp ./src/**/*.java ./samples/**/*.java
