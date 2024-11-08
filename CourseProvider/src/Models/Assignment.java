package Models;

import java.text.DateFormat;
import java.util.List;

public class Assignment implements Identifiable {
    private int assignmentID;
    private String description;
    private DateFormat dueDate;
    private List<Quiz> quizzes;

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

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(DateFormat dueDate) {
        this.dueDate = dueDate;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public Integer getId() {
        return this.assignmentID;
    }
}
