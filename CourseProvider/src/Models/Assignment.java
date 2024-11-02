package Models;

import java.text.DateFormat;

public class Assignment {
    private int assignmentID;
    private String description;
    private DateFormat dueDate;

    Assignment(int assignmentID, String description, DateFormat dueDate) {
        this.assignmentID = assignmentID;
        this.description = description;
        this.dueDate = dueDate;
    }

    public int getAssignmentID() {
        return this.assignmentID;
    }

    public String getDescription() {
        return this.description;
    }

    public DateFormat getDueDate() {
        return this.dueDate;
    }

}
