package JTime.Controllers.Handlers.ProjectHandlers;

import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Services.ProjectService;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;

/**
 * Handler for creating a new project.
 * Orchestrates the creation and persistence of a new project.
 */
public class CreateProjectHandler implements IProjectEventHandler {

    private final ProjectService _projectService;
    private IProject<IActivity> _project;

    @Inject
    public CreateProjectHandler(ProjectService projectService) {
        this._projectService = projectService;
    }

    @Override
    public void setProject(IProject<IActivity> project) {
        this._project = project;
    }

    @Override
    public void handle(ActionEvent event) {
        if (_project == null) {
            throw new IllegalStateException("Project must be set before handling creation");
        }
        createProject();
    }

    /**
     * Creates a new project and persists it to the database.
     */
    private void createProject() {
        _projectService.createProject(_project);
    }
}
