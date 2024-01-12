@ECHO OFF

REM Sample 02
javac samples\tcadsdll\02_AccessByVariableName\*.java -classpath dist\TcJavaToAds-3.1.0.jar -deprecation || exit /b %errorlevel%
jar cmf samples\MANIFEST.MF dist\02_AccessByVariableName.jar -C samples\tcadsdll\02_AccessByVariableName Main.class || exit /b %errorlevel%
del /f /q /s samples\tcadsdll\02_AccessByVariableName\*.class || exit /b %errorlevel%
echo "Sample 02_AccessByVariableName was compiled."

REM Sample 03
javac samples\tcadsdll\03_AccessAnArray\*.java -classpath dist\TcJavaToAds-3.1.0.jar -deprecation || exit /b %errorlevel%
jar cmf samples\MANIFEST.MF dist\03_AccessAnArray.jar -C samples\tcadsdll\03_AccessAnArray Main.class || exit /b %errorlevel%
del /f /q /s samples\tcadsdll\03_AccessAnArray\*.class || exit /b %errorlevel%
echo "Sample 03_AccessAnArray was compiled."

REM Sample 04
javac samples\tcadsdll\04_TransmittingStructures\*.java -classpath dist\TcJavaToAds-3.1.0.jar -deprecation || exit /b %errorlevel%
jar cmf samples\MANIFEST.MF dist\04_TransmittingStructures.jar -C samples\tcadsdll\04_TransmittingStructures Main.class -C samples\tcadsdll\04_TransmittingStructures  TransferObject.class || exit /b %errorlevel%
del /f /q /s samples\tcadsdll\04_TransmittingStructures\*.class || exit /b %errorlevel%
echo "Sample 04_TransmittingStructures was compiled."

REM Sample 05
javac samples\tcadsdll\05_ReadingAVariableDeclaration\*.java -classpath dist\TcJavaToAds-3.1.0.jar -deprecation || exit /b %errorlevel%
jar cmf samples\MANIFEST.MF dist\05_ReadingAVariableDeclaration.jar -C samples\tcadsdll\05_ReadingAVariableDeclaration Main.class -C samples\tcadsdll\05_ReadingAVariableDeclaration ValueString.class || exit /b %errorlevel%
del /f /q /s samples\tcadsdll\05_ReadingAVariableDeclaration\*.class || exit /b %errorlevel%
echo "Sample 05_ReadingAVariableDeclaration was compiled."

REM Sample 06
javac samples\tcadsdll\06_WriteFlagSynchronously\*.java -classpath dist\TcJavaToAds-3.1.0.jar -deprecation || exit /b %errorlevel%
jar cmf samples\MANIFEST.MF dist\06_WriteFlagSynchronously.jar -C samples\tcadsdll\06_WriteFlagSynchronously Main.class || exit /b %errorlevel%
del /f /q /s samples\tcadsdll\06_WriteFlagSynchronously\*.class || exit /b %errorlevel%
echo "Sample 06_WriteFlagSynchronously was compiled."

REM Sample 07
javac samples\tcadsdll\07_ReadFlagSynchronously\*.java -classpath dist\TcJavaToAds-3.1.0.jar -deprecation || exit /b %errorlevel%
jar cmf samples\MANIFEST.MF dist\07_ReadFlagSynchronously.jar -C samples\tcadsdll\07_ReadFlagSynchronously Main.class || exit /b %errorlevel%
del /f /q /s samples\tcadsdll\07_ReadFlagSynchronously\*.class || exit /b %errorlevel%
echo "Sample 07_ReadFlagSynchronously was compiled."

REM Sample 08
javac samples\tcadsdll\08_ReleaseVariableHandle\*.java -classpath dist\TcJavaToAds-3.1.0.jar -deprecation || exit /b %errorlevel%
jar cmf samples\MANIFEST.MF dist\08_ReleaseVariableHandle.jar -C samples\tcadsdll\08_ReleaseVariableHandle Main.class || exit /b %errorlevel%
del /f /q /s samples\tcadsdll\08_ReleaseVariableHandle\*.class || exit /b %errorlevel%
echo "Sample 08_ReleaseVariableHandle was compiled."

REM Sample 09
javac samples\tcadsdll\09_EventDrivenReading\*.java -classpath dist\TcJavaToAds-3.1.0.jar -deprecation || exit /b %errorlevel%
jar cmf samples\MANIFEST.MF dist\09_EventDrivenReading.jar -C samples\tcadsdll\09_EventDrivenReading Main.class -C samples\tcadsdll\09_EventDrivenReading AdsListener.class || exit /b %errorlevel%
del /f /q /s samples\tcadsdll\09_EventDrivenReading\*.class || exit /b %errorlevel%
echo "Sample 09_EventDrivenReading was compiled."

REM Sample 11
javac samples\tcadsdll\11_EventDrivenDetectionOfSymbolTableChanges\*.java -classpath dist\TcJavaToAds-3.1.0.jar -deprecation || exit /b %errorlevel%
jar cmf samples\MANIFEST.MF dist\11_EventDrivenDetectionOfSymbolTableChanges.jar -C samples\tcadsdll\11_EventDrivenDetectionOfSymbolTableChanges Main.class -C samples\tcadsdll\11_EventDrivenDetectionOfSymbolTableChanges AdsListener.class || exit /b %errorlevel%
del /f /q /s samples\tcadsdll\11_EventDrivenDetectionOfSymbolTableChanges\*.class || exit /b %errorlevel%
echo "Sample 11_EventDrivenDetectionOfSymbolTableChanges was compiled."

REM Sample 12
javac samples\tcadsdll\12_SumCommandReleaseVariableHandles\*.java -classpath dist\TcJavaToAds-3.1.0.jar -deprecation || exit /b %errorlevel%
jar cmf samples\MANIFEST.MF dist\12_SumCommandReleaseVariableHandles.jar -C samples\tcadsdll\12_SumCommandReleaseVariableHandles Main.class -C samples\tcadsdll\12_SumCommandReleaseVariableHandles ReleaseData.class -C samples\tcadsdll\12_SumCommandReleaseVariableHandles RequestData.class || exit /b %errorlevel%
del /f /q /s samples\tcadsdll\12_SumCommandReleaseVariableHandles\*.class || exit /b %errorlevel%
echo "Sample 12_SumCommandReleaseVariableHandles was compiled."

REM Sample 13
javac samples\tcadsdll\13_SumCommandReadingWritingVariables\*.java -classpath dist\TcJavaToAds-3.1.0.jar -deprecation || exit /b %errorlevel%
jar cmf samples\MANIFEST.MF dist\13_SumCommandReadingWritingVariables.jar -C samples\tcadsdll\13_SumCommandReadingWritingVariables Main.class -C samples\tcadsdll\13_SumCommandReadingWritingVariables RequestData.class || exit /b %errorlevel%
del /f /q /s samples\tcadsdll\13_SumCommandReadingWritingVariables\*.class || exit /b %errorlevel%
echo "Sample 13_SumCommandReadingWritingVariables was compiled."
