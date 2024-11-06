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

    /**
     * Retrieves a list of all students enrolled in a specific course.
     * @param courseId The ID of the course.
     * @return A list of students enrolled in the specified course.
     */
    public List<Student> getEnrolledStudents(Integer courseId) {
        Course course = courseIRepository.get(courseId);
        return course.getEnrolledStudents();
    }

    /**
     * Enrolls a student in a course if there are available spots.
     * @param studId The ID of the student to enroll.
     * @param courseId The ID of the course.
     */
    public void enroll(Integer studId, Integer courseId) {
        Student student = studentIRepository.get(studId);
        Course course = courseIRepository.get(courseId);

        if (course.getAvailableSpots() > course.getEnrolledStudents().size()) {
            course.getEnrolledStudents().add(student);
            student.getCourses().add(course);
            courseIRepository.update(course);
            studentIRepository.update(student);
        }
    }

    /**
     * Adds a new course to the repository.
     * @param course The course to add.
     */
    public void addCourse(Course course) {
        courseIRepository.create(course);
    }

    /**
     * Adds a new student to the repository.
     * @param student The student to add.
     */
    public void addStudent(Student student) {
        studentIRepository.create(student);
    }

    /**
     * Removes a course from the repository and unenrolls every student from the course
     * @param courseId The ID of the course to remove.
     */
    public void removeCourse(Integer courseId) {

        courseIRepository.get(courseId).getEnrolledStudents().forEach(student -> {
            student.getCourses().removeIf(course -> course.getId().equals(courseId));
            studentIRepository.update(student);
        });
        courseIRepository.delete(courseId);

    }

    /**
     * Removes a student from the repository.
     * @param studentId The ID of the student to remove.
     */
    public void removeStudent(Integer studentId) {

    }

    /**
     * Retrieves a list of all courses.
     * @return A list of all courses.
     */
    public List<Course> getAllCourses() {
        return null;
    }

    /**
     * Retrieves a list of all students.
     * @return A list of all students.
     */
    public List<Student> getAllStudents() {
        return null;
    }

    /**
     * Unenrolls a student from a specific course.
     * @param studentId The ID of the student to unenroll.
     * @param courseId The ID of the course.
     */
    public void unenroll(Integer studentId, Integer courseId) {

    }

    /**
     * Retrieves all courses a student is enrolled in.
     * @param studentId The ID of the student.
     * @return A list of courses the student is enrolled in.
     */
    public List<Course> getCoursesByStudent(Integer studentId) {
        return null;
    }

    /**
     * Retrieves detailed information about a course.
     * @param courseId The ID of the course.
     * @return The course object with detailed information.
     */
    public Course getCourseInfo(Integer courseId) {
        return null;
    }

    /**
     * Retrieves detailed information about a student.
     * @param studentId The ID of the student.
     * @return The student object with detailed information.
     */
    public Student getStudentInfo(Integer studentId) {
        return null;
    }

    /**
     * Updates information for an already existing student.
     * @param student the instance of the student.
     */
    public void updateStudent(Student student) {

    }
}
