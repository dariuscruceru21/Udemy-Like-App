package Models;

public class Enrolled implements Identifiable {
    private Integer id;
    private Integer studentId;
    private Integer courseId;

    public Enrolled(Integer id, Integer studentId, Integer courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public Integer getId() {
        return id;
    }


    public Integer getStudentId() {
        return studentId;
    }


    public Integer getCourseId() {
        return courseId;
    }

}
