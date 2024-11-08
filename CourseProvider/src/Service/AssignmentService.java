package Service;

import Models.*;
import Models.Module;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;

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
            module.getAssignments().add(assignment);
            moduleIRepository.update(module);

        }else {
            throw new IllegalArgumentException("Module with id " + module.getModuleID() + " does not exist");
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


}
