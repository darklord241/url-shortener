# URL Shortener – Project Revival Plan

## 1. Context: Why am I reviving this project?

I originally built this URL Shortener using Java and Spring Boot around eight months ago, following an AmigosCode tutorial and experimenting with the concepts along the way.

At the time, I learned and implemented several backend concepts, including REST APIs, Spring Boot, PostgreSQL, Spring Data JPA, Hibernate, dependency injection, and the controller-service-repository architecture.

However, after leaving the project untouched for several months, I've naturally lost some familiarity with the implementation and the reasoning behind certain decisions.

I'm now revisiting it with a different objective.

**The goal isn't to rebuild the application or follow another tutorial. The goal is to understand, improve, and eventually make the existing application production-ready.**

I want to use this project to:
- Regain fluency in Java and Spring Boot through practical work.
- Strengthen my understanding of backend engineering.
- Learn how to structure, test, debug, and maintain a real application.
- Gain experience with tools and practices used in professional software development.
- Build a project that I can confidently explain and demonstrate when applying for internships.

This is particularly relevant because I'm approaching the period when internship opportunities and campus placements will become more important.

---

## 2. What I have already implemented

**Existing technology stack**

| Area | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Build tool | Maven |
| Database | PostgreSQL |
| ORM | Hibernate |
| Persistence | Spring Data JPA |
| Architecture | Controller-Service-Repository |
| API testing | curl |

**Existing functionality**

1. Create a short URL using a POST endpoint.
2. Retrieve all stored URLs using a GET endpoint.
3. Retrieve a URL using its shortcode.
4. Redirect users to the original URL using an HTTP 302 response.
5. Generate random five-character alphanumeric shortcodes.
6. Check whether a generated shortcode already exists and retry if necessary.
7. Check for duplicate original URLs before creating a new entry.

**Existing application structure**

- `UrlShortener` – entity/model.
- `UrlController` – handles incoming HTTP requests.
- `UrlService` – contains business logic.
- `UrlRepo` – interacts with the database.
- `UrlConfig` – configuration and startup-related functionality.

**Important concepts I encountered while building it**

- Spring Boot application startup.
- Maven dependencies, `pom.xml`, and Maven Wrapper.
- `@SpringBootApplication` and `SpringApplication.run()`.
- Dependency injection, beans, and application context.
- `@Configuration`, `@Bean`, and `CommandLineRunner`.
- Spring Data JPA and Hibernate.
- Entity mapping and repository methods.
- JPQL queries and positional parameters.
- `Optional` and database lookup operations.
- HTTP methods, status codes, and redirects.
- Converting incoming JSON into Java objects.
- Separating controller, service, and repository responsibilities.

**Problems I encountered and learned from**

- JPQL uses positional parameters such as `?1`, not a standalone `?`.
- JPQL refers to Java entity names rather than database table names.
- JPA entities require a no-argument constructor.
- Browser address bars send GET requests, so POST endpoints need tools such as curl or Postman.
- `CommandLineRunner` executes during application startup, not on every API request.
- Duplicate URL exceptions can produce HTTP 500 responses if proper exception handling isn't implemented.

These are things I have already worked through once. I should revisit them in the context of the actual code rather than study everything from scratch.

---

## 3. How I want to approach the revival

The first rule is:

**Do not start changing or rebuilding the application immediately.**

I want to recover my understanding of the existing implementation before deciding what needs improvement.

### Phase 1 – Reorientation and understanding

Objective: Become familiar with the application again.

Tasks:

- Open the existing repository in IntelliJ IDEA Ultimate.
- Inspect the complete project structure.
- Understand `pom.xml`, Maven dependencies, and the Maven lifecycle.
- Read the existing source code file by file.
- Understand how the entity, controller, service, and repository interact.
- Inspect the PostgreSQL database and existing schema.
- Re-establish the application and database connection.
- Run the existing application.
- Test all existing endpoints using curl or Postman.
- Read the existing README and `dev_notes/` directory.
- Review the Git commit history to understand how the project evolved.

Why?

I need to know what I actually implemented, why I implemented it that way, and what problems I had already solved.

I don't want to accidentally rewrite working functionality simply because I don't remember how it works.

**Deliverable:** A clear understanding of the existing implementation, its architecture, design decisions, and limitations.

### Phase 2 – Engineering foundations

