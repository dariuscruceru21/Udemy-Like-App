import Models.*;
import Models.Module;
import Repository.FileRepository;
import Ui.SampleDataInitializer;
import Ui.Ui;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
//        // Initialize repositories and sample data
//        SampleDataInitializer dataInitializer = new SampleDataInitializer();
//        dataInitializer.initializeSampleData(); // Populate repositories with sample data
//
//        // Create UI instance and pass repositories or services
//        // Assuming you have a UI class that uses the repositories
//        Ui ui = new Ui(
//                dataInitializer.getCourseRepository(),
//                dataInitializer.getModuleRepository(),
//                dataInitializer.getAssignmentRepository(),
//                dataInitializer.getQuizRepository(),
//                dataInitializer.getStudentRepository(),
//                dataInitializer.getInstructorRepository(),
//                dataInitializer.getAdminRepository(),
//                dataInitializer.getForumRepository(),
//                dataInitializer.getMessageRepository()
//        );
//
//        // Start the UI
//        ui.runUi(); // Assuming your UI has a `run()` method for initialization

        // Create some sample students
        Student student1 = new Student(1, "Alice", "alice@example","da@gmail","student");
        Student student2 = new Student(2, "Bob", "bob@exampl","bow","student");

        // Create some sample modules
        Module module1 = new Module(1, "Mathematics", "MATH101");
        Module module2 = new Module(2, "Physics", "PHYS101");

        // Create an instructor
        Instructor instructor = new Instructor(1, "Dr. Smith", "password123", "dr.smith@example.com", "Instructor");

        // Create a course with students and modules
        Course course = new Course(1, "Intro to Science", "Basic Science Course", 30, "2024-01-10", "2024-05-15", instructor);
        course.getEnrolledStudents().add(student1);
        course.getEnrolledStudents().add(student2);
        course.getModules().add(module1);
        course.getModules().add(module2);

        // Serialize the course to a file
        CourseSerializer courseSerializer = new CourseSerializer();
        FileRepository<Course> courseRepo = new FileRepository<>("courses.txt", courseSerializer);
        courseRepo.create(course);

        // Retrieve the course from the file
        Course retrievedCourse = courseRepo.get(1);
        System.out.println("Retrieved Course: " + retrievedCourse.getCourseTitle());
        System.out.println("Instructor: " + retrievedCourse.getInstructor().getUserName());
        System.out.println(retrievedCourse.getEnrolledStudents());
        System.out.println(retrievedCourse.getModules());
    }

}

