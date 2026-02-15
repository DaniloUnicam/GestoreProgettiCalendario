package JTime.Controllers.Handlers.ActivityHandlers;

import JTime.Controllers.Handlers.IEventHandler;
import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;

/**
 * Interface for activity-related event handlers.
 * Extends IEventHandler and provides context for activity operations.
 */
public interface IActivityEventHandler extends IEventHandler {
    /**
     * Sets the current project context for activity operations.
     * @param project The project containing the activities.
     */
    void setProject(IProject<IActivity> project);

    /**
     * Sets the current activity context for operations.
     * @param activity The activity to operate on.
     */
    void setActivity(IActivity activity);
}

