package JTime.Controllers.Handlers.ProjectHandlers;

import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Services.ProjectService;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;

/**
 * Handler for closing a project.
 * Marks a project as closed and persists the change.
 */
public class CloseProjectHandler implements IProjectEventHandler {

    private final ProjectService _projectService;
    private IProject<IActivity> _project;

    @Inject
    public CloseProjectHandler(ProjectService projectService) {
        this._projectService = projectService;
    }

    @Override
    public void setProject(IProject<IActivity> project) {
        this._project = project;
    }

    @Override
    public void handle(ActionEvent event) {
        if (_project == null) {
            throw new IllegalStateException("Project must be set before handling close");
        }
        closeProject();
    }

    /**
     * Marks the project as closed and updates it in the database.
     */
    private void closeProject() {
        _project.setClosed(true);
        _projectService.updateProject(_project);
    }
}