Objective: Improve the quality and maintainability of the existing application.

Potential tasks:

- Introduce proper exception handling.
- Use DTOs to separate API request/response objects from database entities.
- Add request validation.
- Improve database constraints and indexes.
- Review the database schema and entity relationships.
- Write unit tests for business logic.
- Write integration tests for API and database interactions.
- Understand Spring Security configuration and its implications for testing.
- Improve logging instead of relying on `System.out.println()`.
- Review API response formats and HTTP status codes.

Why?

A working application isn't necessarily a well-engineered application.

I want to understand how to make software more reliable, maintainable, testable, and easier to debug.

**Deliverable:** A cleaner and better-tested application with more predictable behaviour.

### Phase 3 – New capabilities

Objective: Introduce additional functionality while understanding the engineering problems behind it.

Potential features:

1. **Redis caching**
   - Cache frequently accessed shortcode-to-original-URL mappings.
   - Understand cache hits, misses, invalidation, and TTL.
   - Evaluate whether caching actually improves performance.

2. **Rate limiting**
   - Prevent excessive API requests.
   - Understand abuse prevention and request throttling.

3. **URL expiration**
   - Allow short URLs to expire after a specified period.
   - Handle expired links appropriately.

4. **Click tracking**
   - Track how frequently a short URL is accessed.
   - Consider how analytics data should be stored and queried.

5. **Improved shortcode generation**
   - Revisit the existing random generation approach.
   - Understand collision probability, uniqueness, and concurrency.
   - Consider more reliable alternatives.

Why?

I don't want to add technologies just to make the project's tech stack look impressive.

Every feature should solve an actual problem, and I should understand the trade-offs involved in implementing it.

**Deliverable:** A more capable application with meaningful backend engineering improvements.

### Phase 4 – Production readiness

Objective: Learn how to take the application beyond my local development environment.

Potential tasks:

- Dockerize the Spring Boot application.
- Use Docker Compose for the application and PostgreSQL.
- Configure environment variables and externalized configuration.
- Deploy the application.
- Set up CI/CD using GitHub Actions.
- Run automated tests during the build pipeline.
- Improve application logging.
- Introduce monitoring and metrics.
- Perform basic load testing.
- Document the architecture and important design decisions.
- Write clear setup and deployment instructions in the README.

Why?

Building an application locally is only one part of backend development.

I also want to understand how applications are packaged, deployed, monitored, tested, and maintained.

**Deliverable:** A deployed and documented application that I can confidently demonstrate.

---

## 4. Git and version control workflow

I also want to improve how I work with Git.

Previously, I mostly worked directly on the `main` branch. This time, I want to practise a workflow closer to what I'd encounter in a development team.

Possible branch structure:

| Branch | Purpose |
|---|---|
| `main` | Stable version of the application |
| `develop` | Optional integration branch |
| `feature/redis-cache` | Redis caching implementation |
| `feature/rate-limiting` | Rate limiting |
| `feature/url-expiry` | URL expiration |
| `test/integration-tests` | Integration testing |

I don't need to create all these branches immediately. I'll create them when the corresponding work begins.

Practices to follow:

- Make small, meaningful commits.
- Use feature branches for isolated changes.
- Review my own code diffs before merging.
- Create pull requests, even when working alone.
- Understand how to revert changes.
- Keep `main` in a working state.
- Write commit messages that explain what changed and why.

Why?

Version control isn't just about saving code. It's about managing changes, collaborating, reviewing work, and maintaining a stable application.

---

## 5. Making better use of IntelliJ IDEA Ultimate

I already have IntelliJ IDEA Ultimate, so I want to become more comfortable with its development tools.

Areas to explore:

1. **Project navigation:** Understand the complete Spring Boot project structure and navigate between classes efficiently.
2. **Maven integration:** Manage dependencies and execute Maven lifecycle commands.
3. **Spring support:** Navigate beans, dependency injection, mappings, and configuration.
4. **Database tools:** Connect to PostgreSQL, inspect tables, and execute SQL queries directly inside IntelliJ.
5. **Git integration:** Create branches, commit changes, inspect diffs, and manage merges.
6. **Debugger:** Use breakpoints, step through methods, inspect variables, and understand call stacks.

One particular goal is to become comfortable with the debugger rather than relying on `System.out.println()` whenever something goes wrong.

Why?

