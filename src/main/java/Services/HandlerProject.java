package Services;

import Entities.Interfaces.IProject;

public class HandlerProject {
    private IProject project;

    public HandlerProject(IProject project) {
        this.project = project;
    }

    public IProject getProject() {
        return project;
    }

    public void setProject(IProject project) {
        this.project = project;
    }
}
