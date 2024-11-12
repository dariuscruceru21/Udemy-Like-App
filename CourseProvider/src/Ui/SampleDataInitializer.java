package Ui;

import Models.*;
import Models.Module;
import Repository.IRepository;
import Repository.InMemoryRepository;

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

    public void initializeSampleData() {
        // Sample data for User, Student, Instructor, Admin
        Admin admin1 = new Admin(1, "admin1", "password123", "admin1@example.com");
        Instructor instructor1 = new Instructor(2, "instructor1", "password123", "instructor1@example.com");
        Student student1 = new Student(3, "student1", "password123", "student1@example.com", true);
        Student student2 = new Student(4, "student2", "password123", "student2@example.com", false);

        // Add users to the repository
        adminRepository.create(admin1);
        instructorRepository.create(instructor1);
        studentRepository.create(student1);
        studentRepository.create(student2);

        // Create courses
        Course course1 = new Course(101, "Intro to Programming", "Hard", 30, "2023-01-01", "2023-05-15", instructor1);
        Course course2 = new Course(102, "Data Structures", "Harder", 25, "2023-06-01", "2023-10-01", instructor1);

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
        Quiz quiz1 = new Quiz(401, "Intro Quiz", "What is a variable?", 1);
        Quiz quiz2 = new Quiz(402, "Data Structures Quiz", "Explain Stack and Queue", 2);

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


    // Getter methods for repositories
    public IRepository<Student> getStudentRepository() {
        return studentRepository;
    }

    public IRepository<Instructor> getInstructorRepository() {
        return instructorRepository;
    }

    public IRepository<Admin> getAdminRepository() {
        return adminRepository;
    }

    public IRepository<Course> getCourseRepository() {
        return courseRepository;
    }

    public IRepository<Module> getModuleRepository() {
        return moduleRepository;
    }

    public IRepository<Assignment> getAssignmentRepository() {
        return assignmentRepository;
    }

    public IRepository<Quiz> getQuizRepository() {
        return quizRepository;
    }

    public IRepository<Forum> getForumRepository() {
        return forumRepository;
    }

    public IRepository<Message> getMessageRepository() {
        return messageRepository;
    }
}
