# demo-project

Spring Boot 4.0.3 application built with Maven, Java 21, MySQL, Liquibase, and Swagger UI.

## Requirements

- Java 21
- Maven 3.9+
- MySQL running on `127.0.0.1:3306`

## Database

The application connects to the local MySQL server as `root` with no password and uses the `demo_project` database.

Liquibase manages:
- schema creation for `KR_KODTAR_TBL`
- test seed data for `KT015`

## Run

If Java 21 is not your default JDK:

```bash
export JAVA_HOME=/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home
export PATH=/opt/homebrew/opt/openjdk@21/bin:$PATH
```

Start the API:

```bash
mvn spring-boot:run
```

## API

- `GET /api/health`
- `GET /api/szabi`

Example:

```bash
curl "http://localhost:8080/api/szabi?validOn=2026-03-14"
```

Query parameters:

- `validOn`: optional ISO date in `yyyy-MM-dd` format

Swagger:

- UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI: `http://localhost:8080/v3/api-docs`

## Tests

Unit tests do not require a running API:

```bash
mvn test
```

Integration tests require the API to already be running on `http://127.0.0.1:8080`:

```bash
mvn verify -Papi-it
```

The integration tests insert their own test row and delete it before and after each run.
