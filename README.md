# Dynamic Employee Management System (DEMS)

## Objective
The Dynamic Employee Management System (DEMS) aims to demonstrate expertise in advanced PostgreSQL queries, Redis integration, JDBC connection pooling, and database handling concepts while adhering to ACID principles. This system enables administrators to efficiently manage employee data with caching for frequently accessed information.

## Features
- **CRUD Operations**: Admins can perform create, read, update, and delete operations on employee records.
- **Search & Filter**: Users and admins can search, filter, and paginate employee records based on various criteria.
- **Caching**: Frequently accessed employee profiles are cached in Redis for faster retrieval.
- **Transactions**: Ensure ACID compliance for sensitive operations, supporting rollbacks on errors.
- **Role-based Access Control**: Admin and user roles with JWT-based authentication.

## Technologies Used
- **Spring Boot** for application development.
- **PostgreSQL** for relational database management.
- **Redis** for caching.
- **JDBC and HikariCP** for efficient database connections.
- **JWT** for secure user authentication.

## Topics Covered
### PostgreSQL
- **Advanced Queries**: Use of joins, aggregate functions, and window functions.
- **JSON Data**: Storing and querying JSON attributes (e.g., skills, certifications).
- **Query Optimization**: Indexing, query plans, and database constraints for performance.

### Redis
- **Basics**: Using data types like strings, hashes, and sorted sets.
- **Caching**: Implement caching for employee profiles and invalidation on data changes.
- **Integration with Spring Boot**: Efficient cache management.

### JDBC and Connection Pooling
- **JDBC Operations**: Using JDBC for database queries and updates.
- **HikariCP**: Efficient connection pooling to improve application performance.

### Database Concepts
- **ACID Principles**: Adherence to atomicity, consistency, isolation, and durability in transactions.
- **Error Handling**: Robust handling of database, Redis, and API errors.

## Functional Requirements

### Authentication and Authorization
- **JWT Authentication**: Login and token-based access.
- **Role-Based Access**:
    - **Admin**: Full access to manage employee records.
    - **User**: Limited access to view employee details.

### Employee Management
- **CRUD Operations**: Admin-only ability to add, update, delete employee records.
- **Employee Details**:
    - Employee ID (UUID)
    - Name
    - Designation
    - Department
    - Date of Joining
    - Salary
    - Additional attributes (JSON) like skills, certifications.

### Search and Filtering
- **Search**: By employee name or department.
- **Filter**: By salary range, joining date, or specific JSON attributes.
- **Pagination**: Limit and offset for large result sets.

### Caching
- **Employee Profile Caching**: Redis caching for frequently accessed employee data.
- **Cache Invalidation**: On updates or deletions.

### Transactions
- **ACID Compliance**: Ensure multi-step operations (like batch updates) are atomic.
- **Rollback**: Changes are rolled back if an error occurs during operations.

## Non-Functional Requirements

### Security
- **JWT**: Secure authentication with encrypted tokens.
- **Password Encryption**: Use bcrypt for password hashing.

### Performance
- **Database Optimization**: Use indexes and query optimization techniques.
- **Caching & Connection Pooling**: Minimize response times using Redis caching and HikariCP connection pooling.

### Reliability
- **Error Handling**: Ensure robust logging and user-friendly error responses.

### Scalability
- **Design**: Scalable to handle a large number of users and employee records.

## API Endpoints

### Authentication
- `POST /auth/login`: Authenticate user and return JWT.
- `POST /auth/register`: Register a new user (Admin only).

### Employee Management
- `POST /employees`: Add a new employee (Admin only).
- `GET /employees/{id}`: Retrieve an employee by ID (Admin and User).
- `PUT /employees/{id}`: Update employee details (Admin only).
- `DELETE /employees/{id}`: Delete an employee (Admin only).

### Search and Filtering
- `GET /employees`: Search, filter, and paginate employees (Admin and User).
    - Query parameters: `name`, `department`, `minSalary`, `maxSalary`, `page`, `size`.

### Bulk Operations
- `POST /employees/bulk`: Add or update multiple employees in a single transaction (Admin only).

## Development Guidelines

### PostgreSQL
- **JSONB**: Store additional employee attributes in JSONB format.
- **Indexes**: Index frequently queried fields like name, department, and JSONB keys.
- **Query Optimization**: Use `EXPLAIN ANALYZE` for complex queries to identify bottlenecks.

### Redis
- **TTL (Time-to-Live)**: Cache employee data with a TTL.
- **Hashes**: Use Redis Hashes to store structured employee data.
- **Cache Invalidation**: Invalidate cache on updates or deletions of employee records.

### Transactions
- **@Transactional**: Use Spring's `@Transactional` annotation to manage multi-step operations and ensure atomicity.

### Error Handling
- **Logging**: Log detailed error information for debugging.
- **User-Friendly Errors**: Return descriptive error messages to API consumers.

### Code Structure
- **MVC Pattern**: Follow the Model-View-Controller design pattern.
- **Separation of Concerns**: Separate layers for controllers, services, and repositories.
- **Unit Tests**: Write unit tests for all components to ensure reliability.

## How to Run
1. Clone the repository: `git clone <repo-url>`
2. Set up the PostgreSQL and Redis instances.
3. Configure application properties in `application.properties` or `application.yml`.
4. Run the Spring Boot application: `mvn spring-boot:run`
5. Access the API at `http://localhost:8080`.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
