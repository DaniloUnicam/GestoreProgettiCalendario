package it.unicam.cs.mpgc.jtime119685.Controllers;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Model.Services.ProjectService;
import jakarta.inject.Inject;

/**
 * Controller class for managing projects and their associated activities.
 */
public class ProjectController {

    private final ProjectService _projectService;

    @Inject
    public ProjectController(ProjectService _projectService) {
        this._projectService = _projectService;
    }

    /**
     * Creates a new project with an associated activity.
     *
     * @param project The project to be created.
     * @param activity The activity to be associated with the project.
     */
    public void createProjectWithActivity(IProject<IActivity> project, IActivity activity) {
        _projectService.createActivity(project, activity);
        _projectService.updateProject(project);
    }

    /**
     * Creates a new project.
     *
     * @param project The project to be created.
     */
    public void createProject(IProject<IActivity> project) {
        _projectService.createProject(project);
    }

    /**
     * Removes a project.
     *
     * @param project The project to be removed.
     */
    public void removeProject(IProject<IActivity> project) {
        _projectService.deleteProject(project);
    }

    /**
     * Removes an activity from a project.
     *
     * @param project The project from which the activity will be removed.
     * @param activity The activity to be removed.
     */
    public void removeActivityFromProject(IProject<IActivity> project, IActivity activity) {
        _projectService.deleteActivity(project, activity);
    }

    /**
     * Updates a project.
     *
     * @param project The project to be updated.
     */
    public void updateProject(IProject<IActivity> project) {
        _projectService.updateProject(project);
    }
}
