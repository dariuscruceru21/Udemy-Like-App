import Models.*;
import Models.Module;
import Repository.IRepository;
import Repository.InMemoryRepository;

import java.util.ArrayList;
import java.util.List;
import Service.AssignmentService;
import Service.CoursesUserService;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        IRepository<Student> studentRepository = new InMemoryRepository<>();
        IRepository<Course> courseIRepository = new InMemoryRepository<>();
        IRepository<Module> moduleIRepository = new InMemoryRepository<>();
        IRepository<Assignment> assignmentIRepository = new InMemoryRepository<>();
        IRepository<Quiz> quizIRepository = new InMemoryRepository<>();
        IRepository<Instructor> instructorIRepository = new InMemoryRepository<>();
        IRepository<Admin> adminIRepository = new InMemoryRepository<>();

        Student s1 = new Student(1, "Robert", "abc", "@gmail", true);
        Student s2 = new Student(2, "Paul", "def", "@yahoo", false);

        studentRepository.create(s1);

        Instructor i1 = new Instructor(1,"darius","da","da@gmail.com");
        Course c1 = new Course(1,"Mate","decision",10,"21-20-1200","21-09-09",i1);

        instructorIRepository.create(i1);

        Assignment assignment = new Assignment(1,"Mate ass","21-20-2004",0);
        assignmentIRepository.create(assignment);
        Quiz quiz = new Quiz(1,"Mathe","what is 1 + 1    1.)3   2.)2", 2);
        List<Quiz> quizes = new ArrayList<>();
        quizes.add(quiz);

        assignment.setQuizzes(quizes);

        AssignmentService service = new AssignmentService(courseIRepository, moduleIRepository, assignmentIRepository,quizIRepository);

//        service.takeAssignmentQuizz(1);


        Admin a1 = new Admin(1,"darius","g1","da@gmail.com");
        adminIRepository.create(a1);

        CoursesUserService service1 = new CoursesUserService(courseIRepository,studentRepository,instructorIRepository,adminIRepository);

        Scanner scanner = new Scanner(System.in);
        String u1 = scanner.next();
        String u2 = scanner.next();

        if(service1.login(u1,u2) == true)
            System.out.println("Da ma merge");
        else
            System.out.println("Nu ba nu merge");



    }
}