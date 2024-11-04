package Models;

public class Student extends User {
    boolean enrolledCourse;

    public Student(int userID, String userName, String password, String email, boolean enrolledCourse) {
        super(userID, userName, password, email);
        this.enrolledCourse = enrolledCourse;
    }

    public boolean isEnrolledCourse() {
        return this.enrolledCourse;
    }

    public Assignment submitAssignment(Assignment assignment) {
        return assignment;
    }

}
