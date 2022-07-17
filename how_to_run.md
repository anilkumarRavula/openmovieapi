# How to run omdb-api at local

## Prerequisites

* Make sure you have installed JAVA 11(https://jdk.java.net/archive/) v11+ and JAVA_HOME is configured.

## Install

1. Open terminal, clone your forked repo, or download zip and extarct.

```git clone https://github.com/anilkumarRavula/openmovieapi.git```
or
``` Extract downloaded zip```

2 change current to root folder

```cd openmovieapi```
3. Install dependencies.

```.\mvnw clean install```
4. Build Executable Jar.

```.\mvnw clean package spring-boot:repackage```
1. Run the server.

```java -jar omdb-api-{version}.jar```

1. Enjoy at the url printed at terminal. E.g., `http://localhost:8080/`

## Next steps

You may need to find out:

* [How to test front-test requests](./how_to_test_request.md).
* [Separation of front-end and back-end](./separation_of_fe_be.md).
