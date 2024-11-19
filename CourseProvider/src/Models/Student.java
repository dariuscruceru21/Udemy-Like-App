package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student user within the system.
 * A student has additional attributes and methods specific to their role,
 * such as course enrollment and assignment submission.
 */
public class Student extends User {
    /** List of courses that the student is enrolled in */
    private final List<Course> courses = new ArrayList<>();

    /**
     * Constructs a Student object with specified attributes.
     *
     * @param userID         The unique ID of the student.
     * @param userName       The username of the student.
     * @param password       The password for the student's account.
     * @param email          The student's email address.

     */
    public Student(int userID, String userName, String password, String email,String type) {
        super(userID, userName, password, email,type);
    }

    /**
     * Checks if the student is enrolled in a course.
     *
     * @return True if the student is enrolled, false otherwise.
     */


    /**
     * Retrieves the list of courses the student is currently enrolled in.
     *
     * @return A list of Course objects the student is enrolled in.
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * Allows the student to submit an assignment.
     * This method would typically be expanded to include additional
     * assignment submission logic.
     *
     * @param assignment The assignment that the student is submitting.
     * @return The assignment object that was submitted.
     */
    public Assignment submitAssignment(Assignment assignment) {
        // Currently returns the provided assignment; in a real system, this might
        // involve marking it as submitted and associating it with the student.
        return assignment;
    }

    /**
     * Provides a string representation of the student with their details.
     *
     * @return A formatted string displaying the student's details.
     */
    @Override
    public String toString() {
        return "\n" +
                "StudentId = " + getId() + "\n" +
                "StudentName = " + getUserName() + "\n" +
                "StudentPassword = " + getPassword() + "\n" +
                "StudentEmail = " + getEmail() + "\n";
    }
}
