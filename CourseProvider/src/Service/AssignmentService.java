package Service;

import Models.*;
import Models.Module;
import Repository.IRepository;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AssignmentService {
    private final IRepository<Course> courseIRepository;
    private final IRepository<Module> moduleIRepository;
    private final IRepository<Assignment> assignmentIRepository;
    private final IRepository<Quiz> quizIRepository;

    /**
     * Constructs an {@code AssignmentService} with the specified repositories.
     *
     * @param courseIRepository The repository for course data.
     * @param moduleIRepository The repository for module data.
     * @param assignmentIRepository The repository for assignment data.
     * @param quizIRepository The repository for quiz data.
     */
    public AssignmentService(IRepository<Course> courseIRepository, IRepository<Module> moduleIRepository, IRepository<Assignment> assignmentIRepository, IRepository<Quiz> quizIRepository) {
        this.courseIRepository = courseIRepository;
        this.moduleIRepository = moduleIRepository;
        this.assignmentIRepository = assignmentIRepository;
        this.quizIRepository = quizIRepository;
    }



    /**
     * Adds an module to a specific course.
     * @param courseId ID of the course.
     * @param module The module to add.
     */
    public void addModuleToCourse(Integer courseId, Module module){
        Course course = courseIRepository.get(courseId);
        if(course != null){

            if(moduleIRepository.get(module.getModuleID()) == null)
                moduleIRepository.create(module);
            course.getModules().add(module);
            courseIRepository.update(course);
        }else
            throw new IllegalArgumentException("Course with id " + course.getId() + " does not exist");
    }



    /**
     * Adds an assignment to a specific module.
     * @param moduleId ID of the module.
     * @param assignment The assignment to add.
     */
    public void addAssignmentToModule(Integer moduleId, Assignment assignment){
        Module module = moduleIRepository.get(moduleId);
        if(module != null){

            if(assignmentIRepository.get(assignment.getId()) == null)
                assignmentIRepository.create(assignment);
            module.getAssignments().add(assignment);
            moduleIRepository.update(module);

        }else {
            throw new IllegalArgumentException("Module with id " + moduleId + " does not exist");
        }
    }


    /**
     * Adds a quiz to a specific assignment.
     * @param assignmentId ID of the assignment.
     * @param quiz The quiz to add.
     */
    public void addQuizToAssignment(Integer assignmentId, Quiz quiz){
        Assignment assignment = assignmentIRepository.get(assignmentId);
        if(assignment != null){

            if(quizIRepository.get(quiz.getId()) == null)
                quizIRepository.create(quiz);
            assignment.getQuizzes().add(quiz);
            assignmentIRepository.update(assignment);
        }else
            throw new IllegalArgumentException("Assignment with id " + assignment.getId() + " does not exist");
    }

    /**
     * Removes a module from a specific course.
     * @param courseId ID of the course.
     * @param moduleId The id of the module to remove.
     */
    public void removeModuleFromCourse(Integer courseId, Integer moduleId){
        Course course =courseIRepository.get(courseId);
        Module moduleToRemove = moduleIRepository.get(moduleId);
        if(course != null && moduleToRemove != null){
            course.getModules().remove(moduleToRemove);
            courseIRepository.update(course);
        }else
            throw new IllegalArgumentException("Course with id " + course.getId() + " does not exist");
    }

    /**
     * Removes a assignment from a specific module.
     * @param moduleId ID of the module.
     * @param assignmentId The id of the assignment to remove.
     */
    public void removeAssignmentFromModule(Integer moduleId, Integer assignmentId){
        Module module =moduleIRepository.get(moduleId);
        Assignment assignmentToRemove = assignmentIRepository.get(assignmentId);
        if(module != null && assignmentToRemove != null){
            module.getAssignments().remove(assignmentToRemove);
            moduleIRepository.update(module);
        }else
            throw new IllegalArgumentException("Module with id " + module.getId() + " does not exist");
    }

    /**
     * Removes a quiz from a specific assignment.
     * @param assignmentId ID of the assignment.
     * @param quizId The id of the quiz to remove.
     */
    public void removeQuizFromAssignment(Integer assignmentId, Integer quizId){
        Assignment assignment = assignmentIRepository.get(assignmentId);
        Quiz quizToRemove = quizIRepository.get(quizId);
        if(assignment != null && quizToRemove != null){
            assignment.getQuizzes().remove(quizToRemove);
            assignmentIRepository.update(assignment);
        }else
            throw new IllegalArgumentException("Assignment with id " + assignment.getId() + " does not exist");
    }



    /**
     * This method allows a student to take a quiz associated with an assignment.
     * The user is prompted with each quiz question, and they are asked to provide an answer.
     * The method compares the user's input to the correct answer and updates the score accordingly.
     *
     * @param assignmentId The ID of the assignment that the student is taking the quiz for.
     *                     The assignment should contain quizzes that the student will answer.
     */
    public void takeAssignmentQuiz(Integer assignmentId){
        // Fetch the assignment object from the repository using its ID
        Assignment assignment = assignmentIRepository.get(assignmentId);

        // Create a Scanner object to capture user input from the console
        Scanner scanner = new Scanner(System.in);

        // Iterate over each quiz in the assignment
        for(Quiz quiz : assignment.getQuizzes()){

            // Display the quiz question (contents) to the user
            System.out.println(quiz.getContents());

            // Prompt the user for their answer to the quiz
            System.out.println("Your answer:");
            int answer = scanner.nextInt();  // Capture the user's answer

            // Check if the user's answer is correct
            if(answer == quiz.getAnswer()){
                // If correct, inform the user and increment the score
                System.out.println("Correct!\n");
                assignment.setScore(assignment.getScore() + 1);  // Update score
                return;  // Exit after one correct answer (can be changed based on behavior)
            }else{
                // If wrong, inform the user and reveal the correct answer
                System.out.println("Wrong answer! The answer was " + quiz.getAnswer() + "\n");
                return;  // Exit after one wrong answer (can be changed based on behavior)
            }

        }

        // Print the user's total score at the end of the quiz
        System.out.println("You scored " + assignment.getScore());

        // Close the scanner object after use (good practice)
        scanner.close();
    }


    /**
     * Retrieves all modules associated with a specific course.
     * This is useful for accessing and displaying the list of modules for a given course.
     *
     * @param courseId The ID of the course whose modules are to be fetched.
     * @return A list of modules associated with the specified course.
     */
    public List<Module> getModulesFromCourse(Integer courseId){
        // Get the course object by its ID
        Course course = courseIRepository.get(courseId);

        // Return the list of modules associated with the course
        return course.getModules();
    }


    /**
     * Retrieves all assignments associated with a specific module.
     * This method is useful for displaying assignments to users within a particular module.
     *
     * @param moduleId The ID of the module whose assignments are to be fetched.
     * @return A list of assignments associated with the specified module.
     */
    public List<Assignment> getAssignmentsFromModule(Integer moduleId){
        // Get the module object by its ID
        Module module = moduleIRepository.get(moduleId);

        // Return the list of assignments associated with the module
        return module.getAssignments();
    }


    /**
     * Retrieves all quizzes associated with a specific assignment.
     * This method provides access to all quizzes linked to a particular assignment.
     *
     * @param assignmentId The ID of the assignment whose quizzes are to be fetched.
     * @return A list of quizzes associated with the specified assignment.
     */
    public List<Quiz> getQuizFromAssignment(Integer assignmentId){
        // Get the assignment object by its ID
        Assignment assignment = assignmentIRepository.get(assignmentId);

        // Return the list of quizzes associated with the assignment
        return assignment.getQuizzes();
    }


}
