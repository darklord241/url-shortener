### 2nd Nov 2026 
- made a new branch fix/secrets-config which rotated the password and also prevented the actual password from being pushed 
- thought of using both branches + commits on main while working through this project 
- set up the revival-plan.md which is just one intial plan and can be changed for now 
- set up this status.md file to help claude maintain context while not losing its tokens to hold context in a single chat for all development 

#### the current plan made on 2nd Nov 2026 
- Branch or task | What you learn
1. 	fix/secrets-config (in progress), then the README on main |	Config, secrets
2. 	fix/controller-tests and fix/last-used-at | Testing with security, JPA auditing vs native queries
3. 	chore/docker-compose (Postgres)	 | Docker basics, and contextLoads stops needing a manual DB
4. 	test/service-and-integration: Mockito for the service, plus a repository integration test (Testcontainers is the professional way to test against Postgres)	 | Unit vs integration tests
5. 	ci/github-actions and logging with SLF4J (@Slf4j)	 | CI, logs instead of show-sql
6. 	feature/url-expiry	 | Entity, service and 410 Gone handling, and your first Flyway migration
7. 	feature/redis-cache	 | Cache hits, misses, TTL. The redirect currently hits the DB twice and writes on every click, so measure first
8. 	feature/rate-limiting	 | Throttling and abuse prevention
