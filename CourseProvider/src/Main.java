import Models.*;
import Models.Module;
import Repository.FileRepository;
import SerializersAndDeserializers.AdminSerializer;
import SerializersAndDeserializers.CourseSerializer;
import SerializersAndDeserializers.InstructorSerializer;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        Student student1 = new Student(1, "Alice", "alice@example", "da@gmail", "student");
        Student student2 = new Student(2, "Bob", "bob@exampl", "bow", "student");

        // Create some sample modules
        Module module1 = new Module(1, "Mathematics", "MATH101");
        Module module2 = new Module(2, "Physics", "PHYS101");

        // Create an instructor
        Instructor instructor = new Instructor(3, "Dr. Smith", "password123", "dr.smith@example.com", "Instructor");

        // Create a course with students and modules
        Course course = new Course(2, "Intro to Science", "Basic Science Course", 30, "2024-01-10", "2024-05-15", instructor);
        course.getEnrolledStudents().add(student1);
        course.getEnrolledStudents().add(student2);
        course.getModules().add(module1);
        course.getModules().add(module2);

        Course course1= new Course(3, "Intro to Science", "Basic Science Course", 30, "2024-01-10", "2024-05-15", instructor);
        course1.addModule(module1);
        course1.getEnrolledStudents().add(student1);
        // Serialize the course to a file
        CourseSerializer courseSerializer = new CourseSerializer();
        FileRepository<Course> courseRepo = new FileRepository<>("courses.csv", courseSerializer);
        //courseRepo.create(course);
        //courseRepo.create(course1);

        System.out.println(courseRepo.getAll());



    }

}

