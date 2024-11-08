package Controller;

import Models.Course;
import Models.Student;
import Service.CoursesUserService;

import java.util.List;

public class Controller {
    private final CoursesUserService coursesUserService;

    public Controller(CoursesUserService coursesUserService) {
        this.coursesUserService = coursesUserService;
    }

    /**
     * Retrieves all students enrolled in a specified course.
     * @param courseId The ID of the course.
     * @return A list of students enrolled in the course.
     */
    public List<Student> getEnrolledStudents(Integer courseId) {
        return coursesUserService.getEnrolledStudents(courseId);
    }

    /**
     * Enrolls a student in a course if there are available spots.
     * @param studentId The ID of the student to enroll.
     * @param courseId The ID of the course.
     * @return A success message if enrollment is successful.
     */
    public String enrollStudentInCourse(Integer studentId, Integer courseId) {
        try {
            coursesUserService.enroll(studentId, courseId);
            return "Student enrolled successfully.";
        } catch (Exception e) {
            return "Enrollment failed: " + e.getMessage();
        }
    }

    /**
     * Adds a new course to the system.
     * @param course The course to add.
     * @return A success message after adding the course.
     */
    public String addCourse(Course course) {
        coursesUserService.addCourse(course);
        return "Course added successfully.";
    }

    /**
     * Adds a new student to the system.
     * @param student The student to add.
     * @return A success message after adding the student.
     */
    public String addStudent(Student student) {
        coursesUserService.addStudent(student);
        return "Student added successfully.";
    }

    /**
     * Removes a course and unenrolls all students from it.
     * @param courseId The ID of the course to remove.
     * @return A success message after removing the course.
     */
    public String removeCourse(Integer courseId) {
        coursesUserService.removeCourse(courseId);
        return "Course removed successfully.";
    }

    /**
     * Removes a student and unenrolls them from all courses.
     * @param studentId The ID of the student to remove.
     * @return A success message after removing the student.
     */
    public String removeStudent(Integer studentId) {
        coursesUserService.removeStudent(studentId);
        return "Student removed successfully.";
    }

    /**
     * Retrieves a list of all courses in the system.
     * @return A list of all courses.
     */
    public List<Course> getAllCourses() {
        return coursesUserService.getAllCourses();
    }

    /**
     * Retrieves a list of all students in the system.
     * @return A list of all students.
     */
    public List<Student> getAllStudents() {
        return coursesUserService.getAllStudents();
    }

    /**
     * Unenrolls a student from a specific course.
     * @param studentId The ID of the student to unenroll.
     * @param courseId The ID of the course.
     * @return A success message if unenrollment is successful.
     */
    public String unenrollStudentFromCourse(Integer studentId, Integer courseId) {
        coursesUserService.unenroll(studentId, courseId);
        return "Student unenrolled successfully.";
    }

    /**
     * Retrieves all courses a student is enrolled in.
     * @param studentId The ID of the student.
     * @return A list of courses the student is enrolled in.
     */
    public List<Course> getCoursesByStudent(Integer studentId) {
        return coursesUserService.getCoursesByStudent(studentId);
    }

    /**
     * Retrieves detailed information about a specific course.
     * @param courseId The ID of the course.
     * @return The course object containing detailed information.
     */
    public Course getCourseInfo(Integer courseId) {
        return coursesUserService.getCourseInfo(courseId);
    }

    /**
     * Retrieves detailed information about a specific student.
     * @param studentId The ID of the student.
     * @return The student object containing detailed information.
     */
    public Student getStudentInfo(Integer studentId) {
        return coursesUserService.getStudentInfo(studentId);
    }

    /**
     * Updates information for an existing student.
     * @param student The student object with updated information.
     * @return A success message if the update is successful.
     */
    public String updateStudent(Student student) {
        try {
            coursesUserService.updateStudent(student);
            return "Student updated successfully.";
        } catch (IllegalArgumentException e) {
            return "Update failed: " + e.getMessage();
        }
    }
}

