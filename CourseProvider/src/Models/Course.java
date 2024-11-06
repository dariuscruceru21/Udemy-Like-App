package Models;

import java.text.DateFormat;
import java.util.List;

public class Course implements Identifiable {
    private Integer courseID;
    private String courseTitle;
    private String decision;
    private Integer availableSpots;
    private DateFormat startDate;
    private DateFormat endDate;
    private List<Student> enrolledStudents;

    Course(Integer courseID, String courseTitle, String decision,Integer availableSpots, DateFormat startDate, DateFormat endDate) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.decision = decision;
        this.availableSpots = availableSpots;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getCourseTitle() {
        return this.courseTitle;
    }
    public String getDecision() {
        return this.decision;
    }
    public DateFormat getStartDate() {
        return this.startDate;
    }
    public DateFormat getEndDate() {
        return this.endDate;
    }
    public Integer getAvailableSpots(){return this.availableSpots;}
    public List<Student> getEnrolledStudents(){return this.enrolledStudents;}

    @Override
    public Integer getId() {
        return this.courseID;
    }
}
