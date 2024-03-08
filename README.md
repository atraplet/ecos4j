# ECOS Solver for Java

[![Build](https://github.com/atraplet/ecos4j/actions/workflows/build.yml/badge.svg)](https://github.com/atraplet/ecos4j/actions/workflows/build.yml)
[![Maven Central](https://img.shields.io/maven-central/v/com.ustermetrics/ecos4j)](https://mvnrepository.com/artifact/com.ustermetrics/ecos4j)
[![GNU GPLv3 licensed](https://img.shields.io/badge/license-GPLv3-blue)](https://github.com/atraplet/ecos4j/blob/master/LICENSE)

*This library is currently an experimental library requiring JDK 21 and is work in progress. It depends
on [Project Panama’s](https://openjdk.java.net/projects/panama/) Foreign Function and Memory API which is in preview.*

ecos4j (ECOS Solver for Java) is a Java library that provides an interface from the Java programming language to the
native open source mathematical programming solver [ECOS](https://github.com/embotech/ecos). It invokes the solver
through [Project Panama’s](https://openjdk.java.net/projects/panama/) Foreign Function and Memory API.

## Usage

### Dependency

Add the latest version from [Maven central](https://mvnrepository.com/artifact/com.ustermetrics/ecos4j) to
your `pom.xml`

```
<dependency>
    <groupId>com.ustermetrics</groupId>
    <artifactId>ecos4j</artifactId>
    <version>x.y.z</version>
</dependency>
```

### Native Library

The native solver must be installed on the machine. ecos4j dynamically loads the native solver using
the `java.library.path` in order to locate the native library.

### Run Code

Since Panama is in preview in Java 21 `--enable-preview` is required. Additionally,
use `--enable-native-access=com.ustermetrics.ecos4j` or `--enable-native-access=ALL-UNNAMED` (if you are not using Java
Platform Module System) to allow classpath based code to invoke the native code.

## Build

### Java bindings

The directory `./bindings` contains the files and scripts needed to generate the Java bindings. The actual bindings are
under `./src/main/java` in the package `com.ustermetrics.ecos4j.bindings`.

The scripts depend on the [jextract](https://jdk.java.net/jextract/) tool, which mechanically generates Java bindings
from native library headers.

The bindings are generated in two steps: First, `./bindings/generate_includes.sh` generates the dumps of the included
symbols in the `includes.txt` file. Second, `./bindings/generate_bindings.sh` generates the actual Java bindings.

## Release

Update the version in the `pom.xml`, create a tag, and push it by running

```
export VERSION=X.Y.Z
vi RELEASE.md
git checkout --detach HEAD
sed -i -E "s/<version>[0-9]+\-SNAPSHOT<\/version>/<version>$VERSION<\/version>/g" pom.xml
git add RELEASE.md
git commit -p -m "v$VERSION" pom.xml RELEASE.md
git tag v$VERSION
git push origin v$VERSION
```

This will trigger the upload of the package to Maven Central via GitHub Actions.
