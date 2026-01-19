# Expense Tracker

A full-stack Expense & Income Tracking Web Application that helps users manage finances by tracking income, expenses, and real-time profit/loss.

Built using Spring Boot for the backend and Vanilla HTML, CSS, and JavaScript for the frontend.

---

## Features

### Authentication
- Secure user login and signup
- JWT-based authentication
- Token-protected APIs

### Income Management
- Add income with description, amount, category, and date
- View all income entries in a grid/card layout
- Filter income by category
- Delete income entries

### Expense Management
- Add expenses with description, amount, category, and date
- View all expense entries in a grid/card layout
- Filter expenses by category
- Delete expense entries

### Dashboard
- Displays total income
- Displays total expense
- Automatically calculates profit or loss
- Shows the last 5 income entries
- Shows the last 5 expense entries

### UI
- Dark-themed, clean, and responsive layout
- Separate pages for Dashboard, Income, and Expense
- Navigation opens Income and Expense pages in new browser tabs

---

## Tech Stack

### Frontend
- HTML5
- CSS3
- Vanilla JavaScript
- Fetch API

### Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- REST APIs

### Database
- MySQL (or compatible relational database)

---

## Project Structure

ExpenseTracker/
├── backend/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── model/
│   ├── security/
│   └── ExpenseTrackerApplication.java
│
├── frontend/
│   ├── index.html
│   ├── dashboard.html
│   ├── income.html
│   ├── expense.html
│   ├── css/
│   └── js/
│
└── README.md

---

## API Endpoints

### Authentication
POST /api/auth/login  
POST /api/auth/signup  

### Income
POST /api/income/add  
GET /api/income/all  
DELETE /api/income/{id}  

### Expense
POST /api/expense/add  
GET /api/expense/all  
DELETE /api/expense/{id}  

All protected endpoints require the following header:

Authorization: Bearer <JWT_TOKEN>

---

## Running the Project Locally

### Backend Setup
1. Navigate to the backend directory
2. Run the application using:

mvn spring-boot:run

Backend will start on:

http://localhost:8080

---

### Frontend Setup
You can either:
- Open the HTML files using Live Server  
OR
- Use a simple static server:

npx serve frontend

---

## Environment Configuration

Update the following file:

src/main/resources/application.properties

Example configuration:

spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker  
spring.datasource.username=root  
spring.datasource.password=your_password  

jwt.secret=your_secret_key  

---

## Future Enhancements
- Graphs and analytics
- Monthly and yearly reports
- CSV export
- User profile management
- Budget planning

---

## Author

Mohd Aman  
Computer Science & Engineering  
Lovely Professional University

---

## License

This project is open-source and available for learning and personal use.
