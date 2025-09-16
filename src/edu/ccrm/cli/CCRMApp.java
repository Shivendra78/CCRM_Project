package edu.ccrm.cli;

import edu.ccrm.service.StudentService;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.BackupService;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.Grade;
import edu.ccrm.exceptions.DuplicateEnrollmentException;
import edu.ccrm.exceptions.MaxCreditLimitExceededException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CCRMApp {
    private static Scanner scanner = new Scanner(System.in);
    private static CourseService courseService = new CourseService();
    private static EnrollmentService enrollmentService = new EnrollmentService();
    private static StudentService studentService = new StudentService();
    private static boolean isValidEmail(String email) {
    return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
}


    public static void main(String[] args) {
        menuLoop:
        while (true) {
            System.out.println("\nCampus Course Records Manager (CCRM)");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Exit");
            System.out.println("4. Add Course");
            System.out.println("5. List Courses");
            System.out.println("6. Enroll Student in Course");
            System.out.println("7. Unenroll Student from Course");
            System.out.println("8. List Enrollments for Student");
            System.out.println("9. Record Grade for Student");
            System.out.println("10. View Student Transcript");
            System.out.println("11. Import Students from CSV");
            System.out.println("12. Export Students to CSV");
            System.out.println("13. Import Courses from CSV");
            System.out.println("14. Export Courses to CSV");
            System.out.println("15. Import Enrollments from CSV");
            System.out.println("16. Export Enrollments to CSV");
            System.out.println("17. Update Student");
            System.out.println("18. Deactivate Student");
            System.out.println("19. Update Course");
            System.out.println("20. Deactivate Course");
            System.out.println("21. Backup data files");
            System.out.println("22. Show backup folder size");
            System.out.println("23. List backup files recursively");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                        System.out.print("Enter Student ID: ");
                        String id = scanner.nextLine().trim();

                         System.out.print("Enter Full Name: ");
                         String name = scanner.nextLine().trim();

                        String email;
                        while (true) {
                        System.out.println("Enter Email: ");
                        email = scanner.nextLine().trim();
                        if (!isValidEmail(email)) {
                        System.out.println("Invalid email format. Please enter a valid email.");
                        } else {
                        break;
                        }
                }

                        studentService.addStudent(id, name, email);
                        break;


                case "2":
                    studentService.listStudents();
                    break;

                case "3":
                    System.out.println("Exiting...");
                    break menuLoop;

                case "4":
                    System.out.print("Enter Course Code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();

                    int credits;
                    while (true) {
                        System.out.print("Enter Credits: ");
                        String input = scanner.nextLine().trim();
                        try {
                            credits = Integer.parseInt(input);
                            if (credits <= 0) {
                                System.out.println("Credits must be a positive number.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid integer for credits.");
                        }
                    }

                    System.out.print("Enter Instructor: ");
                    String instructor = scanner.nextLine();
                    System.out.print("Enter Semester: ");
                    String semester = scanner.nextLine();

                    courseService.addCourse(code, title, credits, instructor, semester);
                    break;

                case "5":
                    courseService.listCourses();
                    break;

                case "6":
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();

                    Student student = studentService.findStudentById(studentId).orElse(null);
                    Course course = courseService.searchByCode(courseCode);

                    if (student == null) {
                        System.out.println("Student not found.");
                        break;
                    }
                    if (course == null) {
                        System.out.println("Course not found.");
                        break;
                    }

                    try {
                        enrollmentService.enroll(student, course);
                        System.out.println("Enrollment successful.");
                    } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException e) {
                        System.out.println("Enrollment failed: " + e.getMessage());
                    }
                    break;

                case "7":
                    System.out.print("Enter Student ID to unenroll: ");
                    String studentIdUnenroll = scanner.nextLine();
                    System.out.print("Enter Course Code to unenroll from: ");
                    String courseCodeUnenroll = scanner.nextLine();

                    Student studentToUnenroll = studentService.findStudentById(studentIdUnenroll).orElse(null);
                    Course courseToUnenroll = courseService.searchByCode(courseCodeUnenroll);

                    if (studentToUnenroll == null) {
                        System.out.println("Student not found.");
                    } else if (courseToUnenroll == null) {
                        System.out.println("Course not found.");
                    } else {
                        enrollmentService.unenroll(studentToUnenroll, courseToUnenroll);
                    }
                    break;

                case "8":
                    System.out.print("Enter Student ID to list enrollments: ");
                    String studentIdList = scanner.nextLine();
                    Student studentToList = studentService.findStudentById(studentIdList).orElse(null);

                    if (studentToList == null) {
                        System.out.println("Student not found.");
                    } else {
                        var enrollments = enrollmentService.getEnrollmentsForStudent(studentToList);
                        System.out.println("Enrollments for " + studentToList.getFullName() + ":");
                        enrollments.forEach(System.out::println);
                    }
                    break;

                case "9":
                    System.out.print("Enter Student ID: ");
                    String studentIdGrade = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String courseCodeGrade = scanner.nextLine();
                    System.out.print("Enter Grade (S, A, B, C, F): ");
                    String gradeInput = scanner.nextLine().toUpperCase();

                    Student studentGrade = studentService.findStudentById(studentIdGrade).orElse(null);
                    Course courseGrade = courseService.searchByCode(courseCodeGrade);

                    if (studentGrade == null) {
                        System.out.println("Student not found.");
                    } else if (courseGrade == null) {
                        System.out.println("Course not found.");
                    } else {
                        try {
                            Grade grade = Grade.valueOf(gradeInput);
                            enrollmentService.recordGrade(studentGrade, courseGrade, grade);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid grade entered.");
                        }
                    }
                    break;

                case "10":
                    System.out.print("Enter Student ID: ");
                    String studentIdTranscript = scanner.nextLine();
                    Student studentTranscript = studentService.findStudentById(studentIdTranscript).orElse(null);

                    if (studentTranscript == null) {
                        System.out.println("Student not found.");
                    } else {
                        var enrollments = enrollmentService.getEnrollmentsForStudent(studentTranscript);
                        System.out.println("Transcript for " + studentTranscript.getFullName() + ":");
                        enrollments.forEach(System.out::println);
                        double gpa = enrollmentService.calculateGPA(studentTranscript);
                        System.out.printf("GPA: %.2f\n", gpa);
                    }
                    break;

                case "11":
                    System.out.print("Enter CSV filename to import students (e.g. data/students.csv): ");
                    String importFile = scanner.nextLine();
                    try {
                        studentService.importStudents(importFile);
                        System.out.println("Students imported successfully.");
                    } catch (IOException e) {
                        System.out.println("Error importing students: " + e.getMessage());
                    }
                    break;

                case "12":
                    System.out.print("Enter CSV filename to export students (e.g. data/students.csv): ");
                    String exportFile = scanner.nextLine();
                    try {
                        studentService.exportStudents(exportFile);
                        System.out.println("Students exported successfully.");
                    } catch (IOException e) {
                        System.out.println("Error exporting students: " + e.getMessage());
                    }
                    break;

                case "13":
                    System.out.print("Enter CSV filename to import courses (e.g. data/courses.csv): ");
                    String importCourseFile = scanner.nextLine();
                    try {
                        courseService.importCourses(importCourseFile);
                        System.out.println("Courses imported successfully.");
                    } catch (IOException e) {
                        System.out.println("Error importing courses: " + e.getMessage());
                    }
                    break;

                case "14":
                    System.out.print("Enter CSV filename to export courses (e.g. data/courses.csv): ");
                    String exportCourseFile = scanner.nextLine();
                    try {
                        courseService.exportCourses(exportCourseFile);
                        System.out.println("Courses exported successfully.");
                    } catch (IOException e) {
                        System.out.println("Error exporting courses: " + e.getMessage());
                    }
                    break;

                case "15":
                    System.out.print("Enter CSV filename to import enrollments (e.g. data/enrollments.csv): ");
                    String importEnrollFile = scanner.nextLine();
                    try {
                        enrollmentService.importEnrollments(importEnrollFile);
                        System.out.println("Enrollments imported successfully.");
                    } catch (IOException e) {
                        System.out.println("Error importing enrollments: " + e.getMessage());
                    }
                    break;

                case "16":
                    System.out.print("Enter CSV filename to export enrollments (e.g. data/enrollments.csv): ");
                    String exportEnrollFile = scanner.nextLine();
                    try {
                        enrollmentService.exportEnrollments(exportEnrollFile);
                        System.out.println("Enrollments exported successfully.");
                    } catch (IOException e) {
                        System.out.println("Error exporting enrollments: " + e.getMessage());
                    }
                    break;

                case "17":
                        System.out.print("Enter student ID to update: ");
                        String stuIdUpdate = scanner.nextLine().trim();

                        System.out.print("Enter new full name: ");
                        String fullNameUpdate = scanner.nextLine().trim();

                        String emailUpdate;
                        while (true) {
                        System.out.print("Enter new email: ");
                        emailUpdate = scanner.nextLine().trim();
                        if (!isValidEmail(emailUpdate)) {
                          System.out.println("Invalid email format. Please enter a valid email.");
                        } else {
                         break;
        }
    }

                    studentService.updateStudent(stuIdUpdate, fullNameUpdate, emailUpdate);
                    break;


                    case "18":  // Deactivate Student
                     System.out.print("Enter student ID to deactivate: ");
                    String stuIdDeactivate = scanner.nextLine();
                    System.out.print("Are you sure you want to deactivate this student? (y/n): ");
                    String confirmStu = scanner.nextLine().trim().toLowerCase();
                    if (confirmStu.equals("y")) {
                    studentService.deactivateStudent(stuIdDeactivate);
                    System.out.println("Student deactivated.");
                    } else {
                    System.out.println("Deactivation cancelled.");
                     }
                     break;


                case "19":
                    System.out.print("Enter course code to update: ");
                    String courseCodeUpdate = scanner.nextLine();
                    System.out.print("Enter new title: ");
                    String titleUpdate = scanner.nextLine();

                    int creditsUpdate;
                    while (true) {
                        System.out.print("Enter new credits: ");
                        String creditsInput = scanner.nextLine().trim();
                        try {
                            creditsUpdate = Integer.parseInt(creditsInput);
                            if (creditsUpdate <= 0) {
                                System.out.println("Credits must be a positive number.");
                                continue;
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid integer for credits.");
                        }
                    }

                    System.out.print("Enter new instructor: ");
                    String instructorUpdate = scanner.nextLine();
                    System.out.print("Enter new semester: ");
                    String semesterUpdate = scanner.nextLine();
                    System.out.print("Enter new department: ");
                    String departmentUpdate = scanner.nextLine();

                    courseService.updateCourse(courseCodeUpdate, titleUpdate, creditsUpdate, instructorUpdate, semesterUpdate, departmentUpdate);
                    break;

               case "20":  // Deactivate Course
                    System.out.print("Enter course code to deactivate: ");
                    String courseCodeDeactivate = scanner.nextLine();
                    System.out.print("Are you sure you want to deactivate this course? (y/n): ");
                     String confirmCourse = scanner.nextLine().trim().toLowerCase();
                     if (confirmCourse.equals("y")) {
                     courseService.deactivateCourse(courseCodeDeactivate);
                     System.out.println("Course deactivated.");
                     } else {
                    System.out.println("Deactivation cancelled.");
                    }
                    break;


                case "21":
                    try {
                        BackupService.backupDataFiles("data/students.csv", "data/courses.csv", "data/enrollments.csv");
                    } catch (IOException e) {
                        System.out.println("Backup failed: " + e.getMessage());
                    }
                    break;

                case "22":
                    try {
                        Path backupRoot = Paths.get("backup");
                        if (Files.exists(backupRoot)) {
                            long sizeInBytes = BackupService.calculateDirectorySize(backupRoot);
                            System.out.println("Total backup directory size: " + sizeInBytes + " bytes");
                        } else {
                            System.out.println("No backups found.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error calculating backup size: " + e.getMessage());
                    }
                    break;

                case "23":
                    System.out.print("Enter recursion depth (e.g., 2): ");
                    int depth = Integer.parseInt(scanner.nextLine());
                    try {
                        Path backupRoot = Paths.get("backup");
                        if (Files.exists(backupRoot)) {
                            BackupService.printFilesRecursively(backupRoot, depth);
                        } else {
                            System.out.println("No backups found.");
                        }
                    } catch (IOException e) {
                        System.out.println("Error listing backup files: " + e.getMessage());
                    }
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }

        scanner.close();
    }
}
