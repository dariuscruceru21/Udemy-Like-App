import Models.Integer;
import Repository.*;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {

//        Student student1 = new Student(1, "Alice", "alice@example", "da@gmail", "student");
//        Student student2 = new Student(2, "Bob", "bob@exampl", "bow", "student");
//
//        // Create some sample modules
//        Module module1 = new Module(1, "Mathematics", "MATH101");
//        Module module2 = new Module(2, "Physics", "PHYS101");
//
//        // Create an instructor
//        Instructor instructor = new Instructor(3, "Dr. Smith", "password123", "dr.smith@example.com", "Instructor");
//        Instructor instructor1 = new Instructor(4,"Dr.Phill","password123","da@gmail.com","Instructor");
//
//
//
//
//
//        // Create a course with students and modules
//        Course course = new Course(2, "Intro to Science", "Basic Science Course", 30, "2024-01-10", "2024-05-15", instructor);
//        course.getEnrolledStudents().add(student1);
//        course.getEnrolledStudents().add(student2);
//        course.getModules().add(module1);
//        course.getModules().add(module2);
//
//        Course course1= new Course(3, "Intro to Science", "Basic Science Course", 30, "2024-01-10", "2023-05-15", instructor);
//        course1.addModule(module1);
//        course1.getEnrolledStudents().add(student1);
        // Serialize the course to a file
//        CourseSerializer courseSerializer = new CourseSerializer();
//        FileRepository<Course> courseRepo = new FileRepository<>("courses.csv", courseSerializer);
//        //courseRepo.create(course);
//        //courseRepo.create(course1);
//
//        instructor.setCourses(courseRepo.getAll());
//
//        InstructorSerializer instructorSerializer = new InstructorSerializer();
//        FileRepository<Instructor> instructorFileRepository = new FileRepository<>("instructors.csv",instructorSerializer);
////        instructorFileRepository.create(instructor);
//
//
//        student1.addToCourseList(course);
//        StudentSerializer studentSerializer = new StudentSerializer();
//        FileRepository<Student> studentFileRepository = new FileRepository<>("students.csv",studentSerializer);
//       // studentFileRepository.create(student1);
//
//
//        IRepository<Course> courses  = new FileRepository<>("courses.csv",courseSerializer);
//
//
//        IRepository<Student> students = new FileRepository<>("students.csv",studentSerializer);
//
//
//        IRepository<Instructor> instructors = new FileRepository<>("instructors.csv",instructorSerializer);
//
//        AdminSerializer adminSerializer = new AdminSerializer();
//        IRepository<Admin> admins = new FileRepository<>("admin.csv",adminSerializer);
//
////        instructor1.addToCourseList(course);
////        instructorFileRepository.create(instructor1);
//
//        CoursesUserService coursesUserService = new CoursesUserService(courses,students,instructors,admins);
//
//        //System.out.println(coursesUserService.getAllCoursesThatEndBeforeADate("2022-07-21"));
//
//
//        Instructor instructor3 = new Instructor(1, "Dr. Kenedy", "password123", "dr.smith@example.com", "Instructor");
//        Instructor instructor2 = new Instructor(2, "Dr. Johnson", "password456", "dr.johnson@example.com", "Instructor");
//
//
//        Course course2 = new Course(1, "Physics 101", "Basic Physics", 30, "2024-01-10", "2024-05-20", instructor3);
//        course2.addModule(module1);
//        course2.getEnrolledStudents().add(student1);
//        course2.getEnrolledStudents().add(student2);
//        course2.getEnrolledStudents().add(student2);
//
//        course2.getEnrolledStudents().add(student2);
//
//        //courseRepo.update(course2);
//
//        ControllerCoursesUser controllerCoursesUser = new ControllerCoursesUser(coursesUserService);
//        Scanner scanner = new Scanner(System.in);
//
//        //System.out.println(coursesUserService.getInstructorsByTotalEnrollment());
//        UiFileRepo Ui = new UiFileRepo(controllerCoursesUser,scanner);
//        Ui.showMenu();

//        String sql = "Select * from person where id = 2";
//
//        String url = System.getenv("DB_URL");
//        String username = System.getenv("DB_USER");
//        String password = System.getenv("DB_PASSWORD");
//
//        Connection con = DriverManager.getConnection(url,username,password);
//
//        Statement statement = con.createStatement();
//        ResultSet rs = statement.executeQuery(sql);
//        ResultSetMetaData rsmd = rs.getMetaData();
//        int columnCount = rsmd.getColumnCount();
//        while(rs.next()){
//            for(int i = 1; i <= columnCount;i++)
//                System.out.println(rsmd.getColumnName(i) + ": " + rs.getString(i) + "\t");
//            System.out.println();
//        }

//          StudentRepository studentRepository = new StudentRepository();
//          // 1. Test Create Student
//          Student newStudent = new Student(1,"Darius","password123","dariusaa@gmail.com","student");
          //System.out.println("Creating student...");
          //studentRepository.create(newStudent);  // Should insert the student into the database

//          studentRepository.delete(1);

//        // Test Get Student by ID
//        System.out.println("Fetching student with ID: " + newStudent.getId());
//        Student fetchedStudent = studentRepository.get(newStudent.getId());
//        System.out.println(fetchedStudent);  // Should print the details of the

        System.out.println("Salut");











    }
}

