package Models;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends User {
    private String department;
    private List<Course> courses;

    public Instructor(int userID, String userName, String password, String email) {
        super(userID, userName, password, email);
    }

    public String getDepartment() {
        return this.department;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public Assignment gradeAssignment(Assignment assignment) {
        return assignment;
    }

}
