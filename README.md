# Read Corner Library - Spring Boot Backend

## Project Overview

Read Corner Library is a comprehensive backend system for a digital library, built with Spring Boot. This system provides a robust API for managing books, user accounts with authentication features, shopping carts, orders, and user feedback.

## API Documentation

API documentation is available via Swagger UI.
After starting the application, visit `http://localhost:8080/swagger-ui/index.html` to view and interact with the API documentation.

API documentation is also available via Postman.
documentation URL : https://documenter.getpostman.com/view/32077555/2sAXqy4fDi

### Noting that: 
- All inial data will be added to local DB automaticaly on first run.
- Admin user -> mag3789"gmail , pass : m.A123456789
  
## Technologies Used

- Java 21
- Spring Boot 3.3.3 - Framework for building Java applications
- JPA (Hibernate) - Persistence layer for database access
- Spring Security - Security features including authentication and JWT authorization
- MySQL - Relational database to store library data
- Lombok - For automating the generation of boilerplate code
- JWT for authentication
- Spring Mail - for user email verification
- Thymeleaf - For mail template
- Stripe - For payment processing
- Cloudinary - For image hosting
- Swagger - API documentation

## Features

1. **Authentication**
    - User registration with email verification
    - User login with JWT-based authentication.
    - Account activation via token.

2. **Book Management**
    - CRUD operations for books
    - Pagination and sorting
    - Search books by category, Author or Title 
    - Image `upload to cloud` for book covers

3. **Shopping Cart**
    - Add/remove items from cart
    - Update item quantities
    - Clear cart

4. **Order Management**
    - Create orders from cart
    - Update order status
    - Integrate with `Stripe` for payments
    - View orders (all, by status, by user)

5. **User Management**
    - CRUD operations for users
    - User profile management

6. **Feedback System**
    - Add/update/delete feedback for books
    - View feedback for a specific book

## API Endpoints

### Authentication
- POST `/auth/register`: Register a new user
- POST `/auth/login`: Authenticate a user
- GET `/auth/activate-account`: Activate a user account

### Books
- GET `/book/`: Get all books (paginated)
- GET `/book/books_by_category/{category}`: Get books by category
- GET `/book/books_by_author/{author}`: Get books by author
- GET `/book/{id}`: Get a specific book
- POST `/book/`: Add a new book
- PUT `/book/{id}`: Update a book
- DELETE `/book/{id}`: Delete a book

### Cart
- POST `/cart/add_item`: Add an item to the cart
- DELETE `/cart/delete_item/{cartId}/{bookId}`: Remove an item from the cart
- DELETE `/cart/clear/{cartId}`: Clear the cart
- GET `/cart/{cartId}`: Get cart details
- POST `/cart/decrease_item`: Decrease item quantity in the cart

### Feedback
- POST `/feedback/`: Add new feedback
- GET `/feedback/book/{bookId}`: Get all feedback for a book
- DELETE `/feedback/{feedbackId}`: Delete feedback
- PUT `/feedback/{feedbackId}`: Update feedback

### Orders
- POST `/order/create/{cartId}`: Create an order from a cart
- PUT `/order/updateStatus/{orderId}`: Update order status
- GET `/order/success`: Handle successful payment
- GET `/order/cancel`: Handle cancelled payment
- GET `/order/`: Get all orders (admin)
- GET `/order/status/{status}`: Get orders by status
- GET `/order/pending`: Get all pending orders
- GET `/order/user/{userId}`: Get orders for a specific user
- GET `/order/{orderId}`: Get a specific order

### Users
- GET `/user/`: Get all users
- GET `/user/me`: Get current user's details
- GET `/user/{id}`: Get a specific user
- PATCH `/user/update_me`: Update current user's details
- PATCH `/user/{id}`: Update a specific user
- DELETE `/user/{id}`: Delete a user account

## Setup and Installation
1. Clone the repository - git clone <repository-url>
2. Update `application.yml` with the required configurations:
   MySQL Database credentials or can uncomment the hosted DB.
   JWT secret key and email SMTP settings.
   Cloudinary and Stripe API keys.
3. Set up environment variables for sensitive information (e.g., JWT secret, Stripe API key)
4. Run `mvn clean install` to build the project
5. Start the application with `mvn spring-boot:run`

## Security
JWT-based authentication for user login and access.
Admin endpoints are protected and accessible only to authorized users.

## Payment Integration
Stripe is used for handling payments for orders. Orders are initially marked as 'PENDING' and updated based on payment success or failure.

## Image Management
Cloudinary is used to store book cover images, making the application scalable with image storage.

## Data Base ERD
![DB ERD](https://github.com/user-attachments/assets/9b2270fe-7247-452b-b653-7c1ad3423236)

## Swagger Image
![swagger0](https://github.com/user-attachments/assets/0d00b1d4-4ea0-4c73-b75a-b0abdeb25f8f)




