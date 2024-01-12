#!/usr/bin/env bash
set -e

export PATH=$(pwd)/dist:$PATH
export LD_LIBRARY_PATH=$(pwd)/dist:$LD_LIBRARY_PATH

OSCHECK="$(uname -s)"
USE_OPENSOURCE_ADSLIB=false
case "${OSCHECK}" in
    MINGW*) USE_OPENSOURCE_ADSLIB=false ;;
    FreeBSD) USE_OPENSOURCE_ADSLIB=false ;;
    *) USE_OPENSOURCE_ADSLIB=true ;;
esac

if [ "$USE_OPENSOURCE_ADSLIB" = true ] ; then
    echo "Starting sample 02_AccessByVariableName_adslib..."
    java -jar ./dist/02_AccessByVariableName_adslib.jar
else
    echo "Starting sample 02_AccessByVariableName..."
    java -jar ./dist/02_AccessByVariableName.jar
    echo "Starting sample 02_AccessByVariableName..."
    java -jar ./dist/02_AccessByVariableName.jar
    echo -e "\nStarting sample 03_AccessAnArray..."
    java -jar ./dist/03_AccessAnArray.jar
    echo -e "\nStarting sample 04_TransmittingStructures..."
    java -jar ./dist/04_TransmittingStructures.jar
    echo -e "\nStarting sample 05_ReadingAVariableDeclaration..."
    java -jar ./dist/05_ReadingAVariableDeclaration.jar
    echo -e "\nStarting sample 06_WriteFlagSynchronously..."
    java -jar ./dist/06_WriteFlagSynchronously.jar
    echo -e "\nStarting sample 07_ReadFlagSynchronously..."
    java -jar ./dist/07_ReadFlagSynchronously.jar
    echo -e "\nStarting sample 08_ReleaseVariableHandle..."
    java -jar ./dist/08_ReleaseVariableHandle.jar
    echo -e "\nStarting sample 09_EventDrivenReading..."
    java -jar ./dist/09_EventDrivenReading.jar
    echo -e "\nStarting sample 11_EventDrivenDetectionOfSymbolTableChanges..."
    java -jar ./dist/11_EventDrivenDetectionOfSymbolTableChanges.jar
    echo -e "\nStarting sample 12_SumCommandReleaseVariableHandles..."
    java -jar ./dist/12_SumCommandReleaseVariableHandles.jar
    echo -e "\nStarting sample 13_SumCommandReadingWritingVariables..."
    java -jar ./dist/13_SumCommandReadingWritingVariables.jar
fi