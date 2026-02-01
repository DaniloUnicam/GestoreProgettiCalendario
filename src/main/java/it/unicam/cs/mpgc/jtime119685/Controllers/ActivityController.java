package it.unicam.cs.mpgc.jtime119685.Controllers;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Services.ProjectService;

public class ActivityController {

    private final ProjectService projectService;

    public ActivityController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public void addActivity(IProject<IActivity> project,IActivity activity) {
        project.addActivity(activity);
        projectService.update(project);
    }

    public void completeActivity(IProject<IActivity> activity, Long id) {
        projectService.getActivity(activity,id).setCompleted(true);
    }
}

