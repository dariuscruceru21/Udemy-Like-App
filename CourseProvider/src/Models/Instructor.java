package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Instructor in the system, extending the User class.
 * An instructor can have a list of courses they are teaching.
 */
public class Instructor extends User {
    private List<Course> courses = new ArrayList<>();

    /**
     * Constructs an Instructor with the specified ID, username, password, and email.
     *
     * @param userID   The unique ID for the instructor.
     * @param userName The username of the instructor.
     * @param password The password for the instructor's account.
     * @param email    The email address of the instructor.
     */
    public Instructor(int userID, String userName, String password, String email) {
        super(userID, userName, password, email);
    }

    /**
     * Retrieves the list of courses that the instructor is teaching.
     *
     * @return A list of Course objects associated with this instructor.
     */
    public List<Course> getCourses() {
        return this.courses;
    }

    /**
     * Provides a string representation of the instructor's key details,
     * including userID, userName, and email.
     *
     * @return A formatted string containing the instructor's ID, name, and email.
     */
    @Override
    public String toString() {
        return "\n" +
                "userID = " + getId() + "\n" +
                "userName = " + getUserName() + "\n" +
                "email = " + getEmail() + "\n";
    }
}
