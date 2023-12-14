@ECHO OFF

SET PATH=%cd%\dist\;%PATH%

echo "Starting sample 02_AccessByVariableName..."
java -jar dist\02_AccessByVariableName.jar || exit /b %errorlevel%
echo -e "\nStarting sample 03_AccessAnArray..."
java -jar dist\03_AccessAnArray.jar || exit /b %errorlevel%
echo -e "\nStarting sample 04_TransmittingStructures..."
java -jar dist\04_TransmittingStructures.jar || exit /b %errorlevel%
echo -e "\nStarting sample 05_ReadingAVariableDeclaration..."
java -jar dist\05_ReadingAVariableDeclaration.jar || exit /b %errorlevel%
echo -e "\nStarting sample 06_WriteFlagSynchronously..."
java -jar dist\06_WriteFlagSynchronously.jar || exit /b %errorlevel%
echo -e "\nStarting sample 07_ReadFlagSynchronously..."
java -jar dist\07_ReadFlagSynchronously.jar || exit /b %errorlevel%
echo -e "\nStarting sample 08_ReleaseVariableHandle..."
java -jar dist\08_ReleaseVariableHandle.jar || exit /b %errorlevel%
echo -e "\nStarting sample 09_EventDrivenReading..."
java -jar dist\09_EventDrivenReading.jar || exit /b %errorlevel%
echo -e "\nStarting sample 11_EventDrivenDetectionOfSymbolTableChanges..."
java -jar dist\11_EventDrivenDetectionOfSymbolTableChanges.jar || exit /b %errorlevel%
echo -e "\nStarting sample 12_SumCommandReleaseVariableHandles..."
java -jar dist\12_SumCommandReleaseVariableHandles.jar || exit /b %errorlevel%
echo -e "\nStarting sample 13_SumCommandReadingWritingVariables..."
java -jar dist\13_SumCommandReadingWritingVariables.jar || exit /b %errorlevel%
