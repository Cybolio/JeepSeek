# JeepSeek — Spring Boot version

Mirror of the React app (in repo root) using **Controller → Service → Repository → Model** packages.

## Run
```bash
cd springboot
./mvnw spring-boot:run
# or: mvn spring-boot:run
```
Then open http://localhost:8080

## Structure
```
src/main/java/com/example/jeepseekapp/
├── JeepSeekApplication.java
├── controller/   ← handles HTTP requests, returns view names
│   ├── PageController.java
│   └── ContactController.java
├── service/      ← business logic, called by controllers
│   ├── RouteService.java
│   └── ContactService.java
├── repository/   ← Spring Data JPA interfaces (DB access)
│   ├── RouteRepository.java
│   └── ContactRepository.java
└── model/        ← @Entity classes (DB tables)
    ├── Route.java
    └── ContactMessage.java
```

The flow your teacher wants: **Controller calls Service, Service calls Repository, Repository talks to the DB.** Controllers never touch repositories directly.

## DB
Default uses H2 in-memory so it runs out of the box. Swap `application.properties` to MySQL/Postgres when you're ready.

## Map
Routes page loads Leaflet + OpenStreetMap from CDN — no npm needed. Coordinates come from `RouteService.getAll()` and are dropped into the page via Thymeleaf + a small inline `<script>`.

JDK 17+ required.
