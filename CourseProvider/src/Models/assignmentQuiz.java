package Models;

public class assignmentQuiz {
    private Integer assignmentId;
    private Integer quizId;

    public assignmentQuiz(Integer assignmentId, Integer quizId) {
        this.assignmentId = assignmentId;
        this.quizId = quizId;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }
}
