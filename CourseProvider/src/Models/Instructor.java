package Models;

public class Instructor extends User {
    private String department;
    private Course[] courses;

    Instructor(int userID, String userName, String password, String email) {
        super(userID, userName, password, email);
    }

    public String getDepartment() {
        return this.department;
    }

    public Course[] getCourses() {
        return this.courses;
    }

    public Assignment gradeAssignment(Assignment assignment) {
        return assignment;
    }
}
