# Travel Destination Planner

**Status:** In development

**Purpose:** Associate Software Engineer selection task

**Repository:** `Travel_Dest_Planner`

## Table of Contents

- [Overview](#overview)
- [Scope](#scope)
- [Users and permissions](#users-and-permissions)
- [Product overview](#product-overview)
- [Architecture](#architecture)
- [Requirements](#requirements)
- [Technology stack](#technology-stack)
- [Data model](#data-model)
- [API endpoints](#api-endpoints)
- [Setup and execution](#setup-and-execution)
- [Testing and verification](#testing-and-verification)
- [Known gaps and future improvements](#known-gaps-and-future-improvements)
- [References and learning resources](#references-and-learning-resources)

## Overview

Travel Destination Planner is a full-stack web application for managing a curated collection of travel destinations.

Administrators can fetch country suggestions from an external country-data API, review them in the application, and add selected destinations to the local database. Users can browse destinations saved by an administrator.

This README documents the product scope, requirements, technology stack, API endpoints, setup instructions, testing approach, and learning references. It is intentionally scoped to this assignment rather than being a complete enterprise Software Requirements Specification (SRS).

## Scope

### In scope

- Administrator and user account roles
- Fetching country suggestions from an external API
- Displaying suggestions without automatically saving them
- Saving a destination only after an administrator selects **Add**
- Removing saved destinations
- Displaying country name, capital, region, population, currency, and flag/image
- A REST API built with Spring Boot
- A frontend built with Angular

### Optional or bonus scope

- Searching and filtering destinations
- A personal **Want to Visit** list for each user
- Bulk addition of destinations
- Pagination

### Out of scope for the initial version

- Booking flights, hotels, or tours
- Processing payments
- Producing travel itineraries
- Live travel-price information

## Users and permissions

| User | Goals and permissions |
|---|---|
| **Administrator** | Sign in, fetch country suggestions, add selected destinations, and remove saved destinations |
| **Registered user** | Sign in and browse saved destinations; optionally search destinations and maintain a personal **Want to Visit** list |

The backend must enforce permissions. Hiding an administrator button in Angular is not sufficient to prevent unauthorized requests.

### Glossary

| Term | Meaning |
|---|---|
| API | Application Programming Interface; a way for software components to communicate |
| REST API | An HTTP-based interface that exposes resources through methods such as `GET`, `POST`, and `DELETE` |
| DTO | Data Transfer Object; a structure used to carry data between components |
| Entity | A Java class mapped to a database record using JPA |
| JPA | Jakarta Persistence; a specification for mapping Java objects to relational data |
| ORM | Object-Relational Mapping; a technique for mapping objects to relational tables |
| CRUD | Create, Read, Update, and Delete operations |
| SRS | Software Requirements Specification |
| UI | User Interface |

## Product overview

### Main functions

1. Authenticate administrators and regular users.
2. Retrieve country suggestions from an external country API.
3. Display external suggestions without saving them automatically.
4. Save a suggestion locally when an administrator selects **Add**.
5. List and remove saved destinations.
6. Display the required country information and flag/image.
7. Provide country-name search if the optional feature is completed.
8. Support bulk addition and pagination if the bonus features are completed.
9. Optionally let users save destinations to a personal **Want to Visit** list.

### Destination visibility rule

A destination is stored only after an administrator explicitly adds it. Therefore, the current `Destination` model does not need a separate approval flag. If pending or draft destinations are introduced later, a separate status or approval field may be required.

## Architecture

The application uses a layered backend:

1. Angular provides the browser UI and sends HTTP requests.
2. Spring MVC controllers receive requests.
3. Service classes coordinate application logic.
4. Spring Data JPA repositories handle persistence.
5. Hibernate implements JPA persistence.
6. H2 stores data locally through JDBC.

The planned external API flow is separate from database persistence. Spring Boot fetches country suggestions and returns them to Angular as DTOs. Suggestions do not become local `Destination` records unless an administrator selects **Add**.

### Constraints and dependencies

- The selection task requires an Angular frontend, Spring Boot backend, Java, a database, and a GitHub repository with run instructions.
- The current `pom.xml` uses Spring Boot `4.1.2` and Java `21`. The assignment states Spring Boot 3+; confirm that the evaluator accepts a 4.x version if the expected release line is specifically 3.x.
- Country suggestions depend on the availability, response format, access requirements, and rate limits of the selected external API.
- Backend development requires a supported Java runtime. Angular development requires Node.js and npm.
- Local H2 database files are development data and must not be committed to Git.

## Requirements

Requirement IDs connect planned behavior to testing. Statuses should be updated as implementation is completed and verified.

### Functional requirements

| ID | Requirement | Priority | Current status |
|---|---|---|---|
| FR-01 | The system shall allow an administrator to authenticate. | Required | Not completed end-to-end |
| FR-02 | The system shall allow an administrator to fetch country suggestions from an external API. | Required | Planned |
| FR-03 | The system shall display fetched suggestions without automatically saving them locally. | Required workflow | Planned |
| FR-04 | The system shall save a destination when an administrator selects **Add**. | Required | Service/controller code exists; endpoint testing pending |
| FR-05 | The system shall allow an authorized administrator to remove a saved destination. | Required | Service/controller code exists; endpoint testing pending |
| FR-06 | The system shall allow users to authenticate. | Required | Not completed end-to-end |
| FR-07 | The system shall return saved destinations for the user-facing destination list. | Required | Endpoint exists; currently protected by default Spring Security configuration |
| FR-08 | Each destination shall include country name, capital, region, population, currency, and flag/image URL. | Required | Fields exist in the backend entity; integration and validation testing pending |
| FR-09 | The system should allow users to search or filter destinations. | Optional | Country-search method exists; testing and frontend integration pending |
| FR-10 | The system may allow users to save destinations to a personal **Want to Visit** list. | Optional | Planned |
| FR-11 | The system may support adding multiple destinations in one request. | Bonus | Service/controller code exists; testing pending |
| FR-12 | The system may paginate destination results. | Bonus | Planned |

### Non-functional requirements and acceptance criteria

| ID | Requirement | Acceptance criteria |
|---|---|---|
| NFR-SEC-01 | Admin-only operations shall be authorized by the backend. | Non-admin requests to add, bulk-add, or delete destinations are denied, even when sent outside Angular. |
| NFR-SEC-02 | Passwords shall not be stored in plain text. | Registration stores an encoded password hash; logs and API responses do not disclose the password or hash. |
| NFR-DATA-01 | Destination input shall be validated. | Missing required fields and invalid population values are rejected with a client error. |
| NFR-REL-01 | External API failures shall not crash the application. | An unavailable API produces an appropriate error response or message. |
| NFR-USAB-01 | The UI shall show clear loading, empty, success, and error states. | Principal user and administrator workflows are verified manually. |
| NFR-MAINT-01 | Backend responsibilities shall be separated into controller, service, repository, and model/entity layers. | Controllers use services instead of accessing repositories directly. |
| NFR-DATA-02 | Saved destination data shall persist across restarts. | A saved destination remains available after Spring Boot restarts. |
| NFR-PERF-01 | Paginated list endpoints shall return bounded pages if pagination is implemented. | `page` and `size` parameters return the expected subset and navigation metadata. |

### External interfaces

- **Angular → Spring Boot:** HTTP requests and JSON responses
- **Spring Boot → H2:** JDBC-backed persistence through Spring Data JPA and Hibernate
- **Spring Boot → country API:** Planned server-side HTTP integration mapped from external responses to DTOs
- **Browser → Angular:** Standard web UI interaction

## Technology stack

| Area | Technology | Purpose |
|---|---|---|
| Frontend | Angular (Angular CLI `21.2.28`) | Builds the browser UI |
| Frontend language | TypeScript | Implements Angular components and application logic |
| Frontend tooling | Node.js `22.22.3`, npm `11.21.1` | Runs frontend tooling and installs packages |
| Backend language | Java `21` | Implements backend logic |
| Backend framework | Spring Boot `4.1.2` | Runs the backend and configures Spring components |
| Build and dependencies | Maven Wrapper (`mvnw`) | Builds the backend and resolves Java dependencies |
| Web layer | Spring Web MVC and REST controllers | Handles HTTP endpoints and JSON |
| Persistence | Spring Data JPA and Hibernate ORM | Provides repositories and object-relational persistence |
| Database connectivity | JDBC with the H2 driver | Connects Java persistence to H2 |
| Database | File-based H2 | Stores destination and user records between restarts |
| Validation | Jakarta Bean Validation and Spring Boot Validation | Validates request/entity fields |
| Security | Spring Security | Provides authentication and authorization infrastructure |
| External data | REST Countries or another suitable country-data API | Planned source of country suggestions |
| Version control | Git and GitHub | Tracks and hosts project source code |

## Data model

<img width="619" height="444" alt="Screen Shot 2026-10-28 at 11 26 49 PM" src="https://github.com/user-attachments/assets/71c23727-b316-4fb2-b376-258fa80ebb7f" />


## API endpoints

These mappings are currently present in the backend code but have not yet been confirmed by end-to-end tests. Spring Security may return `401 Unauthorized` for unauthenticated requests until the intended security rules and login flow are configured.

| Method | Endpoint | Purpose | Status |
|---|---|---|---|
| `GET` | `/api/destinations` | Retrieve saved destinations | Implemented; testing pending |
| `GET` | `/api/destinations/search?country=Japan` | Search by country substring, ignoring case | Implemented; testing pending |
| `POST` | `/api/addDestination` | Save one destination | Implemented; testing pending |
| `POST` | `/api/addDestinations` | Save multiple destinations (bonus) | Implemented; testing pending |
| `DELETE` | `/api/deleteDestination/{id}` | Delete one saved destination by ID | Implemented; testing pending |
| `GET` | Admin country-suggestion endpoint | Fetch external suggestions without saving them | Planned |

All `POST` requests that create destinations expect JSON request bodies. Before the API is ready for production use, request validation, correct HTTP status codes, missing-record handling, and administrator authorization must be completed.

## Setup and execution

### Prerequisites

- Git
- Java JDK 21
- Node.js and npm compatible with the Angular project
- A terminal and an IDE such as IntelliJ IDEA

The Maven Wrapper is included in `backend`, so a separate global Maven installation is not required.

### Clone the repository

```bash
git clone <your-repository-url>
cd Travel_Dest_Planner
```

Replace `<your-repository-url>` with the GitHub repository URL.

### Run the backend

Open a terminal in the repository root:

```bash
cd backend
./mvnw spring-boot:run
```

On Windows:

```bat
cd backend
mvnw.cmd spring-boot:run
```

The backend normally listens at <http://localhost:8080>.

To compile without starting the server:

```bash
./mvnw compile
```

To run automated tests:

```bash
./mvnw test
```

> Run the backend from the `backend` directory. If the server is already running, stop it with `Ctrl + C` before starting another instance.

### Run the frontend

Open a separate terminal in the repository root:

```bash
cd frontend
npm install
npm start
```

If the generated project does not define an `npm start` script, use:

```bash
npx ng serve
```

Open <http://localhost:4200> in a browser. The frontend and backend must run in separate terminals.

### H2 database

The backend uses a file-based H2 database configured in `backend/src/main/resources/application.properties`:

```text
jdbc:h2:file:./data/travel_dest_planner
```

Database files are created relative to the backend process's working directory. When enabled, the H2 console is available at <http://localhost:8080/h2-console>. Use the JDBC URL and credentials configured in `application.properties`.

The H2 console is for local development only. Do not expose it in a deployed environment, and ensure `backend/data/` is excluded from Git.

### Current security caveat

Spring Security is included in the project. Until the custom `SecurityFilterChain`, user-loading configuration, password encoder, and login flow are fully implemented, requests may return `401 Unauthorized` or `403 Forbidden`.

## Testing and verification

Testing is in progress. The backend has been observed compiling and starting, and it connects to the file-based H2 database. CRUD endpoints still need to be exercised under the final security configuration.

Planned checks:

- Compile the backend and run automated tests.
- Create a destination and verify its returned ID and fields.
- Retrieve all saved destinations.
- Search using different capitalization and surrounding spaces.
- Bulk-add destinations and confirm that all records are saved.
- Delete an existing destination and handle a nonexistent ID appropriately.
- Restart Spring Boot and confirm that file-based data persists.
- Submit invalid destination data and verify that validation errors are returned.
- Confirm non-admin accounts cannot create, bulk-add, or delete destinations.
- Confirm public registration cannot create an administrator account.
- Simulate external API failures and incomplete country data.
- Verify that fetching suggestions does not create database records until **Add** is selected.
- Verify that Angular displays success, empty, loading, and error states.

## Known gaps and future improvements

- Complete the country API client, DTO mapping, and administrator suggestion endpoint.
- Complete user registration, login, password hashing, and role-based authorization.
- Add request validation and consistent global exception handling.
- Run and document API tests after security configuration is complete.
- Integrate Angular pages with the backend.
- Consider replacing action-oriented endpoint names with resource-oriented REST paths before frontend integration.
- Implement the optional **Want to Visit** feature.
- Implement bonus pagination if time allows.
- Update this README as features are implemented and verified; do not describe planned features as completed.

## References and learning resources

These references are informative learning resources, not additional assignment requirements.

### Requirements and documentation

- Markdown Software Requirements Specification (SRS) Template — inspiration for scope, requirements, verification, and references
- [The C4 Model](https://c4model.com/) — background reading on software architecture concepts

### Spring Boot architecture and REST controllers

- [Spring: Building a RESTful Web Service](https://spring.io/guides/gs/rest-service) — official REST controller and JSON guide
- Getting Started with Spring Boot: A Beginner's Guide to Building Java Applications
- Introduction to Spring Boot Architecture
- Creating a Spring REST Controller
- [Spring: Validating Form Input](https://spring.io/guides/gs/validating-form-input) — validation annotations and invalid-input handling
- What is the use of `@Transactional` with JPA and Hibernate? — community discussion; official framework documentation remains the final authority

### Persistence, entities, and repositories

- [Jakarta Persistence](https://jakarta.ee/specifications/persistence/) — official persistence specification
- Understanding JPA, Spring Data JPA, and ORM in Spring Boot
- JPA and database annotations in Spring Boot
- Spring Boot `JpaRepository` examples

### Node.js and frontend tooling

- Importance of Node.js in web development
- What Is Node.js? A Clear Explanation

### User management, authentication, and authorization

- Developing a User Management API with Spring Boot
- Authentication and Authorization in Spring Boot 3 Using OAuth 2.0 — learning reference only; OAuth 2.0 is not a confirmed implementation choice
- [Spring Security: Authorize HTTP Requests](https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html) — official authorization reference

### External country data

- [REST Countries](https://restcountries.com/) — check current endpoint formats, available fields, access requirements, and rate limits before implementation

## Revision notes

- This README is a working project document and should be updated with implementation and test results.
- Current status reflects the code and terminal output observed during development.
- Endpoint behavior has not yet been verified end-to-end.
- **Last updated:** 2026-10-28
