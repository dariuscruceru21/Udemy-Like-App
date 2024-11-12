package Ui;

import Controller.AssignmentController;
import Controller.ControllerCoursesUser;
import Models.*;
import Models.Module;
import Repository.IRepository;
import Service.AssignmentService;
import Ui.SampleDataInitializer;
import Service.AuthenticationService;
import Service.CoursesUserService;

import java.util.Scanner;
import java.util.List;

public class Ui {
    private final IRepository<Course> courses;
    private final IRepository<Module> modules;
    private final IRepository<Assignment> assignments;
    private final IRepository<Quiz> quiz;
    private final IRepository<Student> students;
    private final IRepository<Instructor> instructors;
    private final IRepository<Admin> admins;
    private final IRepository<Forum> forums;
    private final IRepository<Message> messages;

    private final AssignmentController assignmentController;
    private final ControllerCoursesUser coursesUserController;
    private final Scanner scanner;

    // Constructor that takes repositories as parameters
    public Ui(IRepository<Course> courseRepository, IRepository<Module> moduleRepository,
              IRepository<Assignment> assignmentRepository, IRepository<Quiz> quizRepository,
              IRepository<Student> studentRepository, IRepository<Instructor> instructorRepository,
              IRepository<Admin> adminRepository, IRepository<Forum> forumRepository, IRepository<Message> messageRepository) {

        this.courses = courseRepository;
        this.modules = moduleRepository;
        this.assignments = assignmentRepository;
        this.quiz = quizRepository;
        this.students = studentRepository;
        this.instructors = instructorRepository;
        this.admins = adminRepository;
        this.forums = forumRepository;
        this.messages = messageRepository;

        //this.authService = new AuthenticationService();
        this.assignmentController = new AssignmentController(new AssignmentService(courses, modules, assignments, quiz));
        this.coursesUserController = new ControllerCoursesUser(new CoursesUserService(courses, students, instructors, admins));
        this.scanner = new Scanner(System.in);
    }


    public void runUi() {
        SampleDataInitializer sampleDataInitializer = new SampleDataInitializer();
        sampleDataInitializer.initializeSampleData(); // Initialize sample data with users

        AuthenticationService authService = new AuthenticationService(sampleDataInitializer);

        while (true) {
            System.out.println("Welcome to the System!");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User loggedInUser = authService.authenticate(username, password);

            if (loggedInUser != null) {
                System.out.println("Login successful! Welcome, " + loggedInUser.getUserName());
                showMenu(loggedInUser);  // Call your menu based on the logged-in user
            } else {
                System.out.println("Login failed! Incorrect username or password.");
            }
        }
    }


    public void showMenu(User user) {
        if (user instanceof Student) {
            showStudentMenu((Student) user);
        } else if (user instanceof Instructor) {
            showInstructorMenu((Instructor) user);
        } else if (user instanceof Admin) {
            showAdminMenu((Admin) user);
        }
    }

    public void showStudentMenu(Student student) {
        System.out.println("\nStudent Menu:");
        System.out.println("1. View enrolled courses");
        System.out.println("2. Take quizzes");
        System.out.println("3. View grades");
        System.out.println("4. Enroll in a course");
        System.out.println("5. Unenroll from a course");
        System.out.println("6. View course details");
        System.out.println("7. View assigned instructor for a course");
        System.out.println("8. Logout");

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
                scanner.nextLine();
                System.out.println(assignmentController.takeAssignmentQuiz(assignmentId));
                break;
            case 3:
                System.out.println("Your grades are not implemented in this sample.");
                break;
            case 4:
                System.out.print("Enter course ID to enroll: ");
                int courseId = scanner.nextInt();
                scanner.nextLine();
                System.out.println(coursesUserController.enrollStudentInCourse(student.getId(), courseId));
                break;
            case 5:
                System.out.print("Enter course ID to unenroll: ");
                int courseIdToUnenroll = scanner.nextInt();
                scanner.nextLine();
                System.out.println(coursesUserController.unenrollStudentFromCourse(student.getId(), courseIdToUnenroll));
                break;
            case 6:
                System.out.println("Enter course ID to view details: ");
                int courseToViewId = scanner.nextInt();
                scanner.nextLine();
                Course courseDetails = coursesUserController.getCourseInfo(courseToViewId);
                System.out.println("Course details: " + courseDetails);
                break;
            case 7:
                System.out.println("Enter course ID to view assigned instructor: ");
                int courseAssignedInstructorsId = scanner.nextInt();
                scanner.nextLine();
                Instructor instructor = coursesUserController.getAssignedInstructor(courseAssignedInstructorsId);
                System.out.println("Assigned Instructor: " + instructor);
                break;
            case 8:
                System.out.println("Logging out...");
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
        showStudentMenu(student);
    }

