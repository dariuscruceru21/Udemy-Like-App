package Models;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class Assignment implements Identifiable {
    private int assignmentID;
    private String description;
    private String dueDate;
    private List<Quiz> quizzes = new ArrayList<Quiz>();
    private Integer score;

    public Assignment(int assignmentID, String description, String  dueDate,Integer score) {
        this.assignmentID = assignmentID;
        this.description = description;
        this.dueDate = dueDate;
        this.score = score;
    }



    public String getDescription() {
        return this.description;
    }

    public String getDueDate() {
        return this.dueDate;
    }



    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public Integer getScore(){
        return this.score;
    }

    public void setScore(Integer score){
        this.score = score;
    }

    @Override
    public Integer getId() {
        return this.assignmentID;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentID=" + assignmentID +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", quizzes=" + quizzes +
                ", score=" + score +
                '}';
    }
}
