
# Library Management System

A comprehensive and easy-to-use library management application designed to streamline the process of managing books, patrons, and library transactions.

## Features

- **Book Management**: Add, update, and delete books in the library catalog.
- **Search Functionality**: Search for books by title, author, genre, or ISBN.

## Technologies Used

- **Backend**: Java, Spring Boot
- **Database**: H2

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/VALOPURUM/library-management.git
   
2. Navigate to the project directory:
   cd library-management
3. Set up your local database (H2):

H2 is an in-memory database, so no installation is required. It will run locally when the application starts.
Ensure you have the appropriate configurations set in application.properties or application.yml. The default H2 settings should work for local development.

4.Install dependencies:
./mvnw clean install

5.Run the application:
./mvnw spring-boot:run

6.Access the application at http://localhost:8080




