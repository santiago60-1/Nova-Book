# BooksNova

## Index

- Description  
- Technologies  
- Prerequisites  
- Installation and configuration  
- Project structure  
- Implementation  
- Domain layer  
- DAO layer  
- Service Layer  
- User Interface  
- Configuration  
- Usage  
- Improvements and Future Features  
- Authorship

## Description

BooksNova is a Java application for managing books, members, and loans in a library.  
It offers both console/dialog (`JOptionPane`) and Swing GUI interfaces for registering and authenticating users.  
The architecture follows OOP, DAO, and service layer principles, with persistence in MySQL.  

## Technologies

- Java 17  
- Swing for graphical interfaces  
- JDBC and DAO pattern for data access  
- Service layer for business logic
- MySQL as database management system
- Maven for dependency management
- `config.properties` for connection configuration and business rules

## Prerequisites

- JDK 17 installed
- MySQL 8.x locally or remotely  
- Maven 3.x installed
- `booksnova` database with `users`, `books`, `partners`, `loans` tables created according to included scripts

## Installation and configuration

1. Clone the repository:
``` bash
   git clone https://github.com/tu-org/booksnova.git
```

2. Update ``` bash
src/main/resources/config.properties with database credentials.
```

3. Build the project with Maven: ``` bash
mvn clean install
```

## Project structure

- ``` bash src/main/java/com/codeup/booksnova/domain ```
Model classes: User, Book, Partner, Loan.

- ``` bash src/main/java/com/codeup/booksnova/dao```
DAO interfaces and their JDBC implementations for CRUD.

- ``` bash src/main/java/com/codeup/booksnova/service```
Services with business logic and DAO orchestration.

- ``` bash src/main/java/com/codeup/booksnova/app ``` 
Entry points: ConsoleApp, DialogApp with JOptionPane.

## Implementation

### Domain layer
Each entity is represented with basic validations in constructors and maintains audit fields with LocalDateTime.

### DAO layer
The UserDAO, BookDAO, PartnerDAO, LoanDAO interfaces and their JDBC implementations (*DaoJdbc) are defined using ConnectionFactory to read config.properties.

### Service layer
Implementations ```bash (UserServiceImpl, BookServiceImpl, PartnerServiceImpl, LoanServiceImpl)``` that apply business rules, validations, and transactions with commit/rollback.

### User interface
- Swing Frames for user registration and login with validations and feedback via ``` bash JOptionPane.```
- ``` bash ConseleApp ``` uses ``` bash JOptionPane.showOptionDialog ``` for CRUD menus for books, partners, and loans.

## Configuration
File: ```bash File: src/main/resources/config.properties```
``` bash jdbc.url=jdbc:mysql://localhost:3306/booksnova
jdbc.user=root
jdbc.password=secret

loanPeriodDays=14
finePerDay=0.50
```
MySQL dependency in pom.xml: 
``` bash 
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <version>8.0.33</version>
</dependency>

```
## Usage
- Run the dialog version: ```bash mvn exec:java -Dexec.mainClass=“com.codeup.booksnova.app.ConseleApp”```

## Improvements and future features
- Password hashing with BCrypt
- Message internationalization
- REST API exposure with Spring Boot
- Activity reports and metrics
- Unit testing and continuous integration

# Authorship 
Developed by coder Santiago Ortega using Java best practices, OOP, and design patterns.