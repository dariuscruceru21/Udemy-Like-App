package Ui;

import Models.*;
import Models.Module;
import Repository.IRepository;
import Repository.InMemoryRepository;

/**
 * This class is responsible for initializing sample data for the application.
 * It creates and populates repositories for various entities such as students, instructors, courses, assignments, quizzes, and more.
 * The initialized data is used for testing and demonstration purposes within the application.
 */
public class SampleDataInitializer {

    // Initialize repositories for different model types
    private final IRepository<Student> studentRepository = new InMemoryRepository<>();
    private final IRepository<Instructor> instructorRepository = new InMemoryRepository<>();
    private final IRepository<Admin> adminRepository = new InMemoryRepository<>();
    private final IRepository<Course> courseRepository = new InMemoryRepository<>();
    private final IRepository<Module> moduleRepository = new InMemoryRepository<>();
    private final IRepository<Assignment> assignmentRepository = new InMemoryRepository<>();
    private final IRepository<Quiz> quizRepository = new InMemoryRepository<>();
    private final IRepository<Forum> forumRepository = new InMemoryRepository<>();
    private final IRepository<Message> messageRepository = new InMemoryRepository<>();

    /**
     * Initializes sample data for various entities including users, courses, modules, assignments, quizzes, forums, and messages.
     * This method creates instances of all entities and adds them to the corresponding repositories.
     * The data initialized includes students, instructors, admins, courses, assignments, quizzes, forums, and messages.
     */
    public void initializeSampleData() {
        // Sample data for User, Student, Instructor, Admin
        Admin admin1 = new Admin(1, "admin1", "password123", "admin1@example.com");
        Instructor instructor1 = new Instructor(2, "instructor1", "password123", "instructor1@example.com");
        Instructor instructor2 = new Instructor(3, "instructor2", "password123", "instructor2@example.com");
        Student student1 = new Student(4, "student1", "password123", "student1@example.com", true);
        Student student2 = new Student(5, "student2", "password123", "student2@example.com", false);

        // Add users to the repository
        adminRepository.create(admin1);
        instructorRepository.create(instructor1);
        instructorRepository.create(instructor2);
        studentRepository.create(student1);
        studentRepository.create(student2);

        // Create courses
        Course course1 = new Course(101, "Intro to Programming", "Hard", 30, "2023-01-01", "2023-05-15", instructor1);
        instructor1.getCourses().add(course1);
        instructorRepository.update(instructor1);
        Course course2 = new Course(102, "Data Structures", "Harder", 25, "2023-06-01", "2023-10-01", instructor1);
        instructor1.getCourses().add(course1);
        instructorRepository.update(instructor1);

        // Add courses to repository
        courseRepository.create(course1);
        courseRepository.create(course2);

        // Create modules
        Module module1 = new Module(201, "Programming Basics", "Introduction to Programming Concepts");
        Module module2 = new Module(202, "Advanced Programming", "Data Structures and Algorithms");

        // Add modules to repository
        moduleRepository.create(module1);
        moduleRepository.create(module2);

        // Add modules to courses
        course1.getModules().add(module1);
        course1.getModules().add(module2);

        // Create assignments
        Assignment assignment1 = new Assignment(301, "Basic Syntax Assignment", "2023-03-01", 100);
        Assignment assignment2 = new Assignment(302, "Data Structures Assignment", "2023-07-01", 100);

        // Add assignments to repository
        assignmentRepository.create(assignment1);
        assignmentRepository.create(assignment2);

        // Add assignments to modules
        module1.getAssignments().add(assignment1);
        module2.getAssignments().add(assignment2);

        // Create quizzes
        Quiz quiz1 = new Quiz(401, "Intro Quiz", "What is 1 + 1  Options: 1.) 2  2.) 1?", 1);
        Quiz quiz2 = new Quiz(402, "Data Structures Quiz", "What is 2 + 2 Options: 1.)3  2.)4", 2);

        // Add quizzes to repository
        quizRepository.create(quiz1);
        quizRepository.create(quiz2);

        // Add quizzes to assignments
        assignment1.getQuizzes().add(quiz1);
        assignment2.getQuizzes().add(quiz2);

        // Create forums
        Forum forum1 = new Forum(501, "General Discussion", new String[]{"Programming", "General"});
        Forum forum2 = new Forum(502, "Advanced Topics", new String[]{"Data Structures", "Algorithms"});

        // Add forums to repository
        forumRepository.create(forum1);
        forumRepository.create(forum2);

        // Create messages
        Message message1 = new Message(601, "Welcome to the course!", instructor1, student1);
        Message message2 = new Message(602, "Hello, looking forward to learning!", student1, instructor1);

        // Add messages to repository
        messageRepository.create(message1);
        messageRepository.create(message2);
    }

    /**
     * Retrieves the repository for student data.
     *
     * @return The student repository.
     */
    public IRepository<Student> getStudentRepository() {
        return studentRepository;
    }

    /**
     * Retrieves the repository for instructor data.
     *
     * @return The instructor repository.
     */
    public IRepository<Instructor> getInstructorRepository() {
        return instructorRepository;
    }

    /**
     * Retrieves the repository for admin data.
     *
     * @return The admin repository.
     */
    public IRepository<Admin> getAdminRepository() {
        return adminRepository;
    }

    /**
     * Retrieves the repository for course data.
     *
     * @return The course repository.
     */
    public IRepository<Course> getCourseRepository() {
        return courseRepository;
    }

    /**
     * Retrieves the repository for module data.
     *
     * @return The module repository.
     */
    public IRepository<Module> getModuleRepository() {
        return moduleRepository;
    }

    /**
     * Retrieves the repository for assignment data.
     *
     * @return The assignment repository.
     */
    public IRepository<Assignment> getAssignmentRepository() {
        return assignmentRepository;
    }

    /**
     * Retrieves the repository for quiz data.
     *
     * @return The quiz repository.
     */
    public IRepository<Quiz> getQuizRepository() {
        return quizRepository;
    }

    /**
     * Retrieves the repository for forum data.
     *
     * @return The forum repository.
     */
    public IRepository<Forum> getForumRepository() {
        return forumRepository;
    }

    /**
     * Retrieves the repository for message data.
     *
     * @return The message repository.
     */
    public IRepository<Message> getMessageRepository() {
        return messageRepository;
    }
}

