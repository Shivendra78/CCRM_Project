package edu.ccrm.domain;

import java.time.LocalDate;
import edu.ccrm.domain.Student;
import edu.ccrm.domain.Course;

public class Enrollment {
    private Student student;
    private Course course;
    private LocalDate enrollmentDate;
    private Grade grade;

    public Enrollment(Student student, Course course, LocalDate enrollmentDate){
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    public Grade getGrade(){
        return grade;
    }

    public void setGrade(Grade grade){
        this.grade = grade;
    }

    public Student getStudent(){
        return student;
    }

    public Course getCourse(){
        return course;
    }

    public LocalDate getEnrollmentDate(){
        return enrollmentDate;
    }

    @Override
    public String toString(){
        String gradeStr = (grade != null) ? grade.toString() : "N/A";
        return "Enrollment{" + student.getFullName() + " enrolled in " + course.getCode() +
                ", Grade: " + gradeStr + ", Enrolled on " + enrollmentDate + "}";
    }

}
