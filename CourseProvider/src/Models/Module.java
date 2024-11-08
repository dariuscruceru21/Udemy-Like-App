package Models;

import java.util.List;

public class Module implements Identifiable {
    private int moduleID;
    private String moduleTitle;
    private String moduleContent;
    private List<Assignment> assignments;

    Module(int moduleID, String moduleTitle, String moduleContent) {
        this.moduleID = moduleID;
        this.moduleTitle = moduleTitle;
        this.moduleContent = moduleContent;
    }


    public String getModuleTitle() {
        return this.moduleTitle;
    }

    public String getModuleContent() {
        return this.moduleContent;
    }

    public void updateModule() {
        //implementation
    }

    public void enrollStudent(Student student) {
        //implementation
    }

    public int getModuleID() {
        return moduleID;
    }

    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    public void setModuleContent(String moduleContent) {
        this.moduleContent = moduleContent;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @Override
    public Integer getId() {
        return this.moduleID;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleID=" + moduleID +
                ", moduleTitle='" + moduleTitle + '\'' +
                ", moduleContent='" + moduleContent + '\'' +
                ", assignments=" + assignments +
                '}';
    }
}
