package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;
import edu.ccrm.util.FileUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.IOException;

import edu.ccrm.exceptions.DuplicateEnrollmentException;
import edu.ccrm.exceptions.MaxCreditLimitExceededException;

public class EnrollmentService {

    private StudentService studentService;
    private CourseService courseService;
    private static final int MAX_CREDITS_PER_SEMESTER = 18;
    private List<Enrollment> enrollments = new ArrayList<>();

    public void enroll(Student student, Course course) throws DuplicateEnrollmentException, MaxCreditLimitExceededException {
    // Check if student is already enrolled in the course
    boolean alreadyEnrolled = enrollments.stream()
        .anyMatch(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
    if (alreadyEnrolled) {
        throw new DuplicateEnrollmentException("Student " + student.getId() + " is already enrolled in course " + course.getCode());
    }

    // Check max credit limit rule
    int currentCredits = getTotalCreditsForStudentSemester(student, course.getSemester());
    if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
        throw new MaxCreditLimitExceededException("Enrolling in this course exceeds the maximum allowed credits per semester.");
    }

    // Proceed with enrollment
    Enrollment enrollment = new Enrollment(student, course, LocalDate.now());
    enrollments.add(enrollment);
    System.out.println("Enrolled: " + enrollment);
}
    public void unenroll(Student student, Course course) {
        enrollments.removeIf(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
        System.out.println("Unenrolled student " + student.getFullName() + " from course " + course.getCode());
    }

    public boolean recordGrade(Student student, Course course, Grade grade) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().equals(student) &&
                enrollment.getCourse().equals(course)) {
                enrollment.setGrade(grade);
                System.out.println("Recorded grade " + grade + " for " + student.getFullName());
                return true;
            }
        }
        System.out.println("Enrollment not found.");
        return false;
    }

    public double calculateGPA(Student student) {
        int totalPoints = 0;
        int totalCredits = 0;
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudent().equals(student) && enrollment.getGrade() != null) {
                int credits = enrollment.getCourse().getCredits();
                int points = enrollment.getGrade().getGradePoints();
                totalPoints += credits * points;
                totalCredits += credits;
            }
        }
        if (totalCredits == 0) return 0.0;
        return (double) totalPoints / totalCredits;
    }

    public List<Enrollment> getEnrollmentsForStudent(Student student) {
        return enrollments.stream()
                .filter(e -> e.getStudent().equals(student))
                .collect(Collectors.toList());
    }

    private int getTotalCreditsForStudentSemester(Student student, String semester) {
        return enrollments.stream()
                .filter(e -> e.getStudent().equals(student) && e.getCourse().getSemester().equals(semester))
                .mapToInt(e -> e.getCourse().getCredits())
                .sum();
    }

    // Example import/export methods can be added here as discussed previously
     public void importEnrollments(String filename) throws IOException {
        try (Stream<String> lines = FileUtils.readLines(filename)) {
            lines.skip(1)
                .map(line -> line.split(","))
                .forEach(fields -> {
                    String studentId = fields[0].trim();
                    String courseCode = fields[1].trim();
                    LocalDate enrollmentDate = LocalDate.parse(fields[2].trim());
                    String gradeStr = fields.length > 3 ? fields[3].trim() : null;

                    Student student = studentService.findStudentById(studentId).orElse(null);
                    Course course = courseService.searchByCode(courseCode);

                    if (student != null && course != null) {
                        Enrollment enrollment = new Enrollment(student, course, enrollmentDate);
                        if (gradeStr != null && !gradeStr.isEmpty()) {
                            try {
                                Grade grade = Grade.valueOf(gradeStr);
                                enrollment.setGrade(grade);
                            } catch (IllegalArgumentException e) {
                                // Invalid grade, ignore or log here
                            }
                        }
                        enrollments.add(enrollment);
                    }
                });
        }
    }

    public void exportEnrollments(String filename) throws IOException {
        Stream<String> lines = Stream.concat(
            Stream.of("studentId,courseCode,enrollmentDate,grade"),
            enrollments.stream()
                .map(e -> String.join(",",
                    e.getStudent().getId(),
                    e.getCourse().getCode(),
                    e.getEnrollmentDate().toString(),
                    e.getGrade() != null ? e.getGrade().name() : ""
                ))
        );
        FileUtils.writeLines(filename, lines);
    }
}



