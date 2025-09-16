// File: edu/ccrm/domain/Student.java
package edu.ccrm.domain;

import java.time.LocalDate;

public class Student {
    private String id;
    private String fullName;
    private String email;
    private boolean active;
    private LocalDate enrollmentDate;

    public Student(String id, String fullName, String email, LocalDate enrollmentDate) {
        assert id != null && !id.isEmpty() : "Student ID cannot be null or empty";
        assert fullName != null && !fullName.isEmpty() : "Full name cannot be null or empty";
        assert email != null && email.contains("@") : "Email must be valid";
        assert enrollmentDate != null : "Enrollment date cannot be null";

        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.active = true;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and setters

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + fullName + "', email='" + email + "', active=" + active
                + ", enrolled=" + enrollmentDate + "}";
    }
}
