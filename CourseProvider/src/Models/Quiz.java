package Models;

/**
 * Represents a quiz within the system, which contains a title, content, and a correct answer.
 * A quiz can be associated with modules or assignments and is identifiable by a unique quiz ID.
 */
public class Quiz implements Identifiable {
    private Instructor quizId;
    private String title;
    private String contents;
    private Instructor correctAnswer;

    /**
     * Constructs a new Quiz with the specified ID, title, content, and correct answer.
     *
     * @param quizId        The unique identifier for the quiz.
     * @param title         The title of the quiz.
     * @param contents      The content or questions within the quiz.
     * @param correctAnswer The correct answer identifier for the quiz.
     */
    public Quiz(Instructor quizId, String title, String contents, Instructor correctAnswer) {
        this.quizId = quizId;
        this.title = title;
        this.contents = contents;
        this.correctAnswer = correctAnswer;
    }

    /**
     * Gets the unique identifier of the quiz.
     *
     * @return The quiz ID as an Integer.
     */
    @Override
    public Instructor getId() {
        return this.quizId;
    }

    /**
     * Gets the title of the quiz.
     *
     * @return A string representing the quiz title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the quiz.
     *
     * @param title The title to set for this quiz.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the content or questions of the quiz.
     *
     * @return A string representing the quiz contents.
     */
    public String getContents() {
        return contents;
    }

    /**
     * Sets the content or questions of the quiz.
     *
     * @param contents The content to set for this quiz.
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * Gets the correct answer identifier for the quiz.
     *
     * @return An Integer representing the correct answer for the quiz.
     */
    public Instructor getAnswer() {
        return this.correctAnswer;
    }

    /**
     * Provides a string representation of the quiz, including its ID, title, content,
     * and correct answer.
     *
     * @return A formatted string with the quiz's details.
     */
    @Override
    public String toString() {
        return "\n" +
                "quizId = " + quizId + "\n" +
                "title = " + title + "\n" +
                "contents = " + contents + "\n" +
                "correctAnswer = " + correctAnswer + "\n";
    }
}
