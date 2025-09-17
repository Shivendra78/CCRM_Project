# CCRM_Project
Collage vityarthi project
# Campus Course Records Manager (CCRM)

## Project Overview

Campus Course Records Manager (CCRM) is a Java console application for managing students, courses, enrollments, and grades.
It helps administrators add, update, and deactivate students and courses, enroll students, assign grades, 
calculate GPA, and handle CSV import/export with backup support.

---

## Features

- Student Management:Add, list, update, and deactivate students.
- Course Management: Add, list, update, and deactivate courses.
- Enrollment & Grading: Enroll/unenroll students, record grades, compute GPA.
- File Operations:Import/export students, courses, and enrollments using CSV files.
- Backup & Utilities:Backup data files and recursive folder size and file listing utilities.
- Robust CLI: Command-line interface with input validation, confirmation prompts, and user-friendly menus.
- Java Concepts: Use of enums, exceptions, assertions, design patterns (Singleton, Builder), and Java streams.

---

## Installation & Setup

- Java Version:** Requires Java JDK 8 or above.
- Build:Compile all `.java` files using your IDE or command line.
- Run: Run the program via command line.
- Assertions: To enable assertions during runtime, use the `-ea` JVM option (e.g., `java -ea edu.ccrm.cli.CCRMApp`).

---

## Usage Guide

- Run the program to see the menu of options.
- Enter the number of the perform desired action (e.g., 1 to add a student).
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

- edu.ccrm.domain:Entity classes (Student, Course, Enrollment, Grade).
- edu.ccrm.service: Business logic and service classes for managing entities.
- edu.ccrm.exceptions:Custom exceptions like DuplicateEnrollmentException.
- edu.ccrm.cli: Command-line interface and user interaction.
- edu.ccrm.util: Utility classes for file handling and backups.

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

- Java ME:Micro Edition for mobile & embedded systems.
- Java SE:Standard Edition for desktop and server applications (used here).
- Java EE:Enterprise Edition for large-scale distributed applications.

---

## JDK, JRE, JVM Explained

- JVM:Java Virtual Machine executes compiled Java bytecode.
- JRE:Java Runtime Environment contains JVM and libraries to run Java apps.
- JDK:Java Development Kit includes JRE plus tools/compiler for development.

---

## Screenshots
## JDK Version Check and output snapshot
<img width="1148" height="371" alt="image" src="https://github.com/user-attachments/assets/5055185d-f29d-4289-9345-65c476797d26" />

## Commands that you have to follow for compiling this project
<img width="1362" height="122" alt="image" src="https://github.com/user-attachments/assets/ed698bdb-7edb-44cb-a4e9-49a5d1fc8400" />

## Main Menu Shows the list of all the operations that user can choose according to their preference
<img width="778" height="734" alt="image" src="https://github.com/user-attachments/assets/430a0eef-86bc-44de-982e-617f0db02ad0" />

## Now Selecting one by one these operations and showing the result
## Adding student screenshot
<img width="1904" height="222" alt="image" src="https://github.com/user-attachments/assets/0b64b07e-15b1-45cb-8a4f-551342e1d909" />

## When i select Listing student then output
<img width="1905" height="800" alt="image" src="https://github.com/user-attachments/assets/29e1549a-721b-4790-9568-9786dd7dc9e4" />

## We can add courses by selecting option 4 as
<img width="1338" height="895" alt="image" src="https://github.com/user-attachments/assets/8acc3af2-59f1-4d22-8a11-0ff11b45f971" />

## we can also list the course by selecting option 5
<img width="1269" height="778" alt="image" src="https://github.com/user-attachments/assets/a4e04553-b6c6-4b76-8333-e6c15e70992d" />

## We can enroll the student in courses by selecting option 6
<img width="1502" height="840" alt="image" src="https://github.com/user-attachments/assets/41e1e2b9-3e25-471a-8fb7-07200ba7aac0" />

## I add kausal as a student now let's uneroll that student by using option 7
<img width="862" height="831" alt="image" src="https://github.com/user-attachments/assets/a7060cc8-fdd2-42cd-9954-10694132088c" />

## Now we record Grade for student using option 9
<img width="953" height="859" alt="image" src="https://github.com/user-attachments/assets/a749ef5e-9d67-4aa5-8661-eb7e929b1a73" />

## We can list enrollment for student using option 8
<img width="1253" height="819" alt="image" src="https://github.com/user-attachments/assets/a0a334a9-00ba-4927-a8c0-7926bb122267" />

## WE can also see Student Transscipt using option 10
<img width="1365" height="830" alt="image" src="https://github.com/user-attachments/assets/ebb9b006-f1ea-44b3-af73-64a9e6c532d2" />

## we also import student from csv by using option 11
<img width="1191" height="770" alt="image" src="https://github.com/user-attachments/assets/e1e686d1-b9e0-4881-87c4-d071f19d5c5a" />

## we can also export students to Csv by Selecting option 12
<img width="1324" height="795" alt="image" src="https://github.com/user-attachments/assets/1b84a612-f671-4119-9e38-61cadba37a81" />
<img width="302" height="109" alt="image" src="https://github.com/user-attachments/assets/8a8040cb-8518-45d5-bee6-633ab5f0470d" />


## Similarly we export courses to csv by selecting option 14
<img width="1221" height="800" alt="image" src="https://github.com/user-attachments/assets/be3e069c-ba54-4405-8cee-f3a08db249de" />

## you can also export enrollments to csv by selecting option 16
<img width="1298" height="780" alt="image" src="https://github.com/user-attachments/assets/57634093-3084-42be-8d83-5d29b2c2920c" />
<img width="570" height="180" alt="image" src="https://github.com/user-attachments/assets/ff832d70-1473-4dcc-8325-dd216d495db5" />


## We can also update Details of Student by selecting option 17
<img width="864" height="846" alt="image" src="https://github.com/user-attachments/assets/6006d074-e3e3-473c-bd16-fa6110f473be" />

## You can also deactivate Student if needed using option 18
<img width="913" height="835" alt="image" src="https://github.com/user-attachments/assets/53fa2ad7-4525-4c7c-b7c8-e2038e142bad" />

## We can also update course by selecting option 19
<img width="741" height="910" alt="image" src="https://github.com/user-attachments/assets/d5036f51-5355-4181-8bc5-e7f86071ca12" />
<img width="623" height="122" alt="image" src="https://github.com/user-attachments/assets/9dc5853e-619e-4b04-bc5b-921d64f7c8d1" />


## we can also Deactivate the course by selecting option 20
<img width="933" height="830" alt="image" src="https://github.com/user-attachments/assets/e0a43fe2-5af8-42e8-923b-1039198bc2c2" />

## we can also backup file using selecting option 21
<img width="1346" height="756" alt="image" src="https://github.com/user-attachments/assets/a184360b-f654-48e9-85e3-09971bc6cd5a" />

## We can also check backup directory size by using option 22
<img width="805" height="762" alt="image" src="https://github.com/user-attachments/assets/92b5801b-9083-4680-b418-b0330a80893f" />

## we can also list back up file recursively using option 23
<img width="720" height="824" alt="image" src="https://github.com/user-attachments/assets/a50c5359-c4ba-48f3-8042-bf4e5a547447" />


## Acknowledgements

This project is developed as part of the Advanced Java coursework.  
No external libraries used beyond Java standard library.

---

