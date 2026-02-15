package JTime.Controllers.Handlers.ProjectHandlers;

import JTime.Controllers.Handlers.IEventHandler;
import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;

/**
 * Interface for project-related event handlers.
 * Extends IEventHandler and provides context for project operations.
 */
public interface IProjectEventHandler extends IEventHandler {
    /**
     * Sets the current project context for operations.
     * @param project The project to operate on.
     */
    void setProject(IProject<IActivity> project);
}