Knowing how to use the development environment effectively is part of becoming a better developer, not just a convenience.

---

## 6. How I will revise Java and Spring Boot

I don't want to spend two weeks revising all of Java before returning to the project.

Instead, I'll follow a project-driven revision approach.

Examples:

- Forgot `Optional`? Revisit it when examining repository methods.
- Forgot dependency injection? Revisit it while inspecting the service layer.
- Forgot JPA? Revisit it while examining entities and repositories.
- Forgot HTTP status codes? Revisit them while improving the controller.
- Forgot Maven? Revisit it when managing dependencies and running builds.
- Forgot Spring Security? Revisit it when investigating authentication and authorization.

The idea is to learn concepts when they become relevant.

Why?

Contextual revision should help me reconnect concepts to actual implementation instead of memorising isolated theory.

I have already worked with these technologies, so the objective is to recover fluency and deepen understanding rather than start from zero.

---

## 7. Initial issues to investigate

Before implementing new features, I need to establish a reliable baseline.

In the latest Maven test run:

- The project used Java 21 and Spring Boot 3.5.9.
- Maven discovered seven tests in total.
- Six tests failed and one test errored.
- The application context failed because the PostgreSQL connection timed out.
- Controller tests received HTTP 401 for GET requests and HTTP 403 for POST requests, indicating that Spring Security is affecting the tests.
- The database connection issue also caused Hibernate to fail while initializing JPA.

These failures do not establish that the URL-shortening business logic itself is incorrect.

Initial investigation order:

1. Verify that PostgreSQL is running and reachable from WSL.
2. Check the datasource URL, port, database name, and credentials.
3. Understand why the Spring application context cannot connect to PostgreSQL.
4. Inspect the Spring Security configuration.
5. Inspect the controller tests and determine how authentication and CSRF should be handled.
6. Run the tests again and establish a clean baseline.

I should understand the reason behind each failure rather than simply modifying the tests until they pass.

---

## 8. Suggested timeline

This is a tentative plan, not a rigid deadline.

| Period | Focus |
|---|---|
| November–December 2026 | Reorientation, code review, database setup, refactoring, testing, Redis, rate limiting, and other selected improvements |
| January 2027 | Prioritise semester examinations and maintain the project only if time permits |
| February–mid-March 2027 | Docker, deployment, CI/CD, monitoring, load testing, documentation, and internship preparation |

The aim is to have a project that is technically stronger and presentable before internship opportunities become a major priority.

---

## 9. Rules I want to follow throughout this project

- Don't rebuild something that already works without understanding it first.
- Don't blindly copy code from tutorials or AI tools.
- Understand every important implementation decision.
- Use AI for explanations, debugging assistance, and code review, but remain in control of the changes.
- Prefer hints and explanations before asking for complete code.
- Don't add technologies just for the sake of adding them.
- Test changes before merging them.
- Document important decisions and problems encountered.
- Keep the application functional throughout development.
- Focus on learning engineering practices, not just completing features.

---

## 10. Immediate next steps

My first set of tasks should be:

1. Open the existing URL Shortener repository in IntelliJ IDEA Ultimate.
2. Check the current Git status and active branch.
3. Inspect the project structure and `pom.xml`.
4. Read the README and `dev_notes/`.
5. Go through the existing entity, controller, service, repository, and configuration classes.
6. Verify PostgreSQL connectivity.
7. Run the application and test the existing endpoints.
8. Investigate the current Maven test failures.
9. Write down the current architecture, existing functionality, and technical debt.
10. Only then decide what to refactor or implement next.

**The first milestone is not adding Redis, Docker, or more endpoints. It is being able to explain my existing application from end to end.**

---

## Final objective

I want to turn this URL Shortener from an application I built while following a tutorial into a project that demonstrates my understanding of backend engineering.

By the end, I should be able to:

- Explain the complete architecture.
- Justify important technical decisions.
- Design and implement REST APIs.
- Work confidently with Java, Spring Boot, PostgreSQL, and Redis.
- Write and debug unit and integration tests.
- Use Git effectively.
- Containerize and deploy an application.
- Understand basic monitoring, logging, and performance considerations.
- Explain the problems I encountered and how I solved them.

**The real goal is not to have a project with a long list of technologies. It's to have a project whose implementation I genuinely understand and can defend in a technical discussion.**

I'm continuing an existing project, not starting another tutorial.
