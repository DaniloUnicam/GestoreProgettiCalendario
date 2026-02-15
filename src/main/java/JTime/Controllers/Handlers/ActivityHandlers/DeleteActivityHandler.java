package JTime.Controllers.Handlers.ActivityHandlers;

import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Services.ActivityService;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;

/**
 * Handler for deleting an activity.
 * Orchestrates the removal of an activity from a project and its deletion from persistence.
 */
public class DeleteActivityHandler implements IActivityEventHandler {

    private final ActivityService _activityService;
    private IProject<IActivity> _project;
    private IActivity _activity;

    @Inject
    public DeleteActivityHandler(ActivityService activityService) {
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
        if (_project == null || _activity == null) {
            throw new IllegalStateException("Project and Activity must be set before handling deletion");
        }
        deleteActivity();
    }

    /**
     * Deletes an activity from the project and the database.
     */
    private void deleteActivity() {
        _project.deleteActivity(_activity);
        _activityService.deleteActivity(_activity);
    }
}
