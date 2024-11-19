import Models.Admin;
import Models.User;
import Models.UserSerializer;
import Repository.FileRepository;
import Ui.SampleDataInitializer;
import Ui.Ui;

import java.util.List;


public class Main {
    public static void main(String[] args) {
//        // Initialize repositories and sample data
//        SampleDataInitializer dataInitializer = new SampleDataInitializer();
//        dataInitializer.initializeSampleData(); // Populate repositories with sample data
//
//        // Create UI instance and pass repositories or services
//        // Assuming you have a UI class that uses the repositories
//        Ui ui = new Ui(
//                dataInitializer.getCourseRepository(),
//                dataInitializer.getModuleRepository(),
//                dataInitializer.getAssignmentRepository(),
//                dataInitializer.getQuizRepository(),
//                dataInitializer.getStudentRepository(),
//                dataInitializer.getInstructorRepository(),
//                dataInitializer.getAdminRepository(),
//                dataInitializer.getForumRepository(),
//                dataInitializer.getMessageRepository()
//        );
//
//        // Start the UI
//        ui.runUi(); // Assuming your UI has a `run()` method for initialization

        UserSerializer userSerializer = new UserSerializer();
        FileRepository<User> userFileRepository = new FileRepository<>("users.csv",userSerializer);

        Admin a1 = new Admin(4,"Ma","da","email@gm.com122","admin");
        userFileRepository.delete(4);

//        List<User>list = userFileRepository.getAll();
//        System.out.println(list);

    }
}
