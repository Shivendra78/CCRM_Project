# CCRM_Project
Colleage vityarthi project
# Campus Course Records Manager (CCRM)

## Project Overview

Campus Course Records Manager (CCRM) is a Java console application designed to manage student records, courses, enrollments, and grades in a campus environment. It allows administrators to add, update, and deactivate students and courses, enroll students into courses, assign grades, calculate GPA, and manage data via CSV import/export and backup utilities.

---

## Features

- **Student Management:** Add, list, update, and deactivate students.
- **Course Management:** Add, list, update, and deactivate courses.
- **Enrollment & Grading:** Enroll/unenroll students, record grades, compute GPA.
- **File Operations:** Import/export students, courses, and enrollments using CSV files.
- **Backup & Utilities:** Backup data files and recursive folder size and file listing utilities.
- **Robust CLI:** Command-line interface with input validation, confirmation prompts, and user-friendly menus.
- **Java Concepts:** Use of enums, exceptions, assertions, design patterns (Singleton, Builder), and Java streams.

---

## Installation & Setup

- **Java Version:** Requires Java JDK 8 or above.
- **Build:** Compile all `.java` files using your IDE or command line.
- **Run:** Run the program via command line.
- **Assertions:** To enable assertions during runtime, use the `-ea` JVM option (e.g., `java -ea edu.ccrm.cli.CCRMApp`).

---

## Usage Guide

- Run the program to see the menu of options.
- Input the number of the desired action (e.g., 1 to add a student).
- Follow prompts for data entry; input validation ensures correct data formats.
- Use CSV import/export commands to manage large datasets.
- Utilize backup commands to secure data files.
- Confirm destructive actions (e.g., deactivations) when prompted.

---

## Data File Formats

- CSV files with headers:

  - Students: `studentId,fullName,email`
  - Courses: `courseCode,title,credits,instructor,semester,department`
  - Enrollments: `studentId,courseCode,grade`

- Sample CSV files can be stored in a `data/` folder alongside the application.

---

## Project Structure

- **edu.ccrm.domain:** Entity classes (Student, Course, Enrollment, Grade).
- **edu.ccrm.service:** Business logic and service classes for managing entities.
- **edu.ccrm.exceptions:** Custom exceptions like DuplicateEnrollmentException.
- **edu.ccrm.cli:** Command-line interface and user interaction.
- **edu.ccrm.util:** Utility classes for file handling and backups.

---

## Java Evolution Highlights Used

- Java 8 features: Streams, Lambdas, Optionals.
- Enums for grade representation.
- Exception handling with custom exceptions.
- Assertions to enforce preconditions.
- Design Patterns: Singleton for service instances, Builder pattern.
- Recursive algorithms for file system utilities.

---

## Java ME vs SE vs EE

- **Java ME:** Micro Edition for mobile & embedded systems.
- **Java SE:** Standard Edition for desktop and server applications (used here).
- **Java EE:** Enterprise Edition for large-scale distributed applications.

---

## JDK, JRE, JVM Explained

- **JVM:** Java Virtual Machine executes compiled Java bytecode.
- **JRE:** Java Runtime Environment contains JVM and libraries to run Java apps.
- **JDK:** Java Development Kit includes JRE plus tools/compiler for development.

---

## Screenshots

*(Include screenshots showing main menu, adding a student, enrolling a student, etc. Paste images if submitting with files.)*

---

## Acknowledgements

This project is developed as part of the Advanced Java coursework.  
No external libraries used beyond Java standard library.

---

