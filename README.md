# URL Shortener

A REST API that turns long URLs into short codes and redirects visitors to the original link. Built with Spring Boot to learn the full backend stack: layered architecture, JPA, validation, security, testing and API docs.

## Features
- Create a short link for any valid URL (POST)
- Optional custom alias (3–10 characters)
- Short codes generated from a Postgres sequence with Hashids, so no collision checks are needed
- Redirect from short code to the original URL (HTTP 302)
- Click counting per link
- Admin-only endpoint to list all links (HTTP Basic auth)
- Request validation and consistent JSON error responses
- Swagger UI for the API docs

## Tech stack
Java 21 · Spring Boot 3.5 · Spring Data JPA (Hibernate) · PostgreSQL · Spring Security · Hashids · Lombok · springdoc-openapi · JUnit 5 / MockMvc

## API
| Method | Path | Access | Description |
|---|---|---|---|
| POST | `/api/v1/url` | public | Create a short link. Body: `{"originalUrl": "...", "customAlias": "optional"}` |
| GET | `/api/v1/url/{shortCode}` | public | Redirect to the original URL |
| GET | `/api/v1/url` | admin | List all links |

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## Running locally
1. Install JDK 21 and PostgreSQL, and start the Postgres service.
2. Create a database named `urls` and a user `darklord` that owns it.
3. Create a `.env` file in the project root (it is git-ignored, never commit it):
   ```
   DB_PASSWORD=your-database-password
   ```
   `application.properties` reads it through `spring.config.import`. The DB name and username are set in `application.properties`.
4. Run `./mvnw spring-boot:run`, or run `UrlShortenerApplication` from IntelliJ.
5. Hibernate creates the table on first run (`ddl-auto=update`).
6. Try the endpoints with the `.http` files in IntelliJ, curl, or Swagger UI.

Running tests: `./mvnw test`

## Project structure
Package-by-layer under `src/main/java/urlshortener`: `controller`, `service`, `repository`, `model`, `dto`, `exception`, `config`, `util`.

## Status
Work in progress. Currently known:
- Controller tests need to be updated for the security config (they get 401/403)
- `contextLoads` needs a running Postgres
- Admin user is an in-memory dev account defined in `SecurityConfig`

Learning notes for each phase are in `dev_notes/`.

## Roadmap
- User accounts: registration and login, each user sees and manages only their own links
- URL expiry
- Rate limiting
- Frontend UI
- Deployment (Docker + hosted Postgres)
