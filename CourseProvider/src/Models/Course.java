package Models;

import java.util.ArrayList;
import java.util.List;

public class Course implements Identifiable {
    private Integer courseID;
    private String courseTitle;
    private String description;
    private Integer availableSpots;
    private String  startDate;
    private String  endDate;
    private List<Student> enrolledStudents = new ArrayList<Student>();
    private List<Module> modules = new ArrayList<Module>();
    private Instructor instructor;

    public Course(Integer courseID, String courseTitle, String description,Integer availableSpots, String  startDate, String  endDate, Instructor instructor) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.description = description;
        this.availableSpots = availableSpots;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
    }



    public String getCourseTitle() {
        return this.courseTitle;
    }
    public String getDescription() {
        return this.description;
    }
    public String  getStartDate() {
        return this.startDate;
    }
    public String  getEndDate() {
        return this.endDate;
    }
    public Integer getAvailableSpots(){return this.availableSpots;}
    public List<Student> getEnrolledStudents(){return this.enrolledStudents;}

    public Instructor getInstructor() {
        return this.instructor;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }



    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAvailableSpots(Integer availableSpots) {
        this.availableSpots = availableSpots;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }



    @Override
    public Integer getId() {
        return this.courseID;
    }

    @Override
    public String toString() {
        return  '\n' +
                "courseID = " + courseID + '\n' +
                "courseTitle = " + courseTitle + '\n' +
                "description = " + description + '\n' +
                "availableSpots = " + availableSpots + '\n' +
                "startDate = " + startDate + '\n' +
                "endDate = " + endDate + '\n' +
                "enrolledStudents = " + enrolledStudents + '\n' +
                "modules = " + modules + '\n' +
                "instructor = " + instructor.getUserName();
    }
}
