import Ui.SampleDataInitializer;
import Ui.Ui;


public class Main {
    public static void main(String[] args) {
        // Initialize repositories and sample data
        SampleDataInitializer dataInitializer = new SampleDataInitializer();
        dataInitializer.initializeSampleData(); // Populate repositories with sample data

        // Create UI instance and pass repositories or services
        // Assuming you have a UI class that uses the repositories
        Ui ui = new Ui(
                dataInitializer.getCourseRepository(),
                dataInitializer.getModuleRepository(),
                dataInitializer.getAssignmentRepository(),
                dataInitializer.getQuizRepository(),
                dataInitializer.getStudentRepository(),
                dataInitializer.getInstructorRepository(),
                dataInitializer.getAdminRepository(),
                dataInitializer.getForumRepository(),
                dataInitializer.getMessageRepository()
        );

        // Start the UI
        ui.runUi(); // Assuming your UI has a `run()` method for initialization
    }
}
