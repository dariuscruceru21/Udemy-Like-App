package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course in the system, which includes information about the course
 * title, description, schedule, instructor, enrolled students, and associated modules.
 */
public class Course implements Identifiable {
    /** Unique identifier for the course */
    private Integer courseID;
    /** Title of the course */
    private String courseTitle;
    /** Description of the course */
    private String description;
    /** Number of available spots for enrollment */
    private Integer availableSpots;
    /** Start date of the course */
    private String startDate;
    /** End date of the course */
    private String endDate;
    /** List of students enrolled in the course */
    private List<Student> enrolledStudents = new ArrayList<>();
    /** List of modules included in the course */
    private List<Module> modules = new ArrayList<>();
    /** The instructor teaching this course */
    private Integer instructorId;

    /**
     * Constructs a Course object with specified attributes.
     *
     * @param courseID       The unique ID of the course.
     * @param courseTitle    The title of the course.
     * @param description    A description of the course content.
     * @param availableSpots The number of spots available for students to enroll.
     * @param startDate      The start date of the course.
     * @param endDate        The end date of the course.
     * @param instructor     The instructor teaching the course.
     */
    public Course(Integer courseID, String courseTitle, String description, Integer availableSpots, String startDate, String endDate, Integer instructor) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.description = description;
        this.availableSpots = availableSpots;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructorId = instructor;
    }

    /**
     * Gets the title of the course.
     *
     * @return The course title.
     */
    public String getCourseTitle() {
        return this.courseTitle;
    }

    /**
     * Gets the description of the course.
     *
     * @return The course description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the start date of the course.
     *
     * @return The course start date.
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the end date of the course.
     *
     * @return The course end date.
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * Gets the number of available spots in the course.
     *
     * @return The number of available spots.
     */
    public Integer getAvailableSpots() {
        return this.availableSpots;
    }

    /**
     * Gets the list of students currently enrolled in the course.
     *
     * @return A list of enrolled students.
     */
    public List<Student> getEnrolledStudents() {
        return this.enrolledStudents;
    }


    /**
     * Sets the list of enrolled students.
     *
     * @param enrolledStudents The list of students to set as enrolled.
     */
    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }



    /**
     * Sets the unique identifier for the course.
     *
     * @param courseID The unique course ID to set.
     */
    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    /**
     * Sets the title of the course.
     *
     * @param courseTitle The course title to set.
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * Sets the description of the course.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the number of available spots for the course.
     *
     * @param availableSpots The number of available spots to set.
     */
    public void setAvailableSpots(Integer availableSpots) {
        this.availableSpots = availableSpots;
    }

    /**
     * Sets the start date for the course.
     *
     * @param startDate The start date to set.
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the end date for the course.
     *
     * @param endDate The end date to set.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets the list of modules associated with the course.
     *
     * @return A list of modules.
     */
    public List<Module> getModules() {
        return modules;
    }

    /**
     * Sets the list of modules for the course.
     *
     * @param modules The list of modules to set.
     */
    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    /**
     * Adds a single module to the course.
     *
     * @param module The module to add to the course.
     */
    public void addModule(Module module) {
        this.modules.add(module);
    }

    /**
     * Gets the unique ID of the course.
     *
     * @return The course ID.
     */



    /**
     * Returns a string representation of the course.
     *
     * @return A formatted string with the course's details.
     */
    @Override
    public String toString() {
        return '\n' +
                "courseID = " + courseID + '\n' +
                "courseTitle = " + courseTitle + '\n' +
                "description = " + description + '\n' +
                "availableSpots = " + availableSpots + '\n' +
                "startDate = " + startDate + '\n' +
                "endDate = " + endDate + '\n' +
                "instructorId = " + instructorId;
    }


    @Override
    public Integer getId() {
        return this.courseID;
    }
}
