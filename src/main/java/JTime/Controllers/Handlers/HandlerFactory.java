package JTime.Controllers.Handlers;

import JTime.Controllers.Handlers.ActivityHandlers.CompleteActivityHandler;
import JTime.Controllers.Handlers.ActivityHandlers.CreateActivityHandler;
import JTime.Controllers.Handlers.ActivityHandlers.DeleteActivityHandler;
import JTime.Controllers.Handlers.ActivityHandlers.EditActivityHandler;
import JTime.Controllers.Handlers.ProjectHandlers.CloseProjectHandler;
import JTime.Controllers.Handlers.ProjectHandlers.CreateProjectHandler;
import JTime.Controllers.Handlers.ProjectHandlers.DeleteProjectHandler;
import JTime.Controllers.Handlers.ProjectHandlers.EditProjectHandler;
import JTime.Persistence.Services.ActivityService;
import JTime.Persistence.Services.ProjectService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

/**
 * Factory for creating and configuring event handlers.
 * This factory is responsible for dependency injection and handler setup.
 * It acts as a central point for handler instantiation.
 */
@Singleton
public class HandlerFactory {

    private final ActivityService _activityService;
    private final ProjectService _projectService;

    @Inject
    public HandlerFactory(ActivityService activityService, ProjectService projectService) {
        this._activityService = activityService;
        this._projectService = projectService;
    }

    // ============= Activity Handlers =============

    /**
     * Creates a handler for creating new activities.
     * @return A new CreateActivityHandler instance.
     */
    public CreateActivityHandler createActivityHandler() {
        return new CreateActivityHandler(_activityService);
    }

    /**
     * Creates a handler for deleting activities.
     * @return A new DeleteActivityHandler instance.
     */
    public DeleteActivityHandler deleteActivityHandler() {
        return new DeleteActivityHandler(_activityService);
    }

    /**
     * Creates a handler for editing activities.
     * @return A new EditActivityHandler instance.
     */
    public EditActivityHandler editActivityHandler() {
        return new EditActivityHandler(_activityService);
    }

    /**
     * Creates a handler for completing activities.
     * @return A new CompleteActivityHandler instance.
     */
    public CompleteActivityHandler completeActivityHandler() {
        return new CompleteActivityHandler(_activityService);
    }

    // ============= Project Handlers =============

    /**
     * Creates a handler for creating new projects.
     * @return A new CreateProjectHandler instance.
     */
    public CreateProjectHandler createProjectHandler() {
        return new CreateProjectHandler(_projectService);
    }

    /**
     * Creates a handler for deleting projects.
     * @return A new DeleteProjectHandler instance.
     */
    public DeleteProjectHandler deleteProjectHandler() {
        return new DeleteProjectHandler(_projectService);
    }

    /**
     * Creates a handler for editing projects.
     * @return A new EditProjectHandler instance.
     */
    public EditProjectHandler editProjectHandler() {
        return new EditProjectHandler(_projectService);
    }

    /**
     * Creates a handler for closing projects.
     * @return A new CloseProjectHandler instance.
     */
    public CloseProjectHandler closeProjectHandler() {
        return new CloseProjectHandler(_projectService);
    }
}

