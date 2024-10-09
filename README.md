# POS System - Spring REST API

## Overview

This project is a **Point of Sale (POS) System** backend developed using **Spring Framework**. It follows a well-structured layered architecture to manage customers, items, and orders. The system is designed with RESTful services that integrate with a front-end for managing sales, customers, and inventory.

### Key Technologies Used:
- **Java 21**
- **Spring Framework** (Spring Web MVC, Spring Data JPA)
- **Hibernate** (ORM)
- **MySQL** (Database)
- **Lombok** (Boilerplate reduction)
- **Maven** (Build automation)
- **AJAX (Fetch API)** (for front-end integration)
- **Logback & SLF4J** (Logging)
- **Postman** (API testing and documentation)

## Features

- **Customer Management:** Add, update, and retrieve customer details.
- **Item Management:** Add, update, and retrieve item details.
- **Order Processing:** Manage orders with auto-generated order IDs, customer, and item selection, quantity updates, discount handling, and bill generation.
- **Custom Status Codes:** Provide customized responses using `CustomStatusCode`.
- **Regex Validation:** Enforces validation for customer IDs using regular expressions.
- **Exception Handling:** Custom exceptions for improved error handling.
- **Logging:** Integrated logging using SLF4J and Logback.

## API Endpoints

### Customers
| Method | Endpoint            | Description                      |
|--------|---------------------|----------------------------------|
| POST   | `/api/customers`     | Add a new customer               |
| GET    | `/api/customers/{id}`| Retrieve customer by ID          |
| PUT    | `/api/customers/{id}`| Update customer details by ID    |
| GET    | `/api/customers`     | Retrieve all customers           |

### Items
| Method | Endpoint            | Description                      |
|--------|---------------------|----------------------------------|
| POST   | `/api/items`         | Add a new item                   |
| GET    | `/api/items/{id}`    | Retrieve item by ID              |
| PUT    | `/api/items/{id}`    | Update item details by ID        |
| GET    | `/api/items`         | Retrieve all items               |

### Orders
| Method | Endpoint            | Description                      |
|--------|---------------------|----------------------------------|
| POST   | `/api/orders`        | Create a new order               |
| GET    | `/api/orders/{id}`   | Retrieve order by ID             |
| GET    | `/api/orders`        | Retrieve all orders              |

## Running the Application

1. Clone the repository:
   ```bash
   git clone https://github.com/dimagisihilel/POS-With-Spring.git
   ```
2. Configure the database details in WebAppRootConfig Class
      ```bash
      @Bean
       public DataSource dataSource() {

           var dmds = new DriverManagerDataSource();
           dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
           dmds.setUrl("jdbc:mysql://localhost:3306/PosWithSpringDB?createDatabaseIfNotExist=true");  //DB Name
           dmds.setUsername("root");
           dmds.setPassword("Ijse@1234");
           return dmds;
       }
   ```
3. Run the Application.

4. Access the API
      ```bash
      http://localhost:8080/possystem/api/v1/customers
   ```
## API Documentation

You can explore the complete API documentation using **Postman** by importing the following collection:

[![Run in Postman](https://run.pstmn.io/button.svg)](https://documenter.getpostman.com/view/36189383/2sAXxQdXaP)

## Testing

- **Postman:** All API endpoints can be tested using the Postman collection provided.

## License
This project is licensed under the MIT License - see the LICENSE file for details.


