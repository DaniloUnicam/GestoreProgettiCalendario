package Services;

import Entities.Interfaces.IProject;


public class HandlerProject<T extends IProject> {
    //The project being handled
    private T project;

    /**
     * The HandlerProject constructor.
     * @param project the project to be handled
     */
    public HandlerProject(T project) {
        this.project = project;
    }

    /**
     * Gets the project being handled.
     * @return the project
     */
    public IProject getProject() {
        return project;
    }

    /**
     * Sets the project being handled.
     * @param project the project to be set
     */
    public void setProject(T project) {
        this.project = project;
    }
}
