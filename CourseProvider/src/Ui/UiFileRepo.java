package Ui;

import Controller.ControllerCoursesUser;
import Models.*;
import Models.Module;
import Repository.FileRepository;
import Repository.IRepository;
import SerializersAndDeserializers.AdminSerializer;
import SerializersAndDeserializers.CourseSerializer;
import SerializersAndDeserializers.InstructorSerializer;
import SerializersAndDeserializers.StudentSerializer;

import java.util.List;
import java.util.Scanner;

/**
 * This class represents the UI layer of the application, specifically for managing
 * operations using file-based repositories.
 */
public class UiFileRepo {

    /**
     * Serializer for courses.
     */
    CourseSerializer courseSerializer = new CourseSerializer();

    /**
     * Repository for managing courses.
     */
    private IRepository<Course> courses;

    /**
     * Serializer for students.
     */
    StudentSerializer studentSerializer = new StudentSerializer();

    /**
     * Repository for managing students.
     */
    private IRepository<Student> students;

    /**
     * Serializer for instructors.
     */
    InstructorSerializer instructorSerializer = new InstructorSerializer();

    /**
     * Repository for managing instructors.
     */
    private IRepository<Instructor> instructors;

    /**
     * Serializer for admins.
     */
    AdminSerializer adminSerializer = new AdminSerializer();

    /**
     * Repository for managing admins.
     */
    private IRepository<Admin> admins;

    /**
     * Controller for managing courses and users.
     */
    private final ControllerCoursesUser coursesUserController;

    /**
     * Scanner for reading user input.
     */
    private final Scanner scanner;

    /**
     * Constructor to initialize repositories, the controller, and the scanner.
     *
     * @param coursesUserController the controller managing course and user operations.
     * @param scanner               the scanner for user input.
     */
    public UiFileRepo(ControllerCoursesUser coursesUserController, Scanner scanner) {
        courses = new FileRepository<>("courses.csv", courseSerializer);
        students = new FileRepository<>("students.csv", studentSerializer);
        instructors = new FileRepository<>("instructors.csv", instructorSerializer);
        admins = new FileRepository<>("admin.csv", adminSerializer);
        this.coursesUserController = coursesUserController;
        this.scanner = scanner;
    }

    /**
     * Displays the main menu for the application and handles user input.
     */
    public void showMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. Add Instructor");
            System.out.println("4. Remove Course");
            System.out.println("5. Remove Student");
            System.out.println("6. Remove Instructor");
            System.out.println("7. Update Course");
            System.out.println("8. Update Student");
            System.out.println("9. Update Instructor");
            System.out.println("10. View All Courses");
            System.out.println("11. View All Students");
            System.out.println("12. View All Instructors");
            System.out.println("13. View All Under-Occupied Courses");
            System.out.println("14. Sort Courses by Occupation");
            System.out.println("15. Sort Instructors by Number of Courses");
            System.out.println("16. View Courses Ending Before a Specific Date");
            System.out.println("17. View Instructors Sorted by Total Enrollment");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addCourse();
                case 2 -> addStudent();
                case 3 -> addInstructor();
                case 4 -> removeCourse();
                case 5 -> removeStudent();
                case 6 -> removeInstructor();
                case 7 -> updateCourse();
                case 8 -> updateStudent();
                case 9 -> updateInstructor();
                case 10 -> viewAllCourses();
                case 11 -> viewAllStudents();
                case 12 -> viewAllInstructors();
                case 13 -> showUnderOccupiedCourses();
                case 14 -> showSortedCoursesByOccupation();
                case 15 -> showSortedInstructorsByCourses();
                case 16 -> showCoursesEndingBeforeDate();
                case 17 -> showInstructorsByTotalEnrollment();
                case 0 -> exit = true;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Adds a new course to the system by taking user input.
     */
    public void addCourse() {
        System.out.print("Enter course ID: ");
        Integer courseID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter course title: ");
        String title = scanner.nextLine();

        System.out.print("Enter course description: ");
        String description = scanner.nextLine();

        System.out.print("Enter available spots: ");
        Integer availableSpots = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter start date (yyyy-MM-dd): ");
        String startDate = scanner.nextLine();

        System.out.print("Enter end date (yyyy-MM-dd): ");
        String endDate = scanner.nextLine();

        System.out.print("Enter instructor ID: ");
        Integer instructorID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Instructor instructor = coursesUserController.getInstructorInfo(instructorID);
        if (instructor == null) {
            System.out.println("Instructor not found. Course creation aborted.");
            return;
        }

        Course course = new Course(courseID, title, description, availableSpots, startDate, endDate, instructor);
        coursesUserController.addCourse(course);

        System.out.println("Course added successfully.");
    }

    /**
     * Adds a new student to the system by taking user input.
     */
    public void addStudent() {
        System.out.print("Enter student ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter student name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter student password: ");
        String password = scanner.nextLine();

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        System.out.print("Enter student type (e.g., 'student'): ");
        String type = scanner.nextLine();

        Student student = new Student(userID, userName, password, email, type);
        coursesUserController.addStudent(student);

        System.out.println("Student added successfully.");
    }

    /**
     * Adds a new instructor to the system by taking user input.
     */
    public void addInstructor() {
        System.out.print("Enter instructor ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter instructor name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter instructor password: ");
        String password = scanner.nextLine();

        System.out.print("Enter instructor email: ");
        String email = scanner.nextLine();

        System.out.print("Enter instructor type (e.g., 'instructor'): ");
        String type = scanner.nextLine();

        Instructor instructor = new Instructor(userID, userName, password, email, type);
        coursesUserController.addInstructor(instructor);

        System.out.println("Instructor added successfully.");
    }

