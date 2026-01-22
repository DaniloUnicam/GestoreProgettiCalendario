package Services;

import Entities.Interfaces.IProject;


public class HandlerProject {
    //The project being handled
    private IProject project;

    /**
     * The HandlerProject constructor.
     * @param project the project to be handled
     */
    public HandlerProject(IProject project) {
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
    public void setProject(IProject project) {
        this.project = project;
    }
}
