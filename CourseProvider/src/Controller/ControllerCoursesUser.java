package Controller;

import Models.Course;
import Models.Integer;
import Models.Student;
import Service.CoursesUserService;

import java.util.List;

public class ControllerCoursesUser {
    private final CoursesUserService coursesUserService;

    public ControllerCoursesUser(CoursesUserService coursesUserService) {
        this.coursesUserService = coursesUserService;
    }

    /**
     * Retrieves all students enrolled in a specified course.
     * @param courseId The ID of the course.
     * @return A list of students enrolled in the course.
     */
    public List<Student> getEnrolledStudents(java.lang.Integer courseId) {
        return coursesUserService.getEnrolledStudents(courseId);
    }

    /**
     * Enrolls a student in a course if there are available spots.
     * @param studentId The ID of the student to enroll.
     * @param courseId The ID of the course.
     * @return A success message if enrollment is successful.
     */
    public String enrollStudentInCourse(java.lang.Integer studentId, java.lang.Integer courseId) {
        try {
            coursesUserService.enroll(studentId, courseId);
            return "Student enrolled successfully.";
        } catch (Exception e) {
            return "Enrollment failed: " + e.getMessage();
        }
    }

    /**
     * Assigns an instructor to a specified course.
     * @param instructorId The ID of the instructor.
     * @param courseId The ID of the course.
     * @return A success message if the assignment is successful.
     */
    public String assignInstructorToCourse(java.lang.Integer instructorId, java.lang.Integer courseId) {
        try {
            coursesUserService.assignInstructor(instructorId, courseId);
            return "Instructor assigned successfully.";
        } catch (Exception e) {
            return "Instructor assignment failed: " + e.getMessage();
        }
    }

