# demo-project

Spring Boot 4.0.3 application built with Maven and Java 21.

## Requirements

- Java 21
- Maven 3.6.3+
- MySQL running on `127.0.0.1:3306`

## Run

Use Java 21 explicitly if it is not your default JDK:

```bash
export JAVA_HOME=/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home
export PATH=/opt/homebrew/opt/openjdk@21/bin:$PATH
mvn spring-boot:run
```

The app will connect to the local MySQL server as `root` with no password and create the `demo_project` database on first startup.

## API

`GET /api/health` returns a simple health payload.
