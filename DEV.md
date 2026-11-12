# Development workflow

There are two ways to get automatic restart/reload, and they do not require
restarting the whole Compose project.

## 1. Local development (fastest)

Spring Boot DevTools is now a dependency of every service. When the compiled
classes change, the running app restarts automatically.

1. Start only the infrastructure in Docker:

   ```bash
   docker compose up -d mysql-accounts mysql-loans eureka-server
   ```

2. Stop the container for the service you want to edit (its port must be free):

   ```bash
   docker compose stop accounts-service
   ```

3. Run the service locally, either from your IDE or:

   ```bash
   cd accounts-service
   ./mvnw spring-boot:run
   ```

The service's `application.yml` already points at `localhost:3306`/`localhost:3307`
and `http://localhost:8761/eureka`, so it works against the Dockerized
infrastructure.

### IntelliJ IDEA

For automatic restarts on save you need IDEA to compile changed classes while
the app is running:

- Settings → Build, Execution, Deployment → Compiler → enable **Build project automatically**
- Settings → Advanced Settings → enable **Allow auto-make to start even if developed application is currently running**

### Terminal

`spring-boot:run` does not watch sources itself. Keep a second terminal open and
compile after each change:

```bash
cd accounts-service
./mvnw compile
```

DevTools notices the new classes in `target/classes` and restarts the app.

## 2. Docker development

Use the dev override file, which builds each service from source and enables
Compose Watch:

```bash
docker compose -f docker-compose.yml -f docker-compose.dev.yml up -d
docker compose -f docker-compose.yml -f docker-compose.dev.yml watch
```

Now edit any Java file, `src/main/resources` file, or `pom.xml`:

- only the affected service image is rebuilt
- only that container is recreated
- Eureka, the gateway, MySQL and the other services keep running

Watch a single service only:

```bash
docker compose -f docker-compose.yml -f docker-compose.dev.yml watch accounts-service
```

### One-off rebuild without watch

```bash
docker compose up -d --build accounts-service
```

This also only rebuilds/restarts `accounts-service`.

## Notes

- The normal `Dockerfile` remains the production image; `Dockerfile.dev` is only
  used when `docker-compose.dev.yml` is passed.
- DevTools is automatically excluded from the repackaged production jars, so it
  does not ship to production.
- Compose Watch requires Docker Compose v2.22 or newer (`docker compose version`).
