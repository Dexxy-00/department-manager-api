# Department Manager API

A modern, basic REST API built with Spring Boot to manage departments. This project demonstrates clean N-Tier architecture, DTO mapping, and global exception handling.

## Features
- **CRUD Operations**: Complete RESTful endpoints (GET, POST, PUT, PATCH, DELETE).
- **Validation**: Jakarta Validation API constraints on inputs.
- **DTO Pattern**: Uses ModelMapper to separate internal entities from API responses.
- **Global Error Handling**: Custom `@RestControllerAdvice` for readable, structured API error responses.

## Tech Stack
- Java 21+
- Spring Boot 4.x
- Spring Data JPA (Hibernate)
- PostgreSQL
- Lombok
- ModelMapper

## Setup

1. Clone the repository.
2. Update `src/main/resources/application.properties` with your local PostgreSQL database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/DepartmentDB
   spring.datasource.username=postgres
   spring.datasource.password=YOUR_PASSWORD_HERE
   ```
3. Run the application using Maven: `./mvnw spring-boot:run`

## Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/departments` | Retrieve all departments |
| GET | `/departments/{id}` | Retrieve a specific department by ID |
| POST | `/departments` | Create a new department |
| PUT | `/departments/{id}` | Update an entire department |
| PATCH | `/departments/{id}` | Partially update a department |
| DELETE | `/departments/{id}` | Delete a department |