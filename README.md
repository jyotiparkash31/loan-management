# RapiBank - Loan Management System

## Overview
RapiBank is a secure, RESTful API-based Loan Management System built with Spring Boot. It provides endpoints for user authentication, loan management, and administrative functions with JWT-based security.

## Features

- **User Authentication & Authorization**
  - JWT-based authentication
  - Role-based access control
  - Secure password storage with BCrypt

- **Loan Management**
  - Loan application processing
  - Loan status tracking
  - Loan details retrieval

- **Admin Dashboard**
  - User management
  - Loan approval/rejection
  - System monitoring

## Tech Stack

- **Backend**: Spring Boot 3.5.0
- **Security**: Spring Security with JWT
- **Database**: MySQL
- **Build Tool**: Maven
- **Java Version**: 17
- **Dependency Management**: Maven

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- MySQL 8.0 or higher
- Git (for version control)

## Getting Started

### 1. Clone the Repository
```bash
git clone <repository-url>
cd project
```

### 2. Configure Database
1. Create a MySQL database named `rapibank`
2. Update the database configuration in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rapibank
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Build the Application
```bash
mvn clean install
```

### 4. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Authenticate and get JWT token

### Loan Operations (Authenticated)
- `GET /api/loan` - Get all loans (Admin only)
- `POST /api/loan/apply` - Apply for a new loan
- `GET /api/loan/{id}` - Get loan details by ID

### Admin Operations
- `GET /api/admin/users` - Get all users (Admin only)
- `PUT /api/admin/loans/{id}/status` - Update loan status (Admin only)

## Security
- JWT-based authentication
- Password encryption using BCrypt
- Role-based authorization
- CSRF protection
- Stateless session management

## Project Structure

```
src/main/java/com/rapibank/project/
├── config/           # Configuration classes
├── controller/       # REST controllers
├── model/            # Entity classes
├── repository/       # Data access layer
├── security/         # Security configurations
├── service/          # Business logic
└── ProjectApplication.java  # Main application class
```

## Contributing
1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support
For support, please contact the development team or open an issue in the repository.
