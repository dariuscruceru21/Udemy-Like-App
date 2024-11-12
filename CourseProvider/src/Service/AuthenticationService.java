package Service;

import Models.Admin;
import Models.Instructor;
import Models.Student;
import Models.User;
import Repository.IRepository;
import Ui.SampleDataInitializer;

import java.util.List;

public class AuthenticationService {

    private final SampleDataInitializer sampleDataInitializer;

    public AuthenticationService(SampleDataInitializer sampleDataInitializer) {
        this.sampleDataInitializer = sampleDataInitializer;
    }

    public User authenticate(String username, String password) {
        // Check students repository
        List<Student> students = sampleDataInitializer.getStudentRepository().getAll();
        for (Student student : students) {
            if (student.getUserName().equals(username) && student.getPassword().equals(password)) {
                return student;
            }
        }

        // Check instructors repository
        List<Instructor> instructors = sampleDataInitializer.getInstructorRepository().getAll();
        for (Instructor instructor : instructors) {
            if (instructor.getUserName().equals(username) && instructor.getPassword().equals(password)) {
                return instructor;
            }
        }

        // Check admins repository
        List<Admin> admins = sampleDataInitializer.getAdminRepository().getAll();
        for (Admin admin : admins) {
            if (admin.getUserName().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }

        // If no user is found
        return null;
    }
}
