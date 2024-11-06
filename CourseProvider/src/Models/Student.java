package Models;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    boolean enrolledCourse;
    private final List<Course> courses = new ArrayList<>();

    public Student(int userID, String userName, String password, String email, boolean enrolledCourse) {
        super(userID, userName, password, email);
        this.enrolledCourse = enrolledCourse;
    }

    public boolean isEnrolledCourse() {
        return this.enrolledCourse;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Assignment submitAssignment(Assignment assignment) {
        return assignment;
    }

}
