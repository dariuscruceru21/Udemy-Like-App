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

public class UiFileRepo {
    CourseSerializer courseSerializer = new CourseSerializer();
    private IRepository<Course> courses;

    StudentSerializer studentSerializer = new StudentSerializer();
    private IRepository<Student> students;

    InstructorSerializer instructorSerializer = new InstructorSerializer();
    private IRepository<Instructor> instructors;

    AdminSerializer adminSerializer = new AdminSerializer();
    private IRepository<Admin> admins;

    private final ControllerCoursesUser coursesUserController;
    private final Scanner scanner;

    public UiFileRepo(ControllerCoursesUser coursesUserController, Scanner scanner) {
        courses = new FileRepository<>("courses.csv", courseSerializer);
        students = new FileRepository<>("students.csv", studentSerializer);
        instructors = new FileRepository<>("instructors.csv", instructorSerializer);
        admins = new FileRepository<>("admin.csv", adminSerializer);
        this.coursesUserController = coursesUserController;
        this.scanner = scanner;
    }

    // Show the main menu
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
              // Consume the newline character after integer input

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

    // Methods for adding entities
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

        // Retrieve instructor based on ID
        Instructor instructor = coursesUserController.getInstructorInfo(instructorID);
        if (instructor == null) {
            System.out.println("Instructor not found. Course creation aborted.");
            return;
        }

        // Create the course object
        Course course = new Course(courseID, title, description, availableSpots, startDate, endDate, instructor);

        // Add course using the controller
        coursesUserController.addCourse(course);

