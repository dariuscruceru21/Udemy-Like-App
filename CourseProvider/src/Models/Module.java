package Models;

import Repository.Identifiable;

public class Module implements Identifiable {
    private int moduleID;
    private String moduleTitle;
    private String moduleContent;

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

    @Override
    public int getId() {
        return this.moduleID;
    }
}
