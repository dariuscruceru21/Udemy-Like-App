package Models;

public class moduleAssignments {
    private Integer moduleId;
    private Integer assignmentId;


    public moduleAssignments(Integer moduleId, Integer assignmentId) {
        this.moduleId = moduleId;
        this.assignmentId = assignmentId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }
}
