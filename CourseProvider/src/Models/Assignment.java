package Models;

import Repository.Identifiable;

import java.text.DateFormat;

public class Assignment implements Identifiable {
    private int assignmentID;
    private String description;
    private DateFormat dueDate;

    Assignment(int assignmentID, String description, DateFormat dueDate) {
        this.assignmentID = assignmentID;
        this.description = description;
        this.dueDate = dueDate;
    }



    public String getDescription() {
        return this.description;
    }

    public DateFormat getDueDate() {
        return this.dueDate;
    }

    @Override
    public int getId() {
        return this.assignmentID;
    }
}
