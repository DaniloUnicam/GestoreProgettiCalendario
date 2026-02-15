package JTime.Controllers.Handlers.ProjectHandlers;

import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Services.ProjectService;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;

/**
 * Handler for editing a project.
 * Orchestrates the update of a project's properties and persists the changes.
 */
public class EditProjectHandler implements IProjectEventHandler {

    private final ProjectService _projectService;
    private IProject<IActivity> _project;

    @Inject
    public EditProjectHandler(ProjectService projectService) {
        this._projectService = projectService;
    }

    @Override
    public void setProject(IProject<IActivity> project) {
        this._project = project;
    }

    @Override
    public void handle(ActionEvent event) {
        if (_project == null) {
            throw new IllegalStateException("Project must be set before handling edit");
        }
        editProject();
    }

    /**
     * Updates the project in the database.
     * Note: The UI layer should modify the project properties before calling this handler.
     */
    private void editProject() {
        _projectService.updateProject(_project);
    }
}
