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

2. Actualizar ``` bash
src/main/resources/config.properties con credenciales de la base de datos.
```

3. Construir el proyecto con Maven: ``` bash
mvn clean install
```

## Estrutucra del proyecto

- ``` bash src/main/java/com/codeup/booksnova/domain ```
Clases de modelo: User, Book, Partner, Loan.

- ``` bash src/main/java/com/codeup/booksnova/dao```
Interfaces DAO y sus implementaciones JDBC para CRUD.

- ``` bash src/main/java/com/codeup/booksnova/service```
Servicios con la lógica de negocio y orquestación de DAOs.

- ``` bash src/main/java/com/codeup/booksnova/app ``` 
Puntos de entrada: ConsoleApp, DialogApp con JOptionPane.

## Implementacion

### Capa de dominio
Cada entidad está representada con validaciones básicas en constructores y mantiene campos de auditoría con LocalDateTime.

### Capa DAO
Se definen las interfaces UserDAO, BookDAO, PartnerDAO, LoanDAO y sus implementaciones JDBC (*DaoJdbc) usando ConnectionFactory para leer config.properties.

### Capa de servicio
Implementaciones ```bash (UserServiceImpl, BookServiceImpl, PartnerServiceImpl, LoanServiceImpl)``` que aplican reglas de negocio, validaciones y transacciones con commit/rollback.

### Interfaz de usuario
- Swing Frames para registro y login de usuarios con validaciones y feedback vía ``` bash JOptionPane.```
- ``` bash ConseleApp ``` usa ``` bash JOptionPane.showOptionDialog ``` para menús CRUD de libros, socios y préstamos.

## Configuracion
Archivo: ```bash Archivo: src/main/resources/config.properties```
``` bash jdbc.url=jdbc:mysql://localhost:3306/booksnova
jdbc.user=root
jdbc.password=secret

loanPeriodDays=14
finePerDay=0.50
```
Dependencia de MySQL en pom.xml: 
``` bash 
<dependency>
  <groupId>com.mysql</groupId>
  <artifactId>mysql-connector-j</artifactId>
  <version>8.0.33</version>
</dependency>

```
## Uso
- Ejecutar la versión dialogada: ```bash mvn exec:java -Dexec.mainClass="com.codeup.booksnova.app.ConseleApp"```

## Mejoras y futuras funcionalidades
- Hasheo de contraseñas con BCrypt
- Internacionalización de mensajes
- Exposición de API REST con Spring Boot
- Reportes de actividad y métricas
- Pruebas unitarias e integración continua

# Autoria 
Desarrollado por el coder Santiago Ortega buenas prácticas de Java, OOP y patrones de diseño.