    /**
     * Unassigns an instructor from a specific course.
     * @param instructorId The ID of the instructor.
     * @param courseId The ID of the course.
     * @return A success message if unassignment is successful.
     */
    public String unassignInstructorFromCourse(java.lang.Integer instructorId, java.lang.Integer courseId) {
        try {
            coursesUserService.unAssignInstructor(instructorId, courseId);
            return "Instructor unassigned successfully.";
        } catch (Exception e) {
            return "Instructor unassignment failed: " + e.getMessage();
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
     * Adds a new instructor to the system.
     * @param instructor The instructor to add.
     * @return A success message after adding the instructor.
     */
    public String addInstructor(Integer instructor) {
        coursesUserService.addInstructor(instructor);
        return "Instructor added successfully.";
    }

    /**
     * Removes a course and unenrolls all students from it.
     * @param courseId The ID of the course to remove.
     * @return A success message after removing the course.
     */
    public String removeCourse(java.lang.Integer courseId) {
        coursesUserService.removeCourse(courseId);
        return "Course removed successfully.";
    }

    /**
     * Removes a student and unenrolls them from all courses.
     * @param studentId The ID of the student to remove.
     * @return A success message after removing the student.
     */
    public String removeStudent(java.lang.Integer studentId) {
        coursesUserService.removeStudent(studentId);
        return "Student removed successfully.";
    }

    /**
     * Removes an instructor and unassigns them from all courses.
     * @param instructorId The ID of the instructor to remove.
     * @return A success message after removing the instructor.
     */
    public String removeInstructor(java.lang.Integer instructorId) {
        if (coursesUserService.getCourseInfo(instructorId) != null) {
            coursesUserService.unAssignInstructor(instructorId, coursesUserService.getCourseInfo(instructorId).getId());
        }
        coursesUserService.removeInstructor(instructorId);
        return "Instructor removed successfully.";
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
     * Retrieves a list of all instructors in the system.
     * @return A list of all instructors.
     */
    public List<Integer> getAllInstructors() {
        return coursesUserService.getAllInstructors();
    }

    /**
     * Unenrolls a student from a specific course.
     * @param studentId The ID of the student to unenroll.
     * @param courseId The ID of the course.
     * @return A success message if unenrollment is successful.
     */
    public String unenrollStudentFromCourse(java.lang.Integer studentId, java.lang.Integer courseId) {
        coursesUserService.unenroll(studentId, courseId);
        return "Student unenrolled successfully.";
    }

    /**
     * Retrieves all courses a student is enrolled in.
     * @param studentId The ID of the student.
     * @return A list of courses the student is enrolled in.
     */
    public List<Course> getCoursesByStudent(java.lang.Integer studentId) {
        return coursesUserService.getCoursesByStudent(studentId);
    }

    /**
     * Retrieves all courses an instructor teaches.
     * @param instructorId The ID of the instructor.
     * @return A list of courses the instructor teaches.
     */
    public List<Course> getCoursesByInstructor(java.lang.Integer instructorId) {
        return coursesUserService.getCoursesByInstructor(instructorId);
    }

    /**
     * Retrieves detailed information about a specific course.
     * @param courseId The ID of the course.
     * @return The course object containing detailed information.
     */
    public Course getCourseInfo(java.lang.Integer courseId) {
        return coursesUserService.getCourseInfo(courseId);
    }

    /**
     * Retrieves detailed information about a specific student.
     * @param studentId The ID of the student.
     * @return The student object containing detailed information.
     */
    public Student getStudentInfo(java.lang.Integer studentId) {
        return coursesUserService.getStudentInfo(studentId);
    }

    /**
     * Retrieves detailed information about a specific instructor.
     * @param instructorId The ID of the instructor.
     * @return The instructor object containing detailed information.
     */
    public Integer getInstructorInfo(java.lang.Integer instructorId) {
        return coursesUserService.getInstructorInfo(instructorId);
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


    public String updateCourse(Course course){
        try {
            coursesUserService.updateCourse(course);
            return "Course updated successfully.";
        } catch (IllegalArgumentException e) {
            return "Update failed: " + e.getMessage();
        }
    }

    /**
     * Updates information for an existing instructor.
     * @param instructor The instructor object with updated information.
     * @return A success message if the update is successful.
     */
    public String updateInstructor(Integer instructor) {
        try {
            coursesUserService.updateInstructor(instructor);
            return "Instructor updated successfully.";
        } catch (IllegalArgumentException e) {
            return "Update failed: " + e.getMessage();
        }
    }

    /**
     * Retrieves the instructor assigned to a specific course.
     * @param courseId The ID of the course.
     * @return The instructor assigned to the course.
     */
    public Integer getAssignedInstructor(java.lang.Integer courseId) {
        return coursesUserService.getAssignedInstructor(courseId);
    }


    /**
     * Retrieves all under-occupied courses.
     * Under-occupied courses are those where the number of enrolled students is
     * less than or equal to 20% of the available spots.
     *
     * @return A list of under-occupied courses.
     */
    public List<Course> getAllUnderOccupiedCourses() {
        return coursesUserService.getAllUnderOccupiedCourses();
    }

    /**
     * Retrieves all courses sorted by the number of enrolled students in descending order.
     *
     * @return A list of courses sorted by occupation.
     */
    public List<Course> sortAllCoursesByOccupation() {
        return coursesUserService.sortAllCoursesByOccupation();
    }

    /**
     * Retrieves all instructors sorted by the number of courses they teach in descending order.
     *
     * @return A list of instructors sorted by the number of courses.
     */
    public List<Integer> sortAllInstructorsByNumberOfCourses() {
        return coursesUserService.sortAllInstructorsByNumberOfCourses();
    }

    /**
     * Retrieves all courses that end before a specified date.
     *
     * @param date The cutoff date in the format "yyyy-MM-dd".
     * @return A list of courses that end before the specified date.
     */
    public List<Course> getAllCoursesThatEndBeforeADate(String date) {
        return coursesUserService.getAllCoursesThatEndBeforeADate(date);
    }

    /**
     * Retrieves all instructors sorted by their total number of enrolled students across all courses.
     *
     * @return A list of instructors sorted by total enrollment.
     */
    public List<Integer> getInstructorsByTotalEnrollment() {
        return coursesUserService.getInstructorsByTotalEnrollment();
    }

}
