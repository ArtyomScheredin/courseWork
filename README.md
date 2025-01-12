# Deans office app

This project is a Java backend application developed with Spring Boot, showcasing advanced skills in REST API design, service-layer abstraction, data manipulation, and security. It manages students, teachers, and academic marks, demonstrating expertise in scalable and secure backend systems.

## Features

- Person Management: CRUD operations, average marks calculation, and retrieval by criteria.

- Mark Management: CRUD operations, analytics for groups, subjects, and students.

- Security: JWT-based authentication for secure endpoints.

- Database Interaction: Hibernate ORM for efficient data handling and complex relationships.

- API Design: Clean REST endpoints with JSON-based responses.

## Technical Highlights

- Technologies: Spring Boot, Spring Security, Hibernate, PostgreSQL/MySQL/SQLite, Swagger/OpenAPI.

- Testing: Unit and integration tests with Mockito.

- Code Quality: Modular architecture, DTO usage, robust exception handling.

## Example Endpoints

- DELETE /person/avg-lower/{threshold}: Remove students below a grade threshold.

- GET /person/{id}/avg: Fetch a person’s average marks.

- GET /mark/subject/avg: Retrieve average marks by subject.
