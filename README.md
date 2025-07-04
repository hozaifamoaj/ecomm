# E-commerce Application

## Overview

This Spring Boot application provides a RESTful API for an e-commerce platform, managing customers, items, orders, order items, and wishlists. Key features include retrieving customer wishlists, daily sales, maximum sale day, and top-selling items. The application uses PostgreSQL for data persistence, Flyway for database migrations, and an ELK stack (Elasticsearch, Logstash, Kibana) for centralized logging. It is containerized with Docker and Docker Compose for easy deployment.

### Features

- **Customer Management**: Fetch customer wishlists via `/api/customers/{id}/wishlist`.
- **Sales Analytics**: Retrieve daily sales (`/api/sales/daily/{date}`), maximum sale day (`/api/sales/max-sale-day`), and top-selling items (`/api/sales/top-items`, `/api/sales/top-items-last-month`).
- **Wishlist Management**: View items in a customer’s wishlist.
- **Logging**: Logs written to `D:/logs/app.log` and indexed in Elasticsearch for visualization in Kibana (`app-logs-*` index).
- **API Documentation**: Swagger UI for interactive API exploration.
- **Database**: PostgreSQL with Flyway migrations for schema and sample data.

## Tools and Technologies

- **Java**: 17
- **Spring Boot**: 3.x (Spring Web, Spring Data JPA, Spring Boot Test)
- **PostgreSQL**: 16.x (relational database)
- **Flyway**: Database schema migrations
- **ELK Stack**:
    - Elasticsearch: Log storage
    - Logstash: Log parsing and forwarding
    - Kibana: Log visualization (`http://localhost:5601`)
    - Filebeat: Log collection from `D:/logs/app.log`
- **Docker**: Containerization
- **Docker Compose**: Orchestration for PostgreSQL and ELK stack
- **Maven**: Dependency management and build
- **Swagger**: OpenAPI 3 for API documentation (`springdoc-openapi`)
- **JUnit 5** and **Mockito**: Unit and integration testing
- **JSONPath**: API response validation in tests
- **SLF4J** and **Logback**: Logging framework

## Design Patterns

- **RESTful Architecture**: Resource-based endpoints following REST principles.
- **Repository Pattern**: Data access abstraction in `CustomerRepository`, `OrderRepository`, `WishlistRepository`.
- **Service Layer Pattern**: Business logic in `CustomerService` and `SalesService`.
- **DTO Pattern**: Data Transfer Objects (`TopSellingItemDto`, `WishlistItemDto`) for API responses.
- **Exception Handling**: Centralized with `@ControllerAdvice` (`GlobalExceptionHandler`) for consistent error responses.
- **Dependency Injection**: Managed by Spring’s IoC container.
- **Layered Architecture**: Separates controllers, services, repositories, and models.

## Project Structure

```plaintext
ecomm/
├── src/
│   ├── main/
│   │   ├── java/com/test/ecomm/
│   │   │   ├── controller/ (CustomerController, SalesController)
│   │   │   ├── service/ (CustomerService, SalesService)
│   │   │   ├── repository/ (CustomerRepository, OrderRepository, WishlistRepository)
│   │   │   ├── entity/ (CustomerEntity, ItemEntity, OrderEntity, OrderItemEntity, WishlistEntity)
│   │   │   ├── dto/ (TopSellingItemDto, WishlistItemDto)
│   │   │   ├── exception/ (GlobalExceptionHandler, ResourceNotFoundException)
│   │   ├── resources/
│   │       ├── dbscripts/ (V1__initial_schema.sql, V2__add_sample_data.sql, V4__add_june_data.sql)
│   │       ├── application.yml (Spring configuration)
│   │       ├── logback-spring.xml (Logging configuration)
│   ├── test/
│   │   ├── java/com/test/ecomm/controller/ (CustomerControllerTest, SalesControllerTest)
├── src/main/resources/docker/
│   ├── docker-compose.yml (PostgreSQL)
│   ├── elk-docker-compose.yml (ELK stack)
├── pom.xml (Maven configuration)
├── README.md

```

## Running the Application

To run the e-commerce Spring Boot application successfully, execute the following commands from the project root directory:

```bash
cd D:/OTHERS/work-ws/ecomm
mkdir -p D:/logs
cd src/main/resources/docker
docker-compose up -d
docker-compose -f elk-docker-compose.yml up -d
cd ../../..
mvn clean install
mvn spring-boot:run
```
## What These Commands Do

- Navigate to the project directory (D:/OTHERS/work-ws/ecomm).
- Create the log directory (D:/logs) for application logs.
- Navigate to the Docker configuration directory (src/main/resources/docker).
- Start the PostgreSQL container (docker-compose.yml) on localhost:5432.
- Start the ELK stack containers (Elasticsearch, Logstash, Kibana, Filebeat) for logging.
- Return to the project root.
- Build the project with Maven.
- Run the application, accessible at http://localhost:8080.

## Prerequisites

- Java 17: Ensure JDK 17 is installed.
- Maven: Install Maven 3.8.x or higher.
- Docker: Install Docker and Docker Compose.
- D:/logs/: The log directory must exist (created by mkdir -p D:/logs).

## Verification

- Application: Access ```http://localhost:8080/swagger-ui/index.html ``` for API documentation.
- Logs: Check ```D:/logs/app.log``` or Kibana (```http://localhost:5601```, index ```app-logs-*```) for logs.
- Database: Verify PostgreSQL with:```docker exec -it postgres psql -U postgres -d ecommerce -c "SELECT * FROM flyway_schema_history;"```



## Troubleshooting

- Docker Issues:
- Check container status: 
```
docker ps
```

### View logs: 
```
docker logs <container-name>
```



### Log Issues:
- Ensure D:/logs/app.log is writable.
- Check Filebeat/Logstash logs:
```
docker logs filebeat
docker logs logstash
```



### Build Issues:
Run with debug output:
```
mvn clean install -X
```




