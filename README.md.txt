# Employee Management System (MVC Architecture)

A simple Java-based Employee Management System developed using the **MVC (Model–View–Controller)** architecture.  
This project demonstrates clean separation of concerns and basic CRUD operations using JDBC.

---

## 📌 Features

- Add new employee
- View all employees
- Update employee details
- Delete employee records
- MVC architecture implementation
- JDBC-based database interaction
- Console-based user interface

---

## 🧠 MVC Architecture Overview

- **Model**  
  Represents employee data and database logic  
  (Employee class, DAO classes)

- **View**  
  Handles user interaction through console input/output

- **Controller**  
  Acts as a bridge between View and Model, processing user requests

---

## 🛠️ Technologies Used

- Java (JDK 8 or above)
- JDBC
- PostgreSQL / MySQL (any relational DB)
- Eclipse IDE
- Git & GitHub

---

## 📂 Project Structure
employee_management_system
├── src/
│ ├── model/
│ │ └── Employee.java
│ ├── dao/
│ │ └── EmployeeDAO.java
│ ├── view/
│ │ └── EmployeeView.java
│ ├── controller/
│ │ └── EmployeeController.java
│ └── Main.java
├── .gitignore
├── README.md
└── lib/
└── jdbc-driver.jar


---

## ⚙️ Prerequisites

- Java JDK installed
- Database installed and running
- JDBC Driver added to project build path
- Eclipse IDE (or any Java IDE)

---

