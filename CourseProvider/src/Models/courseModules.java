package Models;

public class courseModules {
    private  Integer courseId;
    private Integer moduleId;

    public courseModules(Integer courseId, Integer moduleId) {
        this.courseId = courseId;
        this.moduleId = moduleId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}
