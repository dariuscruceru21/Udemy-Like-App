package Service;

import Models.Course;
import Models.Student;
import Repository.IRepository;

import java.util.List;

public class Service {
    private final IRepository<Course> courseIRepository;
    private final IRepository<Student> studentIRepository;

    public Service(IRepository<Course> courseIRepository, IRepository<Student> studentIRepository) {
        this.courseIRepository = courseIRepository;
        this.studentIRepository = studentIRepository;
    }


    public List<Student> getEnrolledStudents(Integer courseId){
        Course course = courseIRepository.get(courseId);
        return course.getEnrolledStudents();
    }


    //enroll a student in a course if there are available spots
    public void enroll(Integer studId, Integer courseId){
        Student student = studentIRepository.get(studId);
        Course course = courseIRepository.get(courseId);

        if(course.getAvailableSpots() > course.getEnrolledStudents().size()){
            course.getEnrolledStudents().add(student);
            student.getCourses().add(course);
            courseIRepository.update(course);
            studentIRepository.update(student);
        }

    }
}
