package JTime.Controllers.Handlers.ActivityHandlers;

import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Services.ActivityService;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;

/**
 * Handler for editing an activity.
 * Orchestrates the update of an activity's properties and persists the changes.
 */
public class EditActivityHandler implements IActivityEventHandler {

    private final ActivityService _activityService;
    private IProject<IActivity> _project;
    private IActivity _activity;

    @Inject
    public EditActivityHandler(ActivityService activityService) {
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
            throw new IllegalStateException("Activity must be set before handling edit");
        }
        editActivity();
    }

    /**
     * Updates the activity in the database.
     * Note: The UI layer should modify the activity properties before calling this handler.
     */
    private void editActivity() {
        _activityService.updateActivity(_activity);
    }
}
