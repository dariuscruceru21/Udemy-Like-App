package Models;

public class Quiz implements Identifiable{
    private Integer quizId;
    private String title;
    private String contents;
    private Integer correctAnswear;


    public Quiz(Integer quizId, String title, String contents,Integer correctAnswear) {
        this.quizId = quizId;
        this.title = title;
        this.contents = contents;
        this.correctAnswear = correctAnswear;

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



    public Integer getAnswer(){
        return this.correctAnswear;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizId=" + quizId +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", correctAnswear=" + correctAnswear +
                '}';
    }
}
