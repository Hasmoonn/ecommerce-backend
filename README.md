# E-Commerce Cart Application

<div align="center">

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat&logo=spring)
![Java](https://img.shields.io/badge/Java-17+-orange?style=flat&logo=java)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat&logo=mysql)

**A professional RESTful API for e-commerce operations**

</div>

---

## Overview

Production-ready e-commerce backend built with Spring Boot, featuring layered architecture, DTO pattern, JPA Specifications, and comprehensive REST APIs.

## Tech Stack

| Category | Technologies |
|----------|--------------|
| Framework | Spring Boot 3.x |
| Language | Java 17+ |
| Database | MySQL 8.0 |
| ORM | Hibernate (Spring Data JPA) |
| Build Tool | Maven |

## Features

- **Product Management**: Pagination, advanced search (category, price range, keywords, ratings), multi-image support, automatic data seeding
- **Order Management**: Order creation with multiple items, UUID reference tracking, automatic calculations (subtotal, tax, total)
- **Review System**: Product ratings (1-5 stars), validation
- **Technical**: DTO Pattern, JPA Specifications, Pagination, Bean Validation

## Quick Start

### Prerequisites
- Java 17+
- MySQL 8.0
- Maven 3.6+

### Database Setup
```sql
CREATE DATABASE ecommerce;
```

### Configuration
`src/main/resources/application.properties`:
```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.format_sql=true

# File Upload Configuration
spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Static Resources for Images
spring.web.resources.static-locations=file:uploads/
```

### Create Uploads Directory
```bash
mkdir uploads
mkdir -p uploads/products
```

### Run Application
```bash
mvn clean install
mvn spring-boot:run
```

The app runs at: **http://localhost:8080**

---

## API Endpoints

### Products
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/products?page=0&size=5` | Get all products (paginated) |
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products/search?category=Phone&minPrice=500` | Search products |

### Orders
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/orders` | Create new order |
| GET | `/api/orders/{referenceId}` | Get order by reference ID |

### Reviews
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/products/reviews` | Add product review |

---

## Project Structure

```
com.eCommerce.cart/
├── controller/     # REST Controllers
├── service/        # Business Logic
├── repository/     # Data Access Layer
├── model/          # Entities
├── dto/            # Data Transfer Objects
└── config/         # Configuration
```

## Design Patterns

- **DTO Pattern**: Prevents circular JSON serialization
- **Repository Pattern**: Abstracts data access logic
- **Specification Pattern**: Dynamic query building
- **Service Layer Pattern**: Separation of concerns

## Deployment

### Docker Compose
```yaml
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_DATABASE: ecommerce
      MYSQL_ROOT_PASSWORD: your_password
    ports:
      - "3306:3306"
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      MYSQL_HOST: mysql
      MYSQL_USER: your_username
      MYSQL_PASSWORD: your_password
    depends_on:
      - mysql
```

### Production Configuration
Update `application.properties` for production:
```properties
spring.jpa.hibernate.ddl-auto=validate
spring.datasource.password=${DB_PASSWORD}
```

### Production Checklist
- [ ] Change `ddl-auto` to `validate`
- [ ] Use environment variables for credentials
- [ ] Enable HTTPS/SSL
- [ ] Add authentication & authorization
- [ ] Set up monitoring (Spring Boot Actuator)

---

## Troubleshooting

### Database Connection Failed
- Verify MySQL is running: `mysql -u root -p`
- Check credentials in `application.properties`
- Ensure database `ecommerce` exists

### Port 8080 Already in Use
```properties
server.port=8081
```

### Product Images Not Loading
- Ensure `uploads/` directory exists
- Verify `spring.web.resources.static-locations=file:uploads/`

## Future Enhancements

- [ ] JWT Authentication & Authorization
- [ ] Payment Integration (Stripe/PayPal)
- [ ] Redis Caching
- [ ] Email Notifications
- [ ] Swagger/OpenAPI Documentation
- [ ] Unit & Integration Tests

---

<div align="center">
  
**Mohamed Hasmoon**  
[GitHub](https://github.com/hasmoonn) • [LinkedIn](www.linkedin.com/in/mohamed-hasmoon-0292732a1)

</div>