    public void showInstructorMenu(Instructor instructor) {
        System.out.println("\nInstructor Menu:");
        System.out.println("1. Add assignment to module");
        System.out.println("2. Add module to course");
        System.out.println("3. View courses you teach");
        System.out.println("4. View course details");
        System.out.println("5. Get all enrolled students from a course.");
        System.out.println("6. Add quiz to an assignment"); // New option to add quiz
        System.out.println("7. Remove module from a course"); // New option to remove module
        System.out.println("8. Remove assignment from a module"); // New option to remove assignment
        System.out.println("9. Remove quiz from an assignment"); // New option to remove quiz
        System.out.println("10. View all modules in a course"); // New option to view modules in course
        System.out.println("11. View all assignments in a module"); // New option to view assignments in module
        System.out.println("12. View all quizzes in an assignment"); // New option to view quizzes in assignment
        System.out.println("13. Logout");

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
                int courseToAddId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter module id: ");
                int innerModuleId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter module title: ");
                String moduleTitle = scanner.nextLine();
                System.out.println("Enter module description: ");
                String moduleDescription = scanner.nextLine();
                Module module = new Module(innerModuleId, moduleTitle, moduleDescription);
                System.out.println(assignmentController.addModuleToCourse(courseToAddId, module));
                break;
            case 3:
                List<Course> instructorCourses = coursesUserController.getCoursesByInstructor(instructor.getId());
                System.out.println("Courses you teach: " + instructorCourses);
                break;
            case 4:
                System.out.println("Enter course ID to view details: ");
                int courseToViewId = scanner.nextInt();
                scanner.nextLine();
                Course courseDetails = coursesUserController.getCourseInfo(courseToViewId);
                System.out.println("Course details: " + courseDetails);
                break;
            case 5:
                System.out.println("Enter courseId of the course whose enrolled students you want to see: ");
                int courseEnrolledStudentsId = scanner.nextInt();
                scanner.nextLine();
                List<Student> enrolledStudents = coursesUserController.getEnrolledStudents(courseEnrolledStudentsId);
            case 6: // Add quiz to assignment
                System.out.println("Enter assignment ID to add quiz: ");
                int assignmentId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter quiz Id: ");
                int quizId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Add the title of the Quiz: ");
                String titel = scanner.nextLine();
                System.out.println("Add the contents of the quiz and the options: ");
                String contents = scanner.nextLine();
                System.out.println("Enter the correct answer: ");
                int correctAnswer = scanner.nextInt();
                scanner.nextLine();
                Quiz quiz = new Quiz(quizId,titel,contents,correctAnswer); // Assuming a method to create/initialize Quiz
                String addQuizMessage = assignmentController.addQuizToAssignment(assignmentId, quiz);
                System.out.println(addQuizMessage);
                break;

            case 7: // Remove module from course
                System.out.println("Enter course ID: ");
                int courseId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter module ID to remove: ");
                int moduleId2 = scanner.nextInt();
                scanner.nextLine();
                String removeModuleMessage = assignmentController.removeModuleFromCourse(courseId, moduleId2);
                System.out.println(removeModuleMessage);
                break;

            case 8: // Remove assignment from module
                System.out.println("Enter the module ID: ");
                int moduleID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter assignment ID to remove: ");
                int assignmentID = scanner.nextInt();
                scanner.nextLine();
                String removeAssignmentMessage = assignmentController.removeAssignmentFromModule(moduleID, assignmentID);
                System.out.println(removeAssignmentMessage);
                break;

            case 9: // Remove quiz from assignment
                System.out.println("Enter assignment ID: ");
                int assignmentI = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter the quiz ID to remove");
                int quizI = scanner.nextInt();
                scanner.nextLine();
                String removeQuizMessage = assignmentController.removeQuizFromAssignment(assignmentI, quizI);
                System.out.println(removeQuizMessage);
                break;

            case 10: // View all modules in course
                System.out.println("Enter the course ID to view modules: ");
                courseId = scanner.nextInt();
                scanner.nextLine();
                List<Module> modules = assignmentController.getModulesFromCourse(courseId);
                System.out.println("Modules in course: " + modules);
                break;

            case 11: // View all assignments in module
                System.out.println("Enter module ID to view assignments: ");
                int moduleI = scanner.nextInt();
                scanner.nextLine();
                List<Assignment> assignments = assignmentController.getAssignmentsFromModule(moduleI);
                System.out.println("Assignments in module: " + assignments);
                break;

            case 12: // View all quizzes in assignment
                System.out.println("Enter the assignment ID to view the quizzes: ");
                int assignmentId2 = scanner.nextInt();
                scanner.nextLine();
                List<Quiz> quizzes = assignmentController.getQuizFromAssignment(assignmentId2);
                System.out.println("Quizzes in assignment: " + quizzes);
                break;

            case 13:
                System.out.println("Logging out...");
                break;

            default:
                System.out.println("Invalid choice. Try again.");
        }
        showInstructorMenu(instructor);
    }




    public void showAdminMenu(Admin admin) {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Manage users");
        System.out.println("2. Manage courses");
        System.out.println("3. View all courses");
        System.out.println("4. View all students");
        System.out.println("5. View all instructors.");
        System.out.println("6. View specific course details");
        System.out.println("7. View specific student details");
        System.out.println("8. View specific instructor details");
        System.out.println("9. Update student information");
        System.out.println("10. Update instructor information");
        System.out.println("11. Logout");

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
                System.out.println("All courses: \n");
                for(Course course:allCourses){
                    System.out.println(course.toString());
                }
                break;
            case 4:
                for (Student listedStudent : coursesUserController.getAllStudents()){
                    System.out.println(listedStudent.toString());
                }
                break;
            case 5:
                for (Instructor listedInstructor : coursesUserController.getAllInstructors()){
                    System.out.println(listedInstructor.toString());
                }
                break;
            case 6:
                System.out.println("Enter course ID to view details: ");
                int courseToViewId = scanner.nextInt();
                scanner.nextLine();
                Course course = coursesUserController.getCourseInfo(courseToViewId);
                System.out.println("Course details: " + course);
                break;

            case 7:
                System.out.println("Enter student ID to view details: ");
                int studentToViewId = scanner.nextInt();
                scanner.nextLine();
                Student studentToView = coursesUserController.getStudentInfo(studentToViewId);
                System.out.println("Student details: " + studentToView);
                break;

            case 8:
                System.out.println("Enter instructor ID to view details: ");
                int instructorToViewId = scanner.nextInt();
                scanner.nextLine();
                Instructor instructorToView = coursesUserController.getInstructorInfo(instructorToViewId);
                System.out.println("Instructor details: " + instructorToView);
                break;

            case 9:
                System.out.println("Enter student ID to update: ");
                int studentToUpdateId = scanner.nextInt();
                scanner.nextLine();
                Student studentToUpdate = coursesUserController.getStudentInfo(studentToUpdateId);
                System.out.println("Enter new name for student: ");
                String newStudentName = scanner.nextLine();
                studentToUpdate.setName(newStudentName);
                //all atributes that could be updated should be inserted in this functionality
                String updateStudentMsg = coursesUserController.updateStudent(studentToUpdate);
                System.out.println(updateStudentMsg);
                break;

            case 10:
                System.out.println("Enter instructor ID to update: ");
                int instructorToUpdateId = scanner.nextInt();
                scanner.nextLine();
                Instructor instructorToUpdate = coursesUserController.getInstructorInfo(instructorToUpdateId);
                System.out.println("Enter new name for instructor: ");
                String newInstructorName = scanner.nextLine();
                instructorToUpdate.setName(newInstructorName);
                //all atributes that could be updated should be inserted in this functionality
                String updateInstructorMsg = coursesUserController.updateInstructor(instructorToUpdate);
                System.out.println(updateInstructorMsg);
                break;
            case 11:
                System.out.println("Logging out...");
                return;
            default:
                System.out.println("Invalid choice. Try again.");
        }
        showAdminMenu(admin);
    }

    public void manageUsers() {
        System.out.println("Manage Users:");
        System.out.println("1. Add student");
        System.out.println("2. Add instructor");
        System.out.println("3. Remove student");
        System.out.println("4. Remove instructor");
        System.out.println("5. Unassign instructor from course.");

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
                scanner.nextLine();
                System.out.println(coursesUserController.removeStudent(studentToRemoveId));
                break;
            case 4:
                System.out.print("Enter instructor ID to remove: ");
                int instructorToRemoveId = scanner.nextInt();
                scanner.nextLine();
                System.out.println(coursesUserController.removeInstructor(instructorToRemoveId));
                break;
            case 5:
                System.out.println("Enter Id of the instructor you want to unassign: ");
                int instructorToUnassignId = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Id of the course you want to unassign from: ");
                int courseToUnassignId = scanner.nextInt();
                scanner.nextLine();
                System.out.println(coursesUserController.unassignInstructorFromCourse(courseToUnassignId, instructorToUnassignId));
            default:
                System.out.println("Invalid choice.");
        }
    }

    public void manageCourses() {
        System.out.println("Manage Courses:");
        System.out.println("1. Add new course");
        System.out.println("2. Remove course");
        System.out.println("3. Assign instructor to course");

        int choice = scanner.nextInt();
        scanner.nextLine();
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
                scanner.nextLine();
                System.out.println(coursesUserController.removeCourse(courseToRemoveId));
                break;
            case 3:
                System.out.print("Enter course ID: ");
                int assignCourseId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter instructor ID: ");
                int assignInstructorId = scanner.nextInt();
                scanner.nextLine();
                System.out.println(coursesUserController.assignInstructorToCourse(assignInstructorId, assignCourseId));
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }


}
