# Spring Boot Starter Backend

A robust and production-ready **Spring Boot 3+** starter template designed to accelerate your development process. This project comes pre-configured with **PostgreSQL**, **JWT Authentication**, **Dockerization**, and **OpenAPI (Swagger)** documentation.

## Features

- **Spring Boot 3.4.2**: The latest stable version of Spring Boot.
- **Java 21**: Leveraging modern Java features and performance improvements.
- **Security & Auth**:
  - JWT (JSON Web Token) based authentication and authorization.
  - Role-based access control.
  - BCrypt password encoding.
- **Database**:
  - PostgreSQL integration with Spring Data JPA.
  - Hibernate for ORM.
  - Pre-configured connection pooling.
- **API Documentation**:
  - Fully integrated OpenAPI 3 (Swagger UI) for interactive API testing.
  - Secure endpoints documentation.
- **Docker Ready**:
  - Multi-stage Dockerfile for optimized image size.
  - `docker-compose.yml` for easy local development and orchestration.
- **Utilities**:
  - Lombok for boilerplate reduction.
  - MapStruct for efficient DTO mapping.
  - Global Exception Handling.
  - Standardized API Response structure.

---

## Prerequisites

- [Docker](https://www.docker.com/get-started) and [Docker Compose](https://docs.docker.com/compose/install/)
- [Java 21 JDK](https://adoptium.net/) (for local development without Docker)
- [Maven 3.9+](https://maven.apache.org/download.cgi) (or use the included `./mvnw`)

---

## Getting Started

### 1. Clone and Configure
Clone the repository and update the `JWT_SECRET` in `docker-compose.yml` or `src/main/resources/application.yaml`.

### 2. Run with Docker (Recommended)
The easiest way to start the entire stack (App + Database + Adminer) is using Docker Compose:

```bash
docker-compose up --build
```

- **Backend API**: `http://localhost:8080`
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **Adminer (DB Management)**: `http://localhost:8888` (Server: `db`, Username: `postgres`, Password: `postgres`, Database: `template`)

### 3. Run Locally
If you want to run the application locally, ensure you have a PostgreSQL instance running on port `5432` (or update `application.yaml`).

```bash
./mvnw spring-boot:run
```

---

## API Documentation

Once the application is running, you can access the interactive Swagger UI at:
👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

### Key Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/v1/auth/register` | Register a new user | No |
| POST | `/api/v1/auth/login` | Login and receive JWT | No |
| GET | `/v3/api-docs` | OpenAPI JSON definition | No |

---

## Project Structure

```text
src/main/java/com/starter/template/starter/
├── configs/          # Security and OpenAPI configurations
├── controllers/      # REST Controllers
├── dtos/             # Data Transfer Objects
├── exceptions/       # Custom exceptions and Global Handler
├── models/           # JPA Entities
├── repositories/     # Spring Data JPA Repositories
├── security/         # JWT and UserDetails implementation
└── services/         # Business Logic Layer
```

---

## Environment Variables

| Variable | Default Value | Description |
|----------|---------------|-------------|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://db:5432/template` | Database connection URL |
| `SPRING_DATASOURCE_USERNAME` | `postgres` | Database username |
| `SPRING_DATASOURCE_PASSWORD` | `postgres` | Database password |
| `JWT_SECRET` | *32+ char secret* | Secret key for JWT signing |
| `JWT_EXPIRATION` | `86400000` (24h) | JWT validity period in ms |

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Developed by [Abdullah Abdelaziz](mailto:abdullah.abdelaziz15@gmail.com)**
