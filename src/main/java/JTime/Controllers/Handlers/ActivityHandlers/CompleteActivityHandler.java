package JTime.Controllers.Handlers.ActivityHandlers;

import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Services.ActivityService;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;

/**
 * Handler for completing an activity.
 * Orchestrates the service calls and UI updates when an activity is marked as completed.
 */
public class CompleteActivityHandler implements IActivityEventHandler {

    private final ActivityService _activityService;
    private IProject<IActivity> _project;
    private IActivity _activity;

    @Inject
    public CompleteActivityHandler(ActivityService activityService) {
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
        if (_activity == null) {
            throw new IllegalStateException("Activity must be set before handling completion");
        }
        completeActivity();
    }

    /**
     * Marks the activity as completed and persists the change.
     */
    private void completeActivity() {
        _activity.setCompleted(true);
        _activityService.updateActivity(_activity);
    }
}