        System.out.println("Course added successfully.");
    }


    public void addStudent() {
        // Collecting information from the user
        System.out.print("Enter student ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter student name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter student password: ");
        String password = scanner.nextLine();

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        System.out.print("Enter student type (e.g., 'student'): ");
        String type = scanner.nextLine();

        // Create a new student object
        Student student = new Student(userID, userName, password, email, type);

        // Add the student to the system (assuming `coursesUserController.addStudent()` method exists)
        coursesUserController.addStudent(student);

        // Notify the user
        System.out.println("Student added successfully.");
    }


    public void addInstructor() {
        // Collecting information from the user
        System.out.print("Enter instructor ID: ");
        int userID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter instructor name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter instructor password: ");
        String password = scanner.nextLine();

        System.out.print("Enter instructor email: ");
        String email = scanner.nextLine();

        System.out.print("Enter instructor type (e.g., 'instructor'): ");
        String type = scanner.nextLine();

        // Create a new instructor object
        Instructor instructor = new Instructor(userID, userName, password, email, type);

        // Add the instructor to the system (assuming `coursesUserController.addInstructor()` exists)
        coursesUserController.addInstructor(instructor);

        // Notify the user
        System.out.println("Instructor added successfully.");
    }


    // Methods for removing entities
    public void removeCourse() {
        System.out.print("Enter course ID to remove: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        coursesUserController.removeCourse(courseId);
        System.out.println("Course removed successfully.");
    }

    public void removeStudent() {
        System.out.print("Enter student ID to remove: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        coursesUserController.removeStudent(studentId);
        System.out.println("Student removed successfully.");
    }

    public void removeInstructor() {
        System.out.print("Enter instructor ID to remove: ");
        int instructorId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        coursesUserController.removeInstructor(instructorId);
        System.out.println("Instructor removed successfully.");
    }

    public void updateCourse() {
        // Collect the course ID that needs to be updated
        System.out.print("Enter course ID to update: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        // Assuming coursesUserController has a method to find a course by ID
        Course course = coursesUserController.getCourseInfo(courseId);

        if (course != null) {
            // If course exists, update its attributes
            System.out.print("Enter new course title: ");
            String title = scanner.nextLine();

            System.out.print("Enter new available spots: ");
            int availableSpots = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.print("Enter new start date (yyyy-MM-dd): ");
            String startDate = scanner.nextLine();

            System.out.print("Enter new end date (yyyy-MM-dd): ");
            String endDate = scanner.nextLine();

            // Update the course object with new details
            course.setCourseTitle(title);
            course.setAvailableSpots(availableSpots);
            course.setStartDate(startDate);
            course.setEndDate(endDate);

            // Optionally, if you want to allow updating other fields, such as description or instructor,
            // you could add more input prompts here.

            // Call the controller to update the course
            coursesUserController.updateCourse(course);

            System.out.println("Course updated successfully.");
        } else {
            // If course was not found
            System.out.println("Course with ID " + courseId + " not found.");
        }
    }


    public void updateStudent() {
        // Step 1: Prompt the user to enter the student ID to update
        System.out.print("Enter student ID to update: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Step 2: Retrieve the student by ID from the controller (service layer)
        Student student = coursesUserController.getStudentInfo(studentId); // Assuming this method exists in the controller

        // Step 3: Check if the student exists
        if (student != null) {
            // Step 4: Prompt the user for new student details
            System.out.print("Enter new student name: ");
            String name = scanner.nextLine();

            // Step 5: Update the student's name
            student.setUserName(name); // Assuming you have the setter for the student name

            // Step 6: Call the update method in the controller or service layer to update the student
            try {
                coursesUserController.updateStudent(student);  // Assuming this method exists to update the student in the system
                System.out.println("Student updated successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error updating student: " + e.getMessage());
            }
        } else {
            // If the student doesn't exist, notify the user
            System.out.println("Student with ID " + studentId + " does not exist.");
        }
    }

    public void updateInstructor() {
        // Step 1: Prompt the user to enter the instructor ID to update
        System.out.print("Enter instructor ID to update: ");
        int instructorId = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        // Step 2: Retrieve the instructor by ID from the controller (service layer)
        Instructor instructor = coursesUserController.getInstructorInfo(instructorId); // Assuming this method exists in the controller

        // Step 3: Check if the instructor exists
        if (instructor != null) {
            // Step 4: Prompt the user for new instructor details
            System.out.print("Enter new instructor name: ");
            String name = scanner.nextLine();

            // Step 5: Update the instructor's name
            instructor.setUserName(name); // Assuming you have the setter for the instructor name

            // Step 6: Call the update method in the controller or service layer to update the instructor
            try {
                coursesUserController.updateInstructor(instructor);  // Assuming this method exists to update the instructor in the system
                System.out.println("Instructor updated successfully.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error updating instructor: " + e.getMessage());
            }
        } else {
            // If the instructor doesn't exist, notify the user
            System.out.println("Instructor with ID " + instructorId + " does not exist.");
        }
    }


    // Methods for viewing entities
    public void viewAllCourses() {
        List<Course> coursesList = coursesUserController.getAllCourses();
        coursesList.forEach(course -> System.out.println(course));
    }

    public void viewAllStudents() {
        List<Student> studentsList = coursesUserController.getAllStudents();
        studentsList.forEach(student -> System.out.println(student));
    }

    public void viewAllInstructors() {
        List<Instructor> instructorsList = coursesUserController.getAllInstructors();
        instructorsList.forEach(instructor -> System.out.println(instructor));
    }

    // Specialized functions
    public void showUnderOccupiedCourses() {
        List<Course> underOccupiedCourses = coursesUserController.getAllUnderOccupiedCourses();
        underOccupiedCourses.forEach(course -> System.out.println(course));
    }

    public void showSortedCoursesByOccupation() {
        List<Course> sortedCourses = coursesUserController.sortAllCoursesByOccupation();
        sortedCourses.forEach(course -> System.out.println(course));
    }

    public void showSortedInstructorsByCourses() {
        List<Instructor> sortedInstructors = coursesUserController.sortAllInstructorsByNumberOfCourses();
        sortedInstructors.forEach(instructor -> System.out.println(instructor));
    }

    public void showCoursesEndingBeforeDate() {
        System.out.print("Enter a date (yyyy-MM-dd): ");
        String date = scanner.nextLine();
        List<Course> coursesBeforeDate = coursesUserController.getAllCoursesThatEndBeforeADate(date);
        coursesBeforeDate.forEach(course -> System.out.println(course));
    }

    public void showInstructorsByTotalEnrollment() {
        List<Instructor> instructorsByEnrollment = coursesUserController.getInstructorsByTotalEnrollment();
        instructorsByEnrollment.forEach(instructor -> System.out.println(instructor));
    }
}