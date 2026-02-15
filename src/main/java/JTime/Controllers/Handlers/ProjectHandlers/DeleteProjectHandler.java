package JTime.Controllers.Handlers.ProjectHandlers;

import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Services.ProjectService;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;

/**
 * Handler for deleting a project.
 * Orchestrates the removal of a project and its deletion from persistence.
 */
public class DeleteProjectHandler implements IProjectEventHandler {

    private final ProjectService _projectService;
    private IProject<IActivity> _project;

    @Inject
    public DeleteProjectHandler(ProjectService projectService) {
        this._projectService = projectService;
    }

    @Override
    public void setProject(IProject<IActivity> project) {
        this._project = project;
    }

    @Override
    public void handle(ActionEvent event) {
        if (_project == null) {
            throw new IllegalStateException("Project must be set before handling deletion");
        }
        deleteProject();
    }

    /**
     * Deletes the project from the database.
     */
    private void deleteProject() {
        _projectService.deleteProject(_project);
    }
}
