package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a course module within the system, which contains a title, content,
 * and a list of assignments. A module can be associated with a course and contains
 * methods for managing assignments and enrolling students.
 */
public class Module implements Identifiable {
    private int moduleID;
    private String moduleTitle;
    private String moduleContent;
    private List<Assignment> assignments = new ArrayList<>();

    /**
     * Constructs a new Module with the specified ID, title, and content.
     *
     * @param moduleID      The unique identifier for the module.
     * @param moduleTitle   The title of the module.
     * @param moduleContent The content or description of the module.
     */
    public Module(int moduleID, String moduleTitle, String moduleContent) {
        this.moduleID = moduleID;
        this.moduleTitle = moduleTitle;
        this.moduleContent = moduleContent;
    }

    /**
     * Gets the title of the module.
     *
     * @return A string representing the module title.
     */
    public String getModuleTitle() {
        return this.moduleTitle;
    }

    /**
     * Gets the content or description of the module.
     *
     * @return A string representing the module content.
     */
    public String getModuleContent() {
        return this.moduleContent;
    }

    /**
     * Placeholder method to update the module's information.
     * (Implementation details would be added here.)
     */
    public void updateModule() {
        // Update module details; implementation to be added.
    }

    /**
     * Placeholder method to enroll a student in the module.
     *
     * @param student The student to enroll in the module.
     * (Implementation details would be added here.)
     */
    public void enrollStudent(Student student) {
        // Enroll a student in the module; implementation to be added.
    }

    /**
     * Gets the unique identifier of the module.
     *
     * @return The module ID as an integer.
     */
    public int getModuleID() {
        return moduleID;
    }

    /**
     * Sets the unique identifier for the module.
     *
     * @param moduleID The ID to assign to this module.
     */
    public void setModuleID(int moduleID) {
        this.moduleID = moduleID;
    }

    /**
     * Sets the title of the module.
     *
     * @param moduleTitle The title to set for this module.
     */
    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    /**
     * Sets the content or description of the module.
     *
     * @param moduleContent The content to set for this module.
     */
    public void setModuleContent(String moduleContent) {
        this.moduleContent = moduleContent;
    }

    /**
     * Retrieves the list of assignments associated with the module.
     *
     * @return A list of Assignment objects in this module.
     */
    public List<Assignment> getAssignments() {
        return assignments;
    }

    /**
     * Sets the assignments associated with the module.
     *
     * @param assignments The list of assignments to assign to this module.
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    /**
     * Adds an assignment to the module's list of assignments.
     *
     * @param assignment The Assignment to add to this module.
     */
    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    /**
     * Retrieves the ID of the module (used by Identifiable interface).
     *
     * @return The module's unique identifier.
     */
    @Override
    public Integer getId() {
        return this.moduleID;
    }

    /**
     * Provides a string representation of the module's details, including
     * the ID, title, and content.
     *
     * @return A formatted string with the module's details.
     */
    @Override
    public String toString() {
        return "\n" +
                "moduleID = " + moduleID + "\n" +
                "moduleTitle = " + moduleTitle + "\n" +
                "moduleContent = " + moduleContent + "\n";
    }
}
