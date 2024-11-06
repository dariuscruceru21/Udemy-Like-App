package Models;

import java.text.DateFormat;

public class Course implements Identifiable {
    private Integer courseID;
    private String courseTitle;
    private String decision;
    private DateFormat startDate;
    private DateFormat endDate;

    Course(Integer courseID, String courseTitle, String decision, DateFormat startDate, DateFormat endDate) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.decision = decision;
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

    @Override
    public Integer getId() {
        return this.courseID;
    }
}
