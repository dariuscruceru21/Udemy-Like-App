package Service;

import Models.Course;
import Models.Student;
import Repository.IRepository;

import java.util.ArrayList;
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
     *
     * @param courseId The ID of the course.
     * @return A list of students enrolled in the specified course.
     */
    public List<Student> getEnrolledStudents(Integer courseId) {
        Course course = courseIRepository.get(courseId);
        return course.getEnrolledStudents();
    }

    /**
     * Enrolls a student in a course if there are available spots.
     *
     * @param studId   The ID of the student to enroll.
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
     *
     * @param course The course to add.
     */
    public void addCourse(Course course) {
        courseIRepository.create(course);
    }

    /**
     * Adds a new student to the repository.
     *
     * @param student The student to add.
     */
    public void addStudent(Student student) {
        studentIRepository.create(student);
    }

    /**
     * Removes a course from the repository and unenrolls every student from the course
     *
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
     * Removes a student from the repository and unenrolls him from all courses
     *
     * @param studentId The ID of the student to remove.
     */
    public void removeStudent(Integer studentId) {

        studentIRepository.get(studentId).getCourses().forEach(course -> {
            course.getEnrolledStudents().removeIf(student -> student.getId().equals(studentId));
            courseIRepository.update(course);
        });
        studentIRepository.delete(studentId);
    }

    /**
     * Retrieves a list of all courses.
     *
     * @return A list of all courses.
     */
    public List<Course> getAllCourses() {
        return courseIRepository.getAll();
    }

    /**
     * Retrieves a list of all students.
     *
     * @return A list of all students.
     */
    public List<Student> getAllStudents() {
        return studentIRepository.getAll();
    }

    /**
     * Unenrolls a student from a specific course.
     *
     * @param studentId The ID of the student to unenroll.
     * @param courseId  The ID of the course.
     */
    public void unenroll(Integer studentId, Integer courseId) {
        Student student = studentIRepository.get(studentId);
        Course course = courseIRepository.get(courseId);

        //check if the student is enrolled in the course
        if (course.getEnrolledStudents().contains(student)) {
            //remove student from course
            course.getEnrolledStudents().remove(student);

            //remove course from students list
            student.getCourses().remove(course);

            //update the repositories
            courseIRepository.update(course);
            studentIRepository.update(student);
        }
    }

    /**
     * Retrieves all courses a student is enrolled in.
     *
     * @param studentId The ID of the student.
     * @return A list of courses the student is enrolled in.
     */
    public List<Course> getCoursesByStudent(Integer studentId) {
        Student student = studentIRepository.get(studentId);

        //check if student exists
        if (student != null)
            return student.getCourses();
        else
            return new ArrayList<>();
    }

    /**
     * Retrieves detailed information about a course.
     *
     * @param courseId The ID of the course.
     * @return The course object with detailed information.
     */
    public Course getCourseInfo(Integer courseId) {
        Course course = courseIRepository.get(courseId);

        if(course != null)
            return course;
        else
            return null;
    }

    /**
     * Retrieves detailed information about a student.
     *
     * @param studentId The ID of the student.
     * @return The student object with detailed information.
     */
    public Student getStudentInfo(Integer studentId) {

        Student student = studentIRepository.get(studentId);

        //check for students existence
        if(student != null)
            return student;
        else
            return null;
    }

    /**
     * Updates information for an already existing student.
     *
     * @param student the instance of the student.
     */
    public void updateStudent(Student student) {

        //check if student exists
        if(studentIRepository.get(student.getId()) != null)
            studentIRepository.update(student);
        else
            throw new IllegalArgumentException("Student with id " + student.getId() + " does not exist");
    }
}
