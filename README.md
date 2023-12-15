# TwinCAT AdsToJava

This library is intended for use in ADS client applications written in the Java
programming language.
It was tested on Windows 10 using TwinCAT v3.1.4024.25 and OpenJDK 11.0.14.1.
It has partial support for Tc/BSD using openjdk8-8.362.09.1.

**It consists of two parts:**

1. **Windows:** `AdsToJava-3.dll`: A JNI-based wrapper ([Java Native Interface](https://en.wikipedia.org/wiki/Java_Native_Interface)) for the `TcAdsDll.dll`.

   **Tc/BSD:** `libAdsToJava-3.so`: A JNI-based wrapper ([Java Native Interface](https://en.wikipedia.org/wiki/Java_Native_Interface)) for the `libTcAdsDll.so`.
2. `TcJavaToAds-3.0.0.jar`: A Java archive that provides a straightforward way to call this JNI.

## Requirements on Windows

For an optimal development experience, we recommend the use of
[Visual Studio Code](https://code.visualstudio.com/).
The [Maven](https://maven.apache.org/download.cgi) project that contains
the Java source code should also be compatible with other Java IDEs like
Eclipse, IntelliJ and Netbeans.

To build this library, the following programs must be
installed on your system:

- A Java Development Kit, for example, the
  [Microsoft Build of OpenJDK](https://docs.microsoft.com/en-us/java/openjdk/download)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [CMake](https://cmake.org/download/)
- Microsoft Visual C++ ([MSVC](https://visualstudio.microsoft.com/downloads#other)) compiler toolset
- The [TwinCAT 3](https://www.beckhoff.com/en-en/products/automation/twincat/)
  automation software from Beckhoff Automation

Additional dependencies for the full build:

- An [LLVM](https://llvm.org/builds/) installation that
  includes `clang-format`
- [NodeJS](https://nodejs.org/en/download/) for spell checking
  based on [cspell](https://www.npmjs.com/package/cspell)

## Requirements on Tc/BSD

To build this library, the following additional packages must be installed on your system:

```sh
doas pkg install bash git openjdk8 maven cmake ninja os-generic-userland-devtools
```

Additional dependencies for the full build:

```sh
doas pkg install bash node npm-node18 llvm
```

## Build

Run one of the following files using
[cmd.exe](https://en.wikipedia.org/wiki/Cmd.exe):

```batch
bootstrap.bat
```

```batch
bootstrap_full.bat
```

Or execute one of these commands if you are on Tc/BSD or
using [Git Bash](https://gitforwindows.org/) on Windows:

```sh
bash ./bootstrap.sh
```

```sh
bash ./bootstrap_full.sh
```

The full build also enables all warnings and runs the tests,
clang-tidy, code spell check and automated code formatting.
Before running the tests you should activate the
[TestPlc](plc/TestPlc/) project.

**Note:** On windows, the build scripts always compile an x64 and
win32 version of the `AdsToJava-3.dll`. Depending on your system,
one of them is copied to the `dist/` directory. You can find
the other version in the `build*/Release` directory.
There is no 32 bit version of Tc/BSD operating system so there is
no need for a 32 bit version of this library on Tc/BSD.

## Samples

The [samples directory](samples/) contains sample programs
that use the TcJavaToAds library to communicate with a PLC.
They depend on the [SamplesPlc](plc/SamplesPlc/) project.

## Documentation

Is generated during the build process.

## Folder structure

Overview of the folder structure of this repository:

```txt
.
├── .vscode  # Configuration files for Visual Studio Code
├── dist     # Generated documentation, compiled library and samples
├── run      # Helper scripts
├── cpp      # C++ source files
├── src      # Java source files and tests
├── samples  # Java source files for the samples
├── build*   # Full C++ build output: x64 (and win32)
├── target   # Full Java build output including coverage report
├── plc      # PLC projects for the tests and samples
```

## Contributing

The main purpose of this repository is to continue evolving this
library, adding features and making it easier to use. Development
of TwinCAT AdsToJava happens in the open on GitHub, and we are
grateful to the community for contributing bugfixes and improvements.

## Security contact information

To report a security vulnerability, please contact
[product-securityincident@beckhoff.com](mailto:product-securityincident@beckhoff.com).
Read the
[Coordinated Disclosure](https://infosys.beckhoff.com/english.php?content=../content/1033/ipc_security/3127586699.html&id=8416374187505380732)
page for more information.

## License

TwinCAT AdsToJava is [MIT licensed](LICENSE).
