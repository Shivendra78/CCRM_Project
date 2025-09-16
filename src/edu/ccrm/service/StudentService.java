// File: edu/ccrm/service/StudentService.java
package edu.ccrm.service;

import edu.ccrm.domain.Student;
import edu.ccrm.util.FileUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StudentService {
    private List<Student> students = new ArrayList<>();

    public void addStudent(String id, String name, String email) {
        Student student = new Student(id, name, email, LocalDate.now());
        students.add(student);
        System.out.println("Student added: " + student);
    }

    public void listStudents() {
        System.out.println("Listing students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public Optional<Student> findStudentById(String id) {
        return students.stream()
            .filter(s -> s.getId().equals(id))
            .findFirst();
    }

    public void importStudents(String filename) throws IOException {
        try (Stream<String> lines = FileUtils.readLines(filename)) {
            lines.skip(1) // Skip header line
                .map(line -> line.split(","))
                .forEach(fields -> {
                    String id = fields[0].trim();
                    String fullName = fields[1].trim();
                    String email = fields[2].trim();
                    boolean active = Boolean.parseBoolean(fields[3].trim());
                    LocalDate enrollmentDate = LocalDate.parse(fields[4].trim());
                    Student student = new Student(id, fullName, email, enrollmentDate);
                    student.setActive(active);
                    students.add(student);
                });
        }
    }

    public void exportStudents(String filename) throws IOException {
        Stream<String> lines = Stream.concat(
            Stream.of("id,fullName,email,active,enrollmentDate"),
            students.stream()
                .map(s -> String.join(",",
                    s.getId(),
                    s.getFullName(),
                    s.getEmail(),
                    String.valueOf(s.isActive()),
                    s.getEnrollmentDate().toString()
                ))
        );
        FileUtils.writeLines(filename, lines);
    }

    public boolean updateStudent(String studentId, String newFullName, String newEmail) {
    for (Student student : students) {
        if (student.getId().equals(studentId)) {
            student.setFullName(newFullName);
            student.setEmail(newEmail);
            System.out.println("Student updated successfully.");
            return true;
        }
    }
    System.out.println("Student not found.");
    return false;
}

public boolean deactivateStudent(String studentId) {
    for (Student student : students) {
        if (student.getId().equals(studentId)) {
            student.setActive(false);
            System.out.println("Student deactivated successfully.");
            return true;
        }
    }
    System.out.println("Student not found.");
    return false;
}


}
