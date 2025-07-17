# BC Student Wellness Management System – Milestone 2

## 📅 Due Date: 17/07/2025  
## 🧑‍💻 Developed with: Java Swing, JavaDB (Derby), MVC Architecture

---

## 📌 Project Overview

This desktop application provides a management system for student wellness services at Belgium Campus. It allows for the scheduling of appointments with counselors, tracking counselor availability, and collecting student feedback, all through an intuitive Java Swing interface.

---

## ✅ Features Implemented

### 🖥️ 1. Swing GUI (Dashboard & Navigation)
- Tabbed interface for easy navigation
- Panels for:
  - Appointments
  - Counselors
  - Feedback

### 📅 2. Appointment Management (CRUD)
- Book, view, reschedule, and cancel appointments
- Integrated with JavaDB
- Data fields: student name, counselor, date, time, status

### 👩‍⚕️ 3. Counselor Management (CRUD)
- Add, view, update, and remove counselors
- Tracks specialization and availability

### 💬 4. Feedback Management (CRUD)
- Submit, view, edit, and delete feedback
- Fields include student name, rating (1–5), and comments
- Input validation and error handling in place

### 🗂️ 5. JavaDB Setup & MVC Architecture
- Database: `WellnessDB`
- Tables:
  - `Appointments`
  - `Counsellors`
  - `Feedback`
- Fully implemented MVC (Model-View-Controller) pattern:
  - Model: DB classes and data objects
  - View: Swing GUI components
  - Controller: Handles logic and database interactions
- Exception handling for SQL errors and form validation

---

## 🛠️ Technologies Used

- Java SE (Swing GUI)
- JavaDB / Derby (Embedded Mode)
- NetBeans IDE (optional) / VS Code
- MVC Design Pattern
- Java Collections (for in-memory data lists)

---

## 📁 Folder Structure
src/
├── controller/
│ ├── AppointmentController.java
│ ├── CounselorController.java
│ └── FeedbackController.java
├── model/
│ ├── Appointment.java
│ ├── Counselor.java
│ ├── Feedback.java
│ └── DBConnection.java
├── view/
│ ├── MainDashboard.java
│ ├── AppointmentPanel.java
│ ├── CounselorPanel.java
│ └── FeedbackPanel.java
└── Main.java

---

## 🧪 How to Run

1. Ensure **JavaDB** is installed and the Derby server is running.
2. Create the database `WellnessDB` and run the SQL scripts in `resources/sql/`.
3. Compile and run `Main.java` using your IDE (NetBeans, IntelliJ, or VS Code).
4. Use the dashboard to navigate between modules and perform CRUD operations.

---

## ⚠️ Validations & Error Handling

- Input validation: No empty fields, ratings must be 1–5
- Delete confirmation dialogs
- Database connection and SQL error handling via try-catch blocks and dialog alerts

---

## 👥 Authors

- **Student Name:** Petri Loots               | 600663
- **Student Name:** Kemitso Pole              | 601341
- **Student Name:** Shaun de Beer             | 601245
- **Student Name:** Daniel Richardt Scholtz   | 600162
- **Student Name:** Adolph Jacobus Van Coller | 601005
- **Module:** Programming 37(8)1  
- **Institution:** Belgium Campus  
- **Project:** Milestone 2 – Desktop Wellness System

---

## 📃 License

This project is for academic use only and is not licensed for production deployment.
