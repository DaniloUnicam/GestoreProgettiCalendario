package JTime.Controllers.Handlers.ActivityHandlers;

import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Services.ActivityService;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;

/**
 * Handler for creating a new activity.
 * Orchestrates the creation of a new activity and its addition to a project.
 */
public class CreateActivityHandler implements IActivityEventHandler {

    private final ActivityService _activityService;
    private IProject<IActivity> _project;
    private IActivity _activity;

    @Inject
    public CreateActivityHandler(ActivityService activityService) {
        this._activityService = activityService;
    }

    @Override
    public void setProject(IProject<IActivity> project) {
        this._project = project;
    }

    @Override
    public void setActivity(IActivity activity) {
        this._activity = activity;
    }

    @Override
    public void handle(ActionEvent event) {
        if (_project == null) {
            throw new IllegalStateException("Project must be set before creating an activity");
        }
        if (_activity == null) {
            throw new IllegalStateException("Activity must be set before handling creation");
        }
        createActivity();
    }

    /**
     * Creates a new activity and adds it to the project.
     */
    private void createActivity() {
        _project.addActivity(_activity);
        _activityService.createActivity(_activity);
    }
}
