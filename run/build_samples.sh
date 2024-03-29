#!/usr/bin/env bash
set -e

########## ADSLIB ##########

# Sample 02
javac ./samples/adslib/02_AccessByVariableName/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/02_AccessByVariableName_adslib.jar -C ./samples/adslib/02_AccessByVariableName Main.class
rm ./samples/adslib/02_AccessByVariableName/*.class
echo "Sample 02_AccessByVariableName was compiled."

########## TCADSDLL ##########

# Sample 02
javac ./samples/tcadsdll/02_AccessByVariableName/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/02_AccessByVariableName.jar -C ./samples/tcadsdll/02_AccessByVariableName Main.class
rm ./samples/tcadsdll/02_AccessByVariableName/*.class
echo "Sample 02_AccessByVariableName was compiled."

# Sample 03
javac ./samples/tcadsdll/03_AccessAnArray/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/03_AccessAnArray.jar -C ./samples/tcadsdll/03_AccessAnArray Main.class
rm ./samples/tcadsdll/03_AccessAnArray/*.class
echo "Sample 03_AccessAnArray was compiled."

# Sample 04
javac ./samples/tcadsdll/04_TransmittingStructures/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/04_TransmittingStructures.jar -C ./samples/tcadsdll/04_TransmittingStructures Main.class -C ./samples/tcadsdll/04_TransmittingStructures  TransferObject.class
rm ./samples/tcadsdll/04_TransmittingStructures/*.class
echo "Sample 04_TransmittingStructures was compiled."

# Sample 05
javac ./samples/tcadsdll/05_ReadingAVariableDeclaration/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/05_ReadingAVariableDeclaration.jar -C ./samples/tcadsdll/05_ReadingAVariableDeclaration Main.class -C ./samples/tcadsdll/05_ReadingAVariableDeclaration ValueString.class
rm ./samples/tcadsdll/05_ReadingAVariableDeclaration/*.class
echo "Sample 05_ReadingAVariableDeclaration was compiled."

# Sample 06
javac ./samples/tcadsdll/06_WriteFlagSynchronously/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/06_WriteFlagSynchronously.jar -C ./samples/tcadsdll/06_WriteFlagSynchronously Main.class
rm ./samples/tcadsdll/06_WriteFlagSynchronously/*.class
echo "Sample 06_WriteFlagSynchronously was compiled."

# Sample 07
javac ./samples/tcadsdll/07_ReadFlagSynchronously/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/07_ReadFlagSynchronously.jar -C ./samples/tcadsdll/07_ReadFlagSynchronously Main.class
rm ./samples/tcadsdll/07_ReadFlagSynchronously/*.class
echo "Sample 07_ReadFlagSynchronously was compiled."

# Sample 08
javac ./samples/tcadsdll/08_ReleaseVariableHandle/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/08_ReleaseVariableHandle.jar -C ./samples/tcadsdll/08_ReleaseVariableHandle Main.class
rm ./samples/tcadsdll/08_ReleaseVariableHandle/*.class
echo "Sample 08_ReleaseVariableHandle was compiled."

# Sample 09
javac ./samples/tcadsdll/09_EventDrivenReading/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/09_EventDrivenReading.jar -C ./samples/tcadsdll/09_EventDrivenReading Main.class -C ./samples/tcadsdll/09_EventDrivenReading AdsListener.class
rm ./samples/tcadsdll/09_EventDrivenReading/*.class
echo "Sample 09_EventDrivenReading was compiled."

# Sample 11
javac ./samples/tcadsdll/11_EventDrivenDetectionOfSymbolTableChanges/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/11_EventDrivenDetectionOfSymbolTableChanges.jar -C ./samples/tcadsdll/11_EventDrivenDetectionOfSymbolTableChanges Main.class -C ./samples/tcadsdll/11_EventDrivenDetectionOfSymbolTableChanges AdsListener.class
rm ./samples/tcadsdll/11_EventDrivenDetectionOfSymbolTableChanges/*.class
echo "Sample 11_EventDrivenDetectionOfSymbolTableChanges was compiled."

# Sample 12
javac ./samples/tcadsdll/12_SumCommandReleaseVariableHandles/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/12_SumCommandReleaseVariableHandles.jar -C ./samples/tcadsdll/12_SumCommandReleaseVariableHandles Main.class -C ./samples/tcadsdll/12_SumCommandReleaseVariableHandles ReleaseData.class -C ./samples/tcadsdll/12_SumCommandReleaseVariableHandles RequestData.class
rm ./samples/tcadsdll/12_SumCommandReleaseVariableHandles/*.class
echo "Sample 12_SumCommandReleaseVariableHandles was compiled."

# Sample 13
javac ./samples/tcadsdll/13_SumCommandReadingWritingVariables/*.java -classpath ./dist/TcJavaToAds-3.1.0.jar -deprecation
jar cmf ./samples/MANIFEST.MF ./dist/13_SumCommandReadingWritingVariables.jar -C ./samples/tcadsdll/13_SumCommandReadingWritingVariables Main.class -C ./samples/tcadsdll/13_SumCommandReadingWritingVariables RequestData.class
rm ./samples/tcadsdll/13_SumCommandReadingWritingVariables/*.class
echo "Sample 13_SumCommandReadingWritingVariables was compiled."
