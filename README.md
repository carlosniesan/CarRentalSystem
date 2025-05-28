# Car Rental System API

A Spring Boot application that provides a RESTful API for a car rental system.

## Features

- Car inventory management
- Rental price calculation based on car type and duration
- Late return surcharge calculation
- Customer loyalty points tracking

## Car Types and Pricing

### Premium Cars
- Base price: €300 per day
- Extra day surcharge: premium price + 20% of premium price
- Loyalty points: 5 points per rental

### SUV Cars
- Base price: €150 per day
- For the first 7 days: SUV price per day
- Between 7 and 30 days: 80% of SUV price per day
- More than 30 days: 50% of SUV price per day
- Extra day surcharge: SUV price + 60% of small price per day
- Loyalty points: 3 points per rental

### Small Cars
- Base price: €50 per day
- For the first 7 days: small price per day
- More than 7 days: 60% of small price per day
- Extra day surcharge: small price + 30% of small price
- Loyalty points: 1 point per rental

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application:
   ```
   ./mvnw spring-boot:run
   ```
4. Access the application at http://localhost:8080
5. Access the Swagger UI at http://localhost:8080/swagger-ui.html to explore and test all the API endpoints.
6. Access The H2 in-memory database console is also available at http://localhost:8080/h2-console.
   1. url: jdbc:h2:mem:carrentaldb
   2. user: sa
   3. pass: (empty)

## API Endpoints

### Cars

- `GET /api/cars`: Get all cars
- `GET /api/cars/available`: Get all available cars
- `GET /api/cars/{id}`: Get a car by ID
- `POST /api/cars`: Add a new car

### Customers

- `GET /api/customers`: Get all customers
- `GET /api/customers/{id}`: Get a customer by ID
- `POST /api/customers`: Add a new customer

### Rentals

- `GET /api/rentals`: Get all rentals
- `GET /api/rentals/{id}`: Get a rental by ID
- `POST /api/rentals/rent`: Rent one or more cars
- `POST /api/rentals/return`: Return a rented car

## Examples

### Renting a Premium Car for 10 Days

Request:
```json
{
  "customerId": 1,
  "carIds": [1],
  "days": 10
}
```

Response:
```json
{
  "rentals": [
    {
      "id": 1,
      "customerId": 1,
      "carId": 1,
      "startDate": "2023-10-29",
      "plannedEndDate": "2023-11-08",
      "plannedDays": 10,
      "initialPrice": 3000.0,
      "extraCharges": 0.0,
      "status": "ACTIVE",
      "loyaltyPointsEarned": 5
    }
  ],
  "totalPrice": 3000.0,
  "totalLoyaltyPointsEarned": 5
}
```

### Returning a Car Late

Request:
```json
{
  "rentalId": 1,
  "returnDate": "2023-11-10"
}
```

Response:
```json
{
  "rental": {
    "id": 1,
    "customerId": 1,
    "carId": 1,
    "startDate": "2023-10-29",
    "plannedEndDate": "2023-11-08",
    "actualEndDate": "2023-11-10",
    "plannedDays": 10,
    "initialPrice": 3000.0,
    "extraCharges": 720.0,
    "status": "COMPLETED",
    "loyaltyPointsEarned": 5
  },
  "extraDays": 2,
  "extraCharge": 720.0
}
```