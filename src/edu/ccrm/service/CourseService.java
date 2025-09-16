package edu.ccrm.service;


import edu.ccrm.domain.Course;
import edu.ccrm.util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CourseService {
    private List<Course> courses = new ArrayList<>();

    public void addCourse(String code, String title, int credits, String instructor, String semester) {
        Course course = new Course(code, title, credits, instructor, semester);
        courses.add(course);
        System.out.println("Course added: " + course);
    }
    public Course searchByCode(String code) {
    return courses.stream()
            .filter(c -> c.getCode().equalsIgnoreCase(code))
            .findFirst()
            .orElse(null);
    }


    public void listCourses() {
        System.out.println("Listing courses:");
        courses.forEach(System.out::println);
    }

      public void importCourses(String filename) throws IOException {
        try (Stream<String> lines = FileUtils.readLines(filename)) {
            lines.skip(1)
                .map(line -> line.split(","))
                .forEach(fields -> {
                    String code = fields[0].trim();
                    String title = fields[1].trim();
                    int credits = Integer.parseInt(fields[2].trim());
                    String instructor = fields[3].trim();
                    String semester = fields[4].trim();
                    Course course = new Course(code, title, credits, instructor, semester);
                    courses.add(course);
                });
        }
    }

     public void exportCourses(String filename) throws IOException {
        Stream<String> lines = Stream.concat(
            Stream.of("code,title,credits,instructor,semester"),
            courses.stream()
                .map(c -> String.join(",",
                    c.getCode(),
                    c.getTitle(),
                    String.valueOf(c.getCredits()),
                    c.getInstructor(),
                    c.getSemester()
                ))
        );
        FileUtils.writeLines(filename, lines);
    }

    
    public List<Course> searchByInstructor(String instructor) {
        return courses.stream()
                .filter(c -> c.getInstructor().equalsIgnoreCase(instructor))
                .collect(Collectors.toList());
    }

    public boolean updateCourse(String courseCode, String newTitle, int newCredits, String newInstructor, String newSemester, String newDepartment) {
    for (Course course : courses) {
        if (course.getCode().equals(courseCode)) {
            course.setTitle(newTitle);
            course.setCredits(newCredits);
            course.setInstructor(newInstructor);
            course.setSemester(newSemester);
            course.setDepartment(newDepartment);
            System.out.println("Course updated successfully.");
            return true;
        }
    }
    System.out.println("Course not found.");
    return false;
}

public boolean deactivateCourse(String courseCode) {
    for (Course course : courses) {
        if (course.getCode().equals(courseCode)) {
            course.setActive(false);
            System.out.println("Course deactivated successfully.");
            return true;
        }
    }
    System.out.println("Course not found.");
    return false;
}

}
