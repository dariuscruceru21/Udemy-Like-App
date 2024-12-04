package Service;

import Models.Admin;
import Models.Integer;
import Models.Student;
import Models.User;
import Ui.SampleDataInitializer;

import java.util.List;

/**
 * Service class responsible for authenticating users (Student, Instructor, Admin) based on their username and password.
 * The authentication is performed by checking the username and password against the repositories of each user type.
 */
public class AuthenticationService {

    // The SampleDataInitializer instance that provides access to repositories.
    private final SampleDataInitializer sampleDataInitializer;

    /**
     * Constructs an instance of the AuthenticationService with the given SampleDataInitializer.
     *
     * @param sampleDataInitializer An instance of SampleDataInitializer that holds the repositories.
     */
    public AuthenticationService(SampleDataInitializer sampleDataInitializer) {
        this.sampleDataInitializer = sampleDataInitializer;
    }

    /**
     * Authenticates a user based on their username and password. It checks the username and password against
     * the stored values in the student, instructor, and admin repositories.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @return The authenticated user (Student, Instructor, or Admin) if credentials match, or {@code null} if no match is found.
     */
    public User authenticate(String username, String password) {
        // Check students repository
        List<Student> students = sampleDataInitializer.getStudentRepository().getAll();
        for (Student student : students) {
            if (student.getUserName().equals(username) && student.getPassword().equals(password)) {
                return student; // Return student if credentials match
            }
        }

        // Check instructors repository
        List<Integer> instructors = sampleDataInitializer.getInstructorRepository().getAll();
        for (Integer instructor : instructors) {
            if (instructor.getUserName().equals(username) && instructor.getPassword().equals(password)) {
                return instructor; // Return instructor if credentials match
            }
        }

        // Check admins repository
        List<Admin> admins = sampleDataInitializer.getAdminRepository().getAll();
        for (Admin admin : admins) {
            if (admin.getUserName().equals(username) && admin.getPassword().equals(password)) {
                return admin; // Return admin if credentials match
            }
        }

        // If no user is found, return null
        return null;
    }
}
