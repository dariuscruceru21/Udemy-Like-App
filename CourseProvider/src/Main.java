import Models.Student;
import Models.User;
import Repository.IRepository;
import Repository.InMemoryRepository;


public class Main {
    public static void main(String[] args) {
        IRepository<User> studentRepository = new InMemoryRepository<>();

        Student s1 = new Student(1, "Robert", "abc", "@gmail", true);
        Student s2 = new Student(2, "Paul", "def", "@yahoo", false);


        // Create
        studentRepository.create(s1);
        studentRepository.create(s2);

        // Read
        System.out.println(studentRepository.getAll());
//        studentRepository.read(2).ifPresent(System.out::println);


        // Update
        s1 = new Student(1, "Alice Smith", "ghi", "@yahoo", false);
        studentRepository.update(s1);

        // Delete
        studentRepository.delete(2);

        // List all students
//        studentRepository.findAll().forEach(System.out::println);
    }
}