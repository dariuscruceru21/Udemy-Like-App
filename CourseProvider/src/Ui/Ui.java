package Ui;

import Controller.AssignmentController;
import Controller.ControllerCoursesUser;
import Models.*;
import Models.Module;
import Repository.IRepository;
import Service.AssignmentService;
import Service.AuthenticationService;
import Service.CoursesUserService;

import java.util.Scanner;
import java.util.List;

public class Ui {
    private static IRepository<Course> courses;
    private static IRepository<Module> modules;
    private static IRepository<Assignment> assignments;
    private static IRepository<Quiz> quiz;
    private static IRepository<Student> students;
    private static IRepository<Instructor> instructors;
    private static IRepository<Admin> admins;

    private static AuthenticationService authService = new AuthenticationService();
    private static AssignmentController assignmentController = new AssignmentController(new AssignmentService(courses, modules, assignments, quiz));
    private static ControllerCoursesUser coursesUserController = new ControllerCoursesUser(new CoursesUserService(courses, students, instructors, admins));
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the System!");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User loggedInUser = authService.authenticate(username, password);

            if (loggedInUser != null) {
                System.out.println("Login successful! Welcome, " + loggedInUser.getUserName());
                showMenu(loggedInUser);
            } else {
                System.out.println("Login failed! Incorrect username or password.");
            }
        }
    }

    private static void showMenu(User user) {
        if (user instanceof Student) {
            showStudentMenu((Student) user);
        } else if (user instanceof Instructor) {
            showInstructorMenu((Instructor) user);
        } else if (user instanceof Admin) {
            showAdminMenu((Admin) user);
        }
    }

    private static void showStudentMenu(Student student) {
        System.out.println("\nStudent Menu:");
        System.out.println("1. View enrolled courses");
        System.out.println("2. Take quizzes");
        System.out.println("3. View grades");
        System.out.println("4. Enroll in a course");
        System.out.println("5. Unenroll from a course");
        System.out.println("6. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        switch (choice) {
            case 1:
                List<Course> courses = coursesUserController.getCoursesByStudent(student.getId());
                System.out.println("Enrolled courses: " + courses);
                break;
            case 2:
                System.out.print("Enter assignment ID to take quiz: ");
                int assignmentId = scanner.nextInt();
                System.out.println(assignmentController.takeAssignmentQuiz(assignmentId));
                break;
            case 3:
                // Example implementation, modify if grade retrieval is in another controller
                System.out.println("Your grades are not implemented in this sample.");
                break;
            case 4:
                System.out.print("Enter course ID to enroll: ");
                int courseId = scanner.nextInt();
                System.out.println(coursesUserController.enrollStudentInCourse(student.getId(), courseId));
                break;
            case 5:
                System.out.print("Enter course ID to unenroll: ");
                int courseIdToUnenroll = scanner.nextInt();
                System.out.println(coursesUserController.unenrollStudentFromCourse(student.getId(), courseIdToUnenroll));
                break;
            case 6:
                System.out.println("Logging out...");
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
        showStudentMenu(student);
    }

    private static void showInstructorMenu(Instructor instructor) {
        System.out.println("\nInstructor Menu:");
        System.out.println("1. Add assignment to module");
        System.out.println("2. Add module to course");
        System.out.println("3. View courses you teach");
        System.out.println("4. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        switch (choice) {
            case 1:
                System.out.print("Enter module ID to add assignment: ");
                int moduleId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter assignment description: ");
                String assignmentDescription = scanner.nextLine();
                System.out.println("Enter assignment due date: ");
                String assignmentDueDate = scanner.nextLine();
                System.out.println("Enter assignment score: ");
                int assignmentScore = scanner.nextInt();
                scanner.nextLine();
                Assignment assignment = new Assignment(1, assignmentDescription, assignmentDueDate, assignmentScore);
                System.out.println(assignmentController.addAssignmentToModule(moduleId, assignment));
                break;
            case 2:
                System.out.print("Enter course ID to add module: ");
                int courseId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter module id: ");
                int innerModuleId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter module title: ");
                String moduleTitle = scanner.nextLine();
                System.out.println("Enter module description: ");
                String moduleDescription = scanner.nextLine();
                Module module = new Module(innerModuleId, moduleTitle, moduleDescription);
                System.out.println(assignmentController.addModuleToCourse(courseId, module));
                break;
            case 3:
                List<Course> instructorCourses = coursesUserController.getCoursesByInstructor(instructor.getId());
                System.out.println("Courses you teach: " + instructorCourses);
                break;
            case 4:
                System.out.println("Logging out...");
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
        showInstructorMenu(instructor);
    }

    private static void showAdminMenu(Admin admin) {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Manage users");
        System.out.println("2. Manage courses");
        System.out.println("3. View all courses");
        System.out.println("4. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        switch (choice) {
            case 1:
                manageUsers();
                break;
            case 2:
                manageCourses();
                break;
            case 3:
                List<Course> allCourses = coursesUserController.getAllCourses();
                System.out.println("All courses: " + allCourses);
                break;
            case 4:
                System.out.println("Logging out...");
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
        showAdminMenu(admin);
    }

    private static void manageUsers() {
        System.out.println("Manage Users:");
        System.out.println("1. Add student");
        System.out.println("2. Add instructor");
        System.out.println("3. Remove student");
        System.out.println("4. Remove instructor");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        switch (choice) {
            case 1:
                System.out.print("Enter student name: ");
                String studentName = scanner.nextLine();
                System.out.println("Enter student id: ");
                int studentId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter student password: ");
                String studentPassword = scanner.nextLine();
                System.out.println("Enter student email: ");
                String studentEmail = scanner.nextLine();
                Student student = new Student(studentId, studentName, studentPassword, studentEmail, false);
                System.out.println(coursesUserController.addStudent(student));
                break;
            case 2:
                System.out.print("Enter instructor name: ");
                String instructorName = scanner.nextLine();
                System.out.println("Enter instructor id: ");
                int instructorId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter instructor password: ");
                String instructorPassword = scanner.nextLine();
                System.out.println("Enter instructor email: ");
                String instructorEmail = scanner.nextLine();
                Instructor instructor = new Instructor(instructorId, instructorName, instructorPassword, instructorEmail);
                System.out.println(coursesUserController.addInstructor(instructor));
                break;
            case 3:
                System.out.print("Enter student ID to remove: ");
                int studentToRemoveId = scanner.nextInt();
                System.out.println(coursesUserController.removeStudent(studentToRemoveId));
                break;
            case 4:
                System.out.print("Enter instructor ID to remove: ");
                int instructorToRemoveId = scanner.nextInt();
                System.out.println(coursesUserController.removeInstructor(instructorToRemoveId));
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void manageCourses() {
        System.out.println("Manage Courses:");
        System.out.println("1. Add new course");
        System.out.println("2. Remove course");
        System.out.println("3. Assign instructor to course");

        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline character
        switch (choice) {
            case 1:
                System.out.print("Enter course title: ");
                String courseTitle = scanner.nextLine();
                System.out.print("Enter course id: ");
                int courseId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter course description: ");
                String courseDescription = scanner.nextLine();
                System.out.println("Enter number of available spots: ");
                int numberOfAvailableSpots = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter start date: ");
                String startDate = scanner.nextLine();
                System.out.println("Enter end date: ");
                String endDate = scanner.nextLine();
                System.out.println("Enter the id of the instructor: ");
                int instructorId = scanner.nextInt();
                scanner.nextLine();
                Course course = new Course(courseId, courseTitle, courseDescription, numberOfAvailableSpots, startDate, endDate, coursesUserController.getInstructorInfo(instructorId));
                System.out.println(coursesUserController.addCourse(course));
                break;
            case 2:
                System.out.print("Enter course ID to remove: ");
                int courseToRemoveId = scanner.nextInt();
                System.out.println(coursesUserController.removeCourse(courseToRemoveId));
                break;
            case 3:
                System.out.print("Enter course ID: ");
                int assignCourseId = scanner.nextInt();
                System.out.print("Enter instructor ID: ");
                int assignInstructorId = scanner.nextInt();
                System.out.println(coursesUserController.assignInstructorToCourse(assignInstructorId, assignCourseId));
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
