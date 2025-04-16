# Hotel Management System

This is a Hotel Management System built with **Spring Boot** and **Embedded h2 memory**. It provides various APIs to add rooms, view available rooms, book room and cancel booking.

## Prerequisites

Before running the application, ensure you have the following:

- **Java 8+** installed.
- **Spring Boot** setup (or an IDE like IntelliJ IDEA or Eclipse for development).
- **Maven** installed for dependency management.

## Getting Started

### 1. Clone the Repository

Clone the repository of the Library Management System:

```bash
git clone https://github.com/Bashar-collab/Hotel-Management-System.git
cd Hotel-Management-System
```

### 2. Set Up Database Configuration

Before running the application, you need to configure the database connection. This project uses **embedded h2 memory** as the default database. Follow the steps below to update the database configuration in the `application.properties` file:

#### Update `application.properties`

1. Navigate to the `src/main/resources/application.properties` file.
2. Update the following properties with your database details:

```properties
# H2 in-memory DB
spring.datasource.url=jdbc:h2:mem:hoteldb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Hibernate settings
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the Application

Once the database configuration is done, you can run the application either using **Maven** or an IDE.

#### Using Maven

1. Open a terminal and navigate to the project directory.
2. Run the following command:

```bash
mvn spring-boot:run
```

### 4. Interact with API Endpoints

Once the application is running, the API is available at `http://localhost:8080`. Below are the main API endpoints you can interact with:

### Rooms API

1. **List Rooms, filter by availability**
  - **Endpoint**: `GET /rooms?available=true`
  - **Request**:
```curl
curl -X 'GET' \
'http://localhost:8080/rooms?available=true' \
-H 'accept: */*'
```
  - **Response**: A list of rooms in JSON format.
```JSON
{
  "status": "200 OK",
  "message": "Rooms retrieved successfully",
  "data": [
    {
    "id": 1,
    "roomNumber": "1",
    "capacity": 2,
    "pricePerNight": 5,
    "available": true
    }
  ]
}
```
2. **Add new room**
  - **Endpoint**: `POST /rooms`
  - **Request**:
```curl
curl -X 'POST' \
  'http://localhost:8080/rooms' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "roomNumber": 1,
  "capacity": 0,
  "pricePerNight": 0,
  "available": true
```
  - **Response**: Confirmation message.
```JSON
{
  "status": "201 CREATED",
  "message": "Room created successfully",
  "data": {
    "id": 1,
    "roomNumber": "1",
    "capacity": 0,
    "pricePerNight": 0,
    "available": true
  }
}
```
3. **List Bookings**
  - **Endpoint**: `GET /bookings`
  - **Request**:
```curl
curl -X 'GET' \
'http://localhost:8080/bookings' \
-H 'accept: */*'
```
  - **Response**: A list of bookings in JSON format.
```JSON
{
  "status": "200 OK",
  "message": "Bookings retrieved successfully!",
  "data": [
    {
      "id": 1,
      "customerName": "Bashar",
      "room": {
        "id": 1,
        "roomNumber": "1",
        "capacity": 2,
        "pricePerNight": 5,
        "available": true
      },
      "checkIn": "2025-04-16",
      "checkOut": "2025-04-16",
      "status": "CANCELLED"
    }
  ]
}
```
4. **View booking details**
  - **Endpoint**: `GET /bookings/{id}`
  - **Request**:
```curl
curl -X 'GET' \
  'http://localhost:8080/bookings/1' \
  -H 'accept: */*'
```
  - **Response**: Booking details in JSON format.
```JSON
{
  "status": "200 OK",
  "message": "Booking retrieved successfully!",
  "data": {
    "id": 1,
    "customerName": "Bashar",
    "room": {
      "id": 1,
      "roomNumber": "1",
      "capacity": 2,
      "pricePerNight": 5,
      "available": false
    },
    "checkIn": "2025-04-16",
    "checkOut": "2025-04-16",
    "status": "CONFIRMED"
  }
}
```
5. **Create a new booking**
  - Validate room availability and date range
  - Mark room as unavailable
  - **Endpoint**: `POST /bookings`
  - **Request**:
```curl
curl -X 'POST' \
  'http://localhost:8080/bookings' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "customerName": "string",
  "roomNumber": 1,
  "checkIn": "2025-04-16",
  "checkOut": "2025-04-16"
}'
```
  - **Response**: Confirmation message.
```JSON
{
  "status": "201 CREATED",
  "message": "Booking created successfully!",
  "data": {
    "id": 1,
    "customerName": "Bashar",
    "room": {
      "id": 1,
      "roomNumber": "1",
      "capacity": 2,
      "pricePerNight": 5,
      "available": false
    },
    "checkIn": "2025-04-16",
    "checkOut": "2025-04-16",
    "status": "CONFIRMED"
  }
}
```
6. **Cancel a booking**
  - **Endpoint**: `PUT /bookings/{id}/cancel`
  - **Request**:
```curl
curl -X 'PUT' \
  'http://localhost:8080/bookings/1/cancel' \
  -H 'accept: */*'
```
  - **Response**: Confirmation message.
```JSON
{
  "status": "200 OK",
  "message": "Booking cancelled successfully!",
  "data": null
}
```
