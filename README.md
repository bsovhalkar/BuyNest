# BuyNest 🛒

> **Shop smart. Live better.**

BuyNest is a Spring Boot-based RESTful e-commerce API that provides a solid foundation for building a full-featured online shopping platform. The application currently supports category management with a clean, layered architecture designed for easy extensibility.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Testing](#testing)

---

## Tech Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 | Programming language |
| Spring Boot | 4.0.3 | Application framework |
| Spring Data JPA | — | ORM / data access |
| Hibernate | — | JPA implementation |
| H2 Database | — | In-memory database (development) |
| Maven | — | Build & dependency management |

---

## Project Structure

```
BuyNest/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/ecommerse/BuyNest/
    │   │   ├── BuyNestApplication.java        # Application entry point
    │   │   ├── controller/
    │   │   │   └── CategoryController.java    # REST controller for categories
    │   │   ├── model/
    │   │   │   └── Category.java              # Category JPA entity
    │   │   ├── repositories/
    │   │   │   └── CategoryRepository.java    # Spring Data JPA repository
    │   │   └── service/
    │   │       ├── CategoryService.java       # Service interface
    │   │       └── CategoryServiceImp.java    # Service implementation
    │   └── resources/
    │       └── application.properties         # Application configuration
    └── test/
        └── java/com/ecommerse/BuyNest/
            └── BuyNestApplicationTests.java   # Spring context load test
```

---

## Architecture

BuyNest follows the standard three-tier layered architecture pattern:

```
┌──────────────────────────────────┐
│     REST Controller Layer        │  CategoryController
│   (/api/public/*, /api/admin/*)  │
└───────────────┬──────────────────┘
                │
┌───────────────▼──────────────────┐
│      Service / Business Logic    │  CategoryService (interface)
│                                  │  CategoryServiceImp (implementation)
└───────────────┬──────────────────┘
                │
┌───────────────▼──────────────────┐
│     Repository / Data Access     │  CategoryRepository (JpaRepository)
└───────────────┬──────────────────┘
                │
┌───────────────▼──────────────────┐
│       H2 In-Memory Database      │
└──────────────────────────────────┘
```

---

## Getting Started

### Prerequisites

- **Java 21** or later installed
- **Maven 3.9+** (or use the included `mvnw` wrapper)

### Running the Application

1. **Clone the repository**

   ```bash
   git clone https://github.com/bsovhalkar/BuyNest.git
   cd BuyNest
   ```

2. **Build the project**

   ```bash
   ./mvnw clean package
   ```

3. **Run the application**

   ```bash
   ./mvnw spring-boot:run
   ```

   The server starts on **http://localhost:8080** by default.

4. **Access the H2 Console** *(development only)*

   Navigate to **http://localhost:8080/h2-console** and connect using:
   - JDBC URL: `jdbc:h2:mem:test`
   - Username: `sa`
   - Password: *(leave blank)*

---

## API Endpoints

### Categories

| Method | Endpoint | Access | Description |
|--------|----------|--------|-------------|
| `GET` | `/api/public/categories` | Public | Retrieve all categories |
| `POST` | `/api/public/categories` | Public | Create a new category |
| `PUT` | `/api/public/categories/{categoryId}` | Public | Update an existing category |
| `DELETE` | `/api/admin/categories/{categoryId}` | Admin | Delete a category |

### Request & Response Examples

**GET /api/public/categories** — returns a list of categories:
```json
[
  { "categoryId": 1, "categoryName": "Electronics" },
  { "categoryId": 2, "categoryName": "Clothing" }
]
```

**POST /api/public/categories** — request body:
```json
{ "categoryName": "Books" }
```
Response: `201 Created` — `"Success"`

**PUT /api/public/categories/1** — request body:
```json
{ "categoryName": "Home Appliances" }
```
Response: `200 OK` — `"Success"` (or `404 Not Found` if the category doesn't exist)

**DELETE /api/admin/categories/1**
Response: `200 OK` — `"Category deleted successfully"` (or `404 Not Found` if the category doesn't exist)

---

## Configuration

Key settings in `src/main/resources/application.properties`:

```properties
spring.application.name=BuyNest

# H2 in-memory database
spring.datasource.url=jdbc:h2:mem:test
spring.h2.console.enabled=true

# JPA / Hibernate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
```

---

## Testing

Run the test suite with:

```bash
./mvnw test
```

The project currently includes a Spring context load test (`BuyNestApplicationTests`) that verifies the application context starts up correctly.
