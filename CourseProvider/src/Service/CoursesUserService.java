package Service;

import Models.*;
import Models.Integer;
import Repository.IRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class CoursesUserService {
    private final IRepository<Course> courseIRepository;
    private final IRepository<Student> studentIRepository;
    private final IRepository<Integer> instructorIRepository;
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
    public CoursesUserService(IRepository<Course> courseIRepository, IRepository<Student> studentIRepository, IRepository<Integer> instructorIRepository, IRepository<Admin> adminIRepository) {
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
    public List<Student> getEnrolledStudents(java.lang.Integer courseId) {
        Course course = courseIRepository.get(courseId);
        return course.getEnrolledStudents();
    }

    /**
     * Retrieves the instructor that teaches a specific course.
     *
     * @param courseId The ID of the course.
     * @return A list of students enrolled in the specified course.
     */
    public Integer getAssignedInstructor(java.lang.Integer courseId) {
        Course course = courseIRepository.get(courseId);
        return course.getInstructor();
    }

    /**
     * Enrolls a student in a course if there are available spots.
     *
     * @param studId   The ID of the student to enroll.
     * @param courseId The ID of the course.
     */
    public void enroll(java.lang.Integer studId, java.lang.Integer courseId) {
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
    public void assignInstructor(java.lang.Integer instructorId, java.lang.Integer courseId) {
        Integer instructor = instructorIRepository.get(instructorId);
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
    public void addInstructor(Integer instructor) {
        instructorIRepository.create(instructor);
    }

    /**
     * Removes a course from the repository and unenrolls every student from the course
     *
     * @param courseId The ID of the course to remove.
     */
    public void removeCourse(java.lang.Integer courseId) {

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
    public void removeStudent(java.lang.Integer studentId) {

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
    public void removeInstructor(java.lang.Integer instructorId) {

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
    public List<Integer> getAllInstructors() {
        return instructorIRepository.getAll();
    }

    /**
     * Unenrolls a student from a specific course.
     *
     * @param studentId The ID of the student to unenroll.
     * @param courseId  The ID of the course.
     */
    public void unenroll(java.lang.Integer studentId, java.lang.Integer courseId) {
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
    public void unAssignInstructor(java.lang.Integer instructorId, java.lang.Integer courseId) {
        Integer instructor = instructorIRepository.get(instructorId);
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
    public List<Course> getCoursesByStudent(java.lang.Integer studentId) {
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
    public List<Course> getCoursesByInstructor(java.lang.Integer instructorId) {
        Integer instructor = instructorIRepository.get(instructorId);

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
    public Course getCourseInfo(java.lang.Integer courseId) {
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
    public Student getStudentInfo(java.lang.Integer studentId) {

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
    public Integer getInstructorInfo(java.lang.Integer instructorId) {

        Integer instructor = instructorIRepository.get(instructorId);

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

    public void updateCourse(Course course){
        if(courseIRepository.get(course.getId()) != null)
            courseIRepository.update(course);
        else
            throw new IllegalArgumentException("Course with id " + course.getId() + " does not exist");
    }


    /**
     * Updates information for an already existing instructor.
     *
     * @param instructor the instance of the student.
     */
    public void updateInstructor(Integer instructor) {

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

        for (Integer instructor : instructorIRepository.getAll()) {
            if (instructor.getEmail().equals(email) && instructor.getPassword().equals(password)) {
                System.out.println("You logged in as an Instructor");
                return true;
            }
        }

        throw new IllegalArgumentException("User not found");
    }

    /**
     * Retrieves a list of courses where the number of enrolled students is less than or equal to
     * 20% of the available spots.
     *
     * @return A list of under-occupied courses.
     */
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

    /**
     * Sorts all courses in descending order based on the number of enrolled students.
     *
     * @return A list of courses sorted by student enrollment in descending order.
     */
    public List<Course> sortAllCoursesByOccupation(){
        List<Course> courses = courseIRepository.getAll();
        // Sort the courses by the size of the enrolled students list in descending order
        courses.sort((course1, course2) -> java.lang.Integer.compare(
                course2.getEnrolledStudents().size(),
                course1.getEnrolledStudents().size()
        ));
        return courses;
    }

    /**
     * Sorts all instructors in descending order based on the number of courses they teach.
     *
     * @return A list of instructors sorted by the number of courses they are assigned to in descending order.
     */
    public List<Integer> sortAllInstructorsByNumberOfCourses(){
        List<Integer> instructors = instructorIRepository.getAll();
        // Sort the instructors by the size of the courses list in descending order
        instructors.sort((instructor1, instructor2) -> java.lang.Integer.compare(
                instructor2.getCourses().size(),
                instructor1.getCourses().size()
        ));
        return instructors;
    }

    /**
     * Retrieves a list of courses that end before a specified date.
     *
     * @param date A string representation of the date in the format "yyyy-MM-dd".
     * @return A list of courses that end before the specified date.
     */
    public List<Course> getAllCoursesThatEndBeforeADate(String date) {
        List<Course> courses = courseIRepository.getAll();
        List<Course> courseList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //date to parse
            Date inputDate = dateFormat.parse(date);

            for (Course course : courses) {
                try {

                    Date courseEndDate = dateFormat.parse(course.getEndDate());

                    if (courseEndDate.before(inputDate))
                        courseList.add(course);
                } catch (ParseException e) {
                    System.err.println("Invalid end date format for course: " + course.getCourseTitle());
                }
            }

        } catch (ParseException e) {
            System.err.println("Invalid input date format. Please use the format yyyy-MM-dd.");

        }
        return courseList;
    }


    /**
     * Retrieves a list of instructors sorted by the total number of students enrolled in the courses they teach.
     *
     * @return A list of instructors sorted by total enrollment in descending order.
     */
    public List<Integer> getInstructorsByTotalEnrollment(){
        List<Course> courses = courseIRepository.getAll();

        Map<Integer, java.lang.Integer> instructorEnrollMap = new HashMap<>();

        for(Course course : courses){
            Integer instructor = course.getInstructor();
            if(instructor != null){
                int enrolledSum = course.getEnrolledStudents().size();
                instructorEnrollMap.put(instructor,instructorEnrollMap.getOrDefault(instructor,0) + enrolledSum);

            }
        }

        //convert the map to list
        return instructorEnrollMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }



}




