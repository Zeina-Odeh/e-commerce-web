# E-Commerce Website

This is a full-stack e-commerce website built with:
- **Frontend**: Angular
- **Backend**: Spring Boot
- **Database**: PostgreSQL

## Features
- User registration and login
- JWT authentication
- Product catalog
- Shopping cart functionality
- Order management

## Folder Structure
- **`frontend/`**: Contains the Angular front-end application.
- **`backend/`**: Contains the Spring Boot back-end application.
- **`postgreSQL/`**: Contains database scripts and configurations.

## Setup Instructions

### Prerequisites
Ensure you have the following installed on your machine:
- **Node.js** (for the frontend)
- **Java** and **Maven** (for the backend)
- **PostgreSQL** (for the database)

### Frontend Setup
1. Navigate to the `frontend` folder:
   ```bash
   cd frontend
2. Install dependencies:
   ```bash
    npm install
4. Start the development server:
   ```bash
   npm start
5. Open your browser and navigate to:
   ```bash
   http://localhost:4200

### Backend Setup
1. Navigate to the backend folder:
   ```bash
     cd backend
2. Build the Spring Boot application:
   ```bash
    mvn clean install
3. Run the application:
   ```bash
    mvn spring-boot:run
4. The backend will run on:
   ```bash
    http://localhost:8080

### Database Setup (PostgreSQL)
1. Navigate to the postgreSQL folder.
2. Open your PostgreSQL client (e.g., pgAdmin or psql).
3. Create a new database:
   ```bash
   CREATE DATABASE ecommerce;
4. Import the SQL script provided in the postgreSQL folder into the ecommerce database.

### Running the Full Stack
1. Start the backend server.
2. Start the frontend development server.
3. Ensure PostgreSQL is running and properly configured.
4. Access the application through the frontend URL.
