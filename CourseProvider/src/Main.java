import Controller.ControllerCoursesUser;
import Models.*;
import Models.Module;
import Repository.FileRepository;
import Repository.IRepository;
import SerializersAndDeserializers.AdminSerializer;
import SerializersAndDeserializers.CourseSerializer;
import SerializersAndDeserializers.InstructorSerializer;
import SerializersAndDeserializers.StudentSerializer;
import Service.CoursesUserService;
import Ui.UiFileRepo;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Student student1 = new Student(1, "Alice", "alice@example", "da@gmail", "student");
        Student student2 = new Student(2, "Bob", "bob@exampl", "bow", "student");

        // Create some sample modules
        Module module1 = new Module(1, "Mathematics", "MATH101");
        Module module2 = new Module(2, "Physics", "PHYS101");

        // Create an instructor
        Instructor instructor = new Instructor(3, "Dr. Smith", "password123", "dr.smith@example.com", "Instructor");
        Instructor instructor1 = new Instructor(4,"Dr.Phill","password123","da@gmail.com","Instructor");





        // Create a course with students and modules
        Course course = new Course(2, "Intro to Science", "Basic Science Course", 30, "2024-01-10", "2024-05-15", instructor);
        course.getEnrolledStudents().add(student1);
        course.getEnrolledStudents().add(student2);
        course.getModules().add(module1);
        course.getModules().add(module2);

        Course course1= new Course(3, "Intro to Science", "Basic Science Course", 30, "2024-01-10", "2023-05-15", instructor);
        course1.addModule(module1);
        course1.getEnrolledStudents().add(student1);
        // Serialize the course to a file
        CourseSerializer courseSerializer = new CourseSerializer();
        FileRepository<Course> courseRepo = new FileRepository<>("courses.csv", courseSerializer);
        //courseRepo.create(course);
        //courseRepo.create(course1);

        instructor.setCourses(courseRepo.getAll());

        InstructorSerializer instructorSerializer = new InstructorSerializer();
        FileRepository<Instructor> instructorFileRepository = new FileRepository<>("instructors.csv",instructorSerializer);
//        instructorFileRepository.create(instructor);


        student1.addToCourseList(course);
        StudentSerializer studentSerializer = new StudentSerializer();
        FileRepository<Student> studentFileRepository = new FileRepository<>("students.csv",studentSerializer);
       // studentFileRepository.create(student1);


        IRepository<Course> courses  = new FileRepository<>("courses.csv",courseSerializer);


        IRepository<Student> students = new FileRepository<>("students.csv",studentSerializer);


        IRepository<Instructor> instructors = new FileRepository<>("instructors.csv",instructorSerializer);

        AdminSerializer adminSerializer = new AdminSerializer();
        IRepository<Admin> admins = new FileRepository<>("admin.csv",adminSerializer);

//        instructor1.addToCourseList(course);
//        instructorFileRepository.create(instructor1);

        CoursesUserService coursesUserService = new CoursesUserService(courses,students,instructors,admins);

        //System.out.println(coursesUserService.getAllCoursesThatEndBeforeADate("2022-07-21"));


        Instructor instructor3 = new Instructor(1, "Dr. Kenedy", "password123", "dr.smith@example.com", "Instructor");
        Instructor instructor2 = new Instructor(2, "Dr. Johnson", "password456", "dr.johnson@example.com", "Instructor");


        Course course2 = new Course(1, "Physics 101", "Basic Physics", 30, "2024-01-10", "2024-05-20", instructor3);
        course2.addModule(module1);
        course2.getEnrolledStudents().add(student1);
        course2.getEnrolledStudents().add(student2);
        course2.getEnrolledStudents().add(student2);

        course2.getEnrolledStudents().add(student2);

        //courseRepo.update(course2);

        ControllerCoursesUser controllerCoursesUser = new ControllerCoursesUser(coursesUserService);
        Scanner scanner = new Scanner(System.in);

        //System.out.println(coursesUserService.getInstructorsByTotalEnrollment());
        UiFileRepo Ui = new UiFileRepo(controllerCoursesUser,scanner);
        Ui.showMenu();



    }

}

