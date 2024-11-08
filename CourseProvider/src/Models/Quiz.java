package Models;

public class Quiz implements Identifiable{
    private Integer quizId;
    private String title;
    private String contents;
    private Double grade;
    private boolean status;

    public Quiz(Integer quizId, String title, String contents, Double grade, boolean status) {
        this.quizId = quizId;
        this.title = title;
        this.contents = contents;
        this.grade = grade;
        this.status = status;
    }

    @Override
    public Integer getId() {
        return this.quizId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
