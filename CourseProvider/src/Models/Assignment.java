package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an assignment that may contain a list of quizzes and has an associated score.
 * Each assignment has a unique ID, description, due date, and score.
 */
public class Assignment implements Identifiable {
    private Integer assignmentID;
    private String description;
    private String dueDate;
    private List<Quiz> quizzes = new ArrayList<>();
    private Integer score;

    /**
     * Constructs an Assignment with the specified ID, description, due date, and score.
     *
     * @param assignmentID The unique ID of the assignment.
     * @param description  A brief description of the assignment.
     * @param dueDate      The due date for the assignment as a string.
     * @param score        The score or weight of the assignment.
     */
    public Assignment(Integer assignmentID, String description, String dueDate, Integer score) {
        this.assignmentID = assignmentID;
        this.description = description;
        this.dueDate = dueDate;
        this.score = score;
    }

    /**
     * Retrieves the description of the assignment.
     *
     * @return The description of the assignment.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the due date of the assignment.
     *
     * @return The due date of the assignment as a string.
     */
    public String getDueDate() {
        return this.dueDate;
    }

    /**
     * Sets the ID of the assignment.
     *
     * @param assignmentID The new ID for the assignment.
     */
    public void setAssignmentID(Integer assignmentID) {
        this.assignmentID = assignmentID;
    }

    /**
     * Updates the description of the assignment.
     *
     * @param description The new description for the assignment.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Updates the due date of the assignment.
     *
     * @param dueDate The new due date for the assignment as a string.
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Retrieves the list of quizzes associated with this assignment.
     *
     * @return A list of quizzes.
     */
    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    /**
     * Sets the quizzes for this assignment.
     *
     * @param quizzes A list of quizzes to associate with this assignment.
     */
    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    /**
     * Retrieves the score of the assignment.
     *
     * @return The score or weight of the assignment.
     */
    public Integer getScore() {
        return this.score;
    }

    /**
     * Sets the score of the assignment.
     *
     * @param score The new score for the assignment.
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * Retrieves the unique ID of the assignment.
     *
     * @return The assignment's unique ID.
     */
    @Override
    public Integer getId() {
        return this.assignmentID;
    }

    /**
     * Returns a string representation of the assignment, showing its ID, description,
     * due date, associated quizzes, and score.
     *
     * @return A formatted string representation of the assignment.
     */
    @Override
    public String toString() {
        return "\n" +
                "assignmentID = " + assignmentID + "\n" +
                "description = " + description + "\n" +
                "dueDate = " + dueDate + "\n" +
                "quizzes = " + quizzes.toString() + "\n" +
                "score = " + score;
    }
}
