package Service;

import Models.*;
import Models.Module;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AssignmentService {
    private final IRepository<Course> courseIRepository;
    private final IRepository<Module> moduleIRepository;
    private final IRepository<Assignment> assignmentIRepository;
    private final IRepository<Quiz> quizIRepository;

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



    public void takeAssignmentQuiz(Integer assignmentId){
        Assignment assignment = assignmentIRepository.get(assignmentId);
        Scanner scanner = new Scanner(System.in);


        //Itterate over Quizes
        for(Quiz quiz : assignment.getQuizzes()){

            System.out.println(quiz.getContents());
            System.out.println("Your answer:");
            int answer = scanner.nextInt();

            if(answer == quiz.getAnswer()){
                System.out.println("Correct!\n");
                assignment.setScore(assignment.getScore() + 1);
                return;
            }else{
                System.out.println("Wrong answer! The answer was" + quiz.getAnswer() + "\n");
                return;
            }

        }

        System.out.println("You scored " + assignment.getScore());
        scanner.close();
    }

    public List<Module> getModulesFromCourse(Integer courseId){
        Course course = courseIRepository.get(courseId);
        return course.getModules();
    }

    public List<Assignment> getAssignmentsFromModule(Integer moduleId){
        Module module = moduleIRepository.get(moduleId);
        return module.getAssignments();
    }

    public List<Quiz> getQuizFromAssignment(Integer assignmentId){
        Assignment assignment = assignmentIRepository.get(assignmentId);
        return assignment.getQuizzes();
    }

}
