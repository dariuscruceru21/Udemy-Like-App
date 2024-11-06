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

    public Course(Integer courseID, String courseTitle, String decision,Integer availableSpots, String  startDate, String  endDate) {
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
    public String  getStartDate() {
        return this.startDate;
    }
    public String  getEndDate() {
        return this.endDate;
    }
    public Integer getAvailableSpots(){return this.availableSpots;}
    public List<Student> getEnrolledStudents(){return this.enrolledStudents;}

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    @Override
    public Integer getId() {
        return this.courseID;
    }
}