    /**
     * Removes a course based on user-provided ID.
     */
    public void removeCourse() {
        System.out.print("Enter course ID to remove: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        coursesUserController.removeCourse(courseId);
        System.out.println("Course removed successfully.");
    }

    /**
     * Removes a student based on user-provided ID.
     */
    public void removeStudent() {
        System.out.print("Enter student ID to remove: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        coursesUserController.removeStudent(studentId);
        System.out.println("Student removed successfully.");
    }

    /**
     * Removes an instructor based on user-provided ID.
     */
    public void removeInstructor() {
        System.out.print("Enter instructor ID to remove: ");
        int instructorId = scanner.nextInt();
        scanner.nextLine();
        coursesUserController.removeInstructor(instructorId);
        System.out.println("Instructor removed successfully.");
    }

    /**
     * Updates the details of an existing course based on user input.
     */
    public void updateCourse() {
        System.out.print("Enter course ID to update: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();

        Course course = coursesUserController.getCourseInfo(courseId);

        if (course != null) {
            System.out.print("Enter new course title: ");
            String title = scanner.nextLine();

            System.out.print("Enter new available spots: ");
            int availableSpots = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter new start date (yyyy-MM-dd): ");
            String startDate = scanner.nextLine();

            System.out.print("Enter new end date (yyyy-MM-dd): ");
            String endDate = scanner.nextLine();

            course.setCourseTitle(title);
            course.setAvailableSpots(availableSpots);
            course.setStartDate(startDate);
            course.setEndDate(endDate);

            coursesUserController.updateCourse(course);

            System.out.println("Course updated successfully.");
        } else {
            System.out.println("Course with ID " + courseId + " not found.");
        }
    }

    /**
     * Updates the details of an existing student based on user input.
     */
    public void updateStudent() {
        System.out.print("Enter student ID to update: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student student = coursesUserController.getStudentInfo(studentId);

        if (student != null) {
            System.out.print("Enter new student name: ");
            String name = scanner.nextLine();

            student.setUserName(name);

            try {
                coursesUserController.updateStudent(student);
                System.out.println("Student updated successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error updating student: " + e.getMessage());
            }
        } else {
            System.out.println("Student with ID " + studentId + " does not exist.");
        }
    }

    /**
     * Updates the details of an existing instructor based on user input.
     */
    public void updateInstructor() {
        System.out.print("Enter instructor ID to update: ");
        int instructorId = scanner.nextInt();
        scanner.nextLine();

        Instructor instructor = coursesUserController.getInstructorInfo(instructorId);

        if (instructor != null) {
            System.out.print("Enter new instructor name: ");
            String name = scanner.nextLine();

            instructor.setUserName(name);

            try {
                coursesUserController.updateInstructor(instructor);
                System.out.println("Instructor updated successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error updating instructor: " + e.getMessage());
            }
        } else {
            System.out.println("Instructor with ID " + instructorId + " does not exist.");
        }
    }

    /**
     * Displays all courses in the system.
     */
    public void viewAllCourses() {
        List<Course> coursesList = coursesUserController.getAllCourses();
        coursesList.forEach(course -> System.out.println(course));
    }

    /**
     * Displays all students in the system.
     */
    public void viewAllStudents() {
        List<Student> studentsList = coursesUserController.getAllStudents();
        studentsList.forEach(student -> System.out.println(student));
    }

    /**
     * Displays all instructors in the system.
     */
    public void viewAllInstructors() {
        List<Instructor> instructorsList = coursesUserController.getAllInstructors();
        instructorsList.forEach(instructor -> System.out.println(instructor));
    }

    /**
     * Displays all courses that are under-occupied.
     */
    public void showUnderOccupiedCourses() {
        List<Course> underOccupiedCourses = coursesUserController.getAllUnderOccupiedCourses();
        underOccupiedCourses.forEach(course -> System.out.println(course));
        underOccupiedCourses.forEach(course -> System.out.println(course.getEnrolledStudents().size()));
        underOccupiedCourses.forEach(course -> System.out.println(course.getAvailableSpots()));
    }

    /**
     * Displays courses sorted by their occupation rate.
     */
    public void showSortedCoursesByOccupation() {
        List<Course> sortedCourses = coursesUserController.sortAllCoursesByOccupation();
        sortedCourses.forEach(course -> System.out.println(course));
        sortedCourses.forEach(course -> System.out.println(course.getEnrolledStudents().size()));
    }

    /**
     * Displays instructors sorted by the number of courses they teach.
     */
    public void showSortedInstructorsByCourses() {
        List<Instructor> sortedInstructors = coursesUserController.sortAllInstructorsByNumberOfCourses();
        sortedInstructors.forEach(instructor -> System.out.println(instructor));
        sortedInstructors.forEach(instructor -> System.out.println(instructor.getCourses().size()));
    }

    /**
     * Displays all courses that end before a specified date.
     */
    public void showCoursesEndingBeforeDate() {
        System.out.print("Enter a date (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        List<Course> coursesBeforeDate = coursesUserController.getAllCoursesThatEndBeforeADate(date);
        coursesBeforeDate.forEach(course -> System.out.println(course));
        coursesBeforeDate.forEach(course -> System.out.println(course.getEndDate()));
    }

    /**
     * Displays instructors sorted by the total enrollment of their courses.
     */
    public void showInstructorsByTotalEnrollment() {
        List<Instructor> instructorsByEnrollment = coursesUserController.getInstructorsByTotalEnrollment();
        instructorsByEnrollment.forEach(instructor -> System.out.println(instructor));
    }
}