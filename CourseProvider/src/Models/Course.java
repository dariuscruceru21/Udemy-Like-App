package Models;

import java.text.DateFormat;
import java.util.List;

public class Course implements Identifiable {
    private Integer courseID;
    private String courseTitle;
    private String decision;
    private Integer availableSpots;
    private String  startDate;
    private String  endDate;
    private List<Student> enrolledStudents;
    private Instructor instructor;

    public Course(Integer courseID, String courseTitle, String decision,Integer availableSpots, String  startDate, String  endDate, Instructor instructor) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.decision = decision;
        this.availableSpots = availableSpots;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
    }



    public String getCourseTitle() {
        return this.courseTitle;
    }
    public String getDecision() {
        return this.decision;
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

    @Override
    public Integer getId() {
        return this.courseID;
    }
}
