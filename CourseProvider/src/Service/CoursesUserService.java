package Service;

import Models.*;
import Repository.IRepository;
import Ui.SampleDataInitializer;

import java.util.ArrayList;
import java.util.List;


public class CoursesUserService {
    private final IRepository<Course> courseIRepository;
    private final IRepository<Student> studentIRepository;
    private final IRepository<Instructor> instructorIRepository;
    private final IRepository<Admin> adminIRepository;

    /**
     * Constructs a new {@code CoursesUserService} with the specified repositories.
     * This service manages the interactions between courses, students, instructors, and admins.
     * The constructor allows dependency injection for the repositories that handle course, student, instructor, and admin data.
     *
     * @param courseIRepository The repository interface for course data, used to fetch and manage course-related information.
     * @param studentIRepository The repository interface for student data, used to fetch and manage student-related information.
     * @param instructorIRepository The repository interface for instructor data, used to fetch and manage instructor-related information.
     * @param adminIRepository The repository interface for admin data, used to fetch and manage admin-related information.
     */
    public CoursesUserService(IRepository<Course> courseIRepository, IRepository<Student> studentIRepository, IRepository<Instructor> instructorIRepository, IRepository<Admin> adminIRepository) {
        this.courseIRepository = courseIRepository;
        this.studentIRepository = studentIRepository;
        this.instructorIRepository = instructorIRepository;
        this.adminIRepository = adminIRepository;
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
     * Retrieves the instructor that teaches a specific course.
     *
     * @param courseId The ID of the course.
     * @return A list of students enrolled in the specified course.
     */
    public Instructor getAssignedInstructor(Integer courseId) {
        Course course = courseIRepository.get(courseId);
        return course.getInstructor();
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
     * Assigns a instructor to teach a course, only possible if the course doesn't have a teacher.
     *
     * @param instructorId The ID of the student to enroll.
     * @param courseId     The ID of the course.
     */
    public void assignInstructor(Integer instructorId, Integer courseId) {
        Instructor instructor = instructorIRepository.get(instructorId);
        Course course = courseIRepository.get(courseId);


        course.setInstructor(instructor);
        instructor.getCourses().add(course);

        instructorIRepository.update(instructor);
        courseIRepository.update(course);
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
     * Adds a new instructor to the repository.
     *
     * @param instructor The instructor to add.
     */
    public void addInstructor(Instructor instructor) {
        instructorIRepository.create(instructor);
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
     * Removes a instructor from the repository and unenrolls him from all courses
     *
     * @param instructorId The ID of the instructor to remove.
     */
    public void removeInstructor(Integer instructorId) {

        instructorIRepository.get(instructorId).getCourses().forEach(course -> {
            course.getEnrolledStudents().removeIf(student -> student.getId().equals(instructorId));
            courseIRepository.update(course);
        });
        instructorIRepository.delete(instructorId);
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
     * Retrieves a list of all instructors.
     *
     * @return A list of all instructors.
     */
    public List<Instructor> getAllInstructors() {
        return instructorIRepository.getAll();
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
     * Unassign a instructor from a specific course.
     *
     * @param instructorId The ID of the student to unenroll.
     * @param courseId     The ID of the course.
     */
    public void unAssignInstructor(Integer instructorId, Integer courseId) {
        Instructor instructor = instructorIRepository.get(instructorId);
        Course course = courseIRepository.get(courseId);

        if (instructor.getCourses().contains(course)) {
            if (course.getInstructor() == instructor) {
                //remove instructor from course
                course.setInstructor(null);

                //remove course from instructors list
                instructor.getCourses().remove(course);

                //update the repositories
                courseIRepository.update(course);
                instructorIRepository.update(instructor);
            }
        }
//        if (course.getInstructor() == instructor) {
//            //remove instructor from course
//            course.setInstructor(null);
//
//            //remove course from instructors list
//            instructor.getCourses().remove(course);
//
//            //update the repositories
//            courseIRepository.update(course);
//            instructorIRepository.update(instructor);
//        }
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
     * Retrieves all courses a instructor teaches in.
     *
     * @param instructorId The ID of the instructor.
     * @return A list of courses the instructor teaches in.
     */
    public List<Course> getCoursesByInstructor(Integer instructorId) {
        Instructor instructor = instructorIRepository.get(instructorId);

        //check if instructor exists
        if (instructor != null)
            return instructor.getCourses();
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

        if (course != null)
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
        if (student != null)
            return student;
        else
            return null;
    }

    /**
     * Retrieves detailed information about a student.
     *
     * @param instructorId The ID of the student.
     * @return The student object with detailed information.
     */
    public Instructor getInstructorInfo(Integer instructorId) {

        Instructor instructor = instructorIRepository.get(instructorId);

        //check for students existence
        if (instructor != null)
            return instructor;
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
        if (studentIRepository.get(student.getId()) != null)
            studentIRepository.update(student);
        else
            throw new IllegalArgumentException("Student with id " + student.getId() + " does not exist");
    }

    /**
     * Updates information for an already existing instructor.
     *
     * @param instructor the instance of the student.
     */
    public void updateInstructor(Instructor instructor) {

        //check if student exists
        if (instructorIRepository.get(instructor.getId()) != null)
            instructorIRepository.update(instructor);
        else
            throw new IllegalArgumentException("Student with id " + instructor.getId() + " does not exist");
    }


    public boolean login(String email, String password) {
        for (Student student : studentIRepository.getAll()) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                System.out.println("You logged in as a Student");
                return true;

            }
        }

        for (Admin admin : adminIRepository.getAll()) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                System.out.println("You logged in as an Admin");
                return true;
            }
        }

        for (Instructor instructor : instructorIRepository.getAll()) {
            if (instructor.getEmail().equals(email) && instructor.getPassword().equals(password)) {
                System.out.println("You logged in as an Instructor");
                return true;
            }
        }

        throw new IllegalArgumentException("User not found");
    }

    public List<Course> getAllUnderOccupiedCourses(){
        List<Course> courses = courseIRepository.getAll();
        List<Course> underOccupiedCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getEnrolledStudents().size() <= course.getAvailableSpots() * 0.2){
                underOccupiedCourses.add(course);
            }
        }
        return underOccupiedCourses;
    }

    public List<Course> sortAllCoursesByOccupation(){
        List<Course> courses = courseIRepository.getAll();
        // Sort the courses by the size of the enrolled students list in descending order
        courses.sort((course1, course2) -> Integer.compare(
                course2.getEnrolledStudents().size(),
                course1.getEnrolledStudents().size()
        ));
        return courses;
    }

    public List<Instructor> sortAllInstructorsByNumberOfCourses(){
        List<Instructor> instructors = instructorIRepository.getAll();
        // Sort the instructors by the size of the courses list in descending order
        instructors.sort((instructor1, instructor2) -> Integer.compare(
                instructor1.getCourses().size(),
                instructor2.getCourses().size()
        ));
        return instructors;
    }

}
