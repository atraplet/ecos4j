# ECOS Solver for Java

![ci workflow](https://github.com/atraplet/ecos4j/actions/workflows/ci.yml/badge.svg)

*This library is currently an experimental library requiring JDK 21 and is work in progress. It depends
on [Project Panama’s](https://openjdk.java.net/projects/panama/) Foreign Function and Memory API which is in preview.*

ecos4j (ECOS Solver for Java) is a Java library that provides an interface from the Java programming language to the
native open source mathematical programming solver [ECOS](https://github.com/embotech/ecos). It invokes the solver
through [Project Panama’s](https://openjdk.java.net/projects/panama/) Foreign Function and Memory API.

The native solver must be installed on the machine. ecos4j doesn't ship any native implementation. ecos4j dynamically
loads the native solver using the `java.library.path` in order to locate the native library.

ecos4j has been tested on Windows 10 and Ubuntu 22.04 (WSL2) using the Zulu build of OpenJDK 21 and ECOS version
v2.0.10. Since Panama is in preview in Java 21 `--enable-preview` is required. Additionally,
use `--enable-native-access=com.ustermetrics.ecos4j` to allow classpath based code to invoke the native code.

## Java bindings

The directory `./bindings` contains the files and scripts needed to generate the Java bindings. The actual bindings are
under `./src/main/java` in the package `com.ustermetrics.ecos4j.bindings`.

The scripts depend on the [jextract](https://jdk.java.net/jextract/) tool, which mechanically generates Java bindings
from native library headers.

The bindings are generated in two steps: First, `./bindings/generate_includes.sh` generates the dumps of the included
symbols in the `includes.txt` file. Second, `./bindings/generate_bindings.sh` generates the actual Java bindings.
