# E-Commerce Item Management API

A RESTful API for managing e-commerce items built with **Spring Boot 3.2**.

**Author:** Sanjana Kuhite  
**Email:** dsvjavalinux@gmail.com

---

## 📋 Table of Contents

- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Data Model](#data-model)
- [Input Validation](#input-validation)
- [How to Run](#how-to-run)
- [API Documentation](#api-documentation)
- [Sample Requests](#sample-requests)
- [Deployment](#deployment)

---

## 🎯 Overview

This application provides a RESTful API for managing a collection of e-commerce items. It supports:
- Adding new items with validation
- Retrieving items by ID
- Listing all items
- In-memory data storage using ArrayList

---

## 🛠 Technology Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 17 | Programming Language |
| Spring Boot | 3.2.2 | Application Framework |
| Spring Web | - | REST API |
| Bean Validation | - | Input Validation |
| Lombok | - | Reduce Boilerplate Code |
| SpringDoc OpenAPI | 2.3.0 | API Documentation (Swagger UI) |
| Maven | 3.x | Build Tool |

---

## 📁 Project Structure

```
item-management-api/
├── pom.xml                          # Maven configuration
├── README.md                        # This file
├── Dockerfile                       # Docker configuration
├── render.yaml                      # Render deployment config
└── src/
    └── main/
        ├── java/com/ecommerce/
        │   ├── ItemManagementApplication.java   # Main entry point
        │   ├── config/
        │   │   └── OpenApiConfig.java           # Swagger configuration
        │   ├── controller/
        │   │   └── ItemController.java          # REST endpoints
        │   ├── dto/
        │   │   └── ApiResponse.java             # Response wrapper
        │   ├── exception/
        │   │   ├── GlobalExceptionHandler.java  # Error handling
        │   │   └── ItemNotFoundException.java   # Custom exception
        │   ├── model/
        │   │   └── Item.java                    # Item entity
        │   ├── repository/
        │   │   └── ItemRepository.java          # In-memory storage
        │   └── service/
        │       └── ItemService.java             # Business logic
        └── resources/
            └── application.properties           # App configuration
```

---

## 🔗 API Endpoints

### Base URL
- **Local:** `http://localhost:8080`
- **Production:** `https://item-management-api.onrender.com`

### Endpoints Summary

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/items` | Add a new item |
| `GET` | `/api/items/{id}` | Get item by ID |
| `GET` | `/api/items` | Get all items |

---

### 1. Add a New Item

**Endpoint:** `POST /api/items`

**Description:** Creates a new item in the catalog.

**Request Body:**
```json
{
  "name": "iPhone 15 Pro",
  "description": "Latest Apple smartphone with A17 Pro chip and titanium design",
  "price": 999.99,
  "category": "Electronics",
  "quantity": 50,
  "imageUrl": "https://example.com/iphone15.jpg"
}
```

**Success Response (201 Created):**
```json
{
  "success": true,
  "message": "Item created successfully",
  "data": {
    "id": 1,
    "name": "iPhone 15 Pro",
    "description": "Latest Apple smartphone with A17 Pro chip and titanium design",
    "price": 999.99,
    "category": "Electronics",
    "quantity": 50,
    "imageUrl": "https://example.com/iphone15.jpg",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
  },
  "timestamp": "2024-01-15T10:30:00"
}
```

**Validation Error Response (400 Bad Request):**
```json
{
  "success": false,
  "message": "Validation failed. Please check the input fields.",
  "errors": [
    "name: Item name is required",
    "price: Price must be greater than 0"
  ],
  "timestamp": "2024-01-15T10:30:00"
}
```

---

### 2. Get Item by ID

**Endpoint:** `GET /api/items/{id}`

**Description:** Retrieves a single item by its unique identifier.

**Path Parameters:**
- `id` (Long, required): The unique item ID

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Item retrieved successfully",
  "data": {
    "id": 1,
    "name": "iPhone 15 Pro",
    "description": "Latest Apple smartphone with A17 Pro chip and titanium design",
    "price": 999.99,
    "category": "Electronics",
    "quantity": 50,
    "imageUrl": "https://example.com/iphone15.jpg",
    "createdAt": "2024-01-15T10:30:00",
    "updatedAt": "2024-01-15T10:30:00"
  },
  "timestamp": "2024-01-15T10:30:00"
}
```

**Not Found Response (404):**
```json
{
  "success": false,
  "message": "Item not found with ID: 999",
  "errors": ["Resource not found"],
  "timestamp": "2024-01-15T10:30:00"
}
```

---

### 3. Get All Items

**Endpoint:** `GET /api/items`

**Description:** Retrieves all items from the catalog.

**Success Response (200 OK):**
```json
{
  "success": true,
  "message": "Retrieved 2 item(s)",
  "data": [
    {
      "id": 1,
      "name": "iPhone 15 Pro",
      "description": "Latest Apple smartphone",
      "price": 999.99,
      "category": "Electronics",
      "quantity": 50,
      "imageUrl": null,
      "createdAt": "2024-01-15T10:30:00",
      "updatedAt": "2024-01-15T10:30:00"
    },
    {
      "id": 2,
      "name": "MacBook Pro",
      "description": "Apple laptop with M3 chip",
      "price": 1999.99,
      "category": "Electronics",
      "quantity": 25,
      "imageUrl": null,
      "createdAt": "2024-01-15T10:35:00",
      "updatedAt": "2024-01-15T10:35:00"
    }
  ],
  "timestamp": "2024-01-15T10:40:00"
}
```

---

## 📦 Data Model

### Item Entity

| Field | Type | Required | Constraints |
|-------|------|----------|-------------|
| `id` | Long | Auto-generated | Unique identifier |
| `name` | String | ✅ Yes | 2-100 characters |
| `description` | String | ✅ Yes | 10-1000 characters |
| `price` | BigDecimal | ✅ Yes | Must be > 0 |
| `category` | String | ✅ Yes | 2-50 characters |
| `quantity` | Integer | ✅ Yes | Must be >= 0 |
| `imageUrl` | String | ❌ No | Valid URL format |
| `createdAt` | LocalDateTime | Auto-generated | Creation timestamp |
| `updatedAt` | LocalDateTime | Auto-generated | Last update timestamp |

---

## ✅ Input Validation

The API validates all input fields and returns detailed error messages:

| Field | Validation Rules |
|-------|-----------------|
| `name` | Required, 2-100 characters |
| `description` | Required, 10-1000 characters |
| `price` | Required, must be > 0, max 10 integer digits, 2 decimal places |
| `category` | Required, 2-50 characters |
| `quantity` | Required, must be >= 0 |
| `imageUrl` | Optional, must be valid URL if provided |

---

## 🚀 How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Clone and Run

```bash
# Clone the repository (if applicable)
cd job-project

# Build the application
./mvnw clean package

# Run the application
./mvnw spring-boot:run
```

### Using JAR file

```bash
# Build JAR
./mvnw clean package -DskipTests

# Run JAR
java -jar target/item-management-api-1.0.0.jar
```

The application will start on `http://localhost:8080`

---

## 📚 API Documentation

Interactive API documentation is available via Swagger UI:

- **Swagger UI:** `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

---

## 🧪 Sample Requests

### Using cURL

**Add Item:**
```bash
curl -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Samsung Galaxy S24",
    "description": "Flagship Android smartphone with AI features",
    "price": 899.99,
    "category": "Electronics",
    "quantity": 100
  }'
```

**Get Item by ID:**
```bash
curl -X GET http://localhost:8080/api/items/1
```

**Get All Items:**
```bash
curl -X GET http://localhost:8080/api/items
```

---

## 🌐 Deployment

### Deploying to Render

1. Push code to GitHub
2. Create a new Web Service on [Render](https://render.com)
3. Connect your GitHub repository
4. Use the following settings:
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/item-management-api-1.0.0.jar`
   - **Environment:** `Java`

Or use the included `render.yaml` for blueprint deployment.

### Docker Deployment

```bash
# Build Docker image
docker build -t item-management-api .

# Run container
docker run -p 8080:8080 item-management-api
```

---

## 📝 Important Implementation Details

1. **In-Memory Storage:** Uses ArrayList for storing items. Data is lost on application restart.

2. **Thread Safety:** Uses AtomicLong for ID generation to ensure unique IDs in concurrent environments.

3. **Validation:** Uses Bean Validation (Jakarta Validation) with comprehensive error messages.

4. **Exception Handling:** Global exception handler provides consistent error responses.

5. **API Response Format:** All responses follow a consistent structure with `success`, `message`, `data`, and optional `errors`.

6. **CORS:** Enabled for all origins to support frontend applications.

---

## 📄 License

This project is licensed under the MIT License.

---

**Created by Sanjana Kuhite** for the Java Backend Developer assessment.
