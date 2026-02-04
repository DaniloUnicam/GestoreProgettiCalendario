package it.unicam.cs.mpgc.jtime119685.Model.Services;

import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Model.Repositories.ActivityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
/**
 * Service class for managing activities within projects.
 */
@ApplicationScoped
public class ActivityService {
    private final ActivityRepository _activityRepository;

    @Inject
    public ActivityService(ActivityRepository activityRepository) {
        _activityRepository = activityRepository;
    }

    /**
     * Creates a new activity and adds it to the specified project.
     * @param project The project to which the activity will be added.
     * @param description The description of the activity.
     * @param duration The duration of the activity in minutes.
     */
    public void createActivity(IProject<IActivity> project, String description, int duration) {
        DefaultActivity activity = new DefaultActivity(description, duration);
        project.createActivity(activity);
        _activityRepository.save(activity);
    }

    /**
     * Saves a new activity to the repository.
     * @param activity The activity to be created.
     */
    public void createActivity(IActivity activity) {
        _activityRepository.save(activity);
    }

    /**
     * Finds an activity by its ID.
     * @param id The ID of the activity to be found.
     * @return The activity with the specified ID.
     */
    public IActivity findById(Long id) {
        return _activityRepository.findById(id);
    }

    /**
     * Updates an activity in the repository.
     * @param activity The activity to be updated.
     */
    public void updateActivity(IActivity activity) {
        _activityRepository.findById(activity.getId());
    }

    /**
     * Deletes an activity from the repository.
     * orphanRemoval should be handled by the project entity.
     * @param activity The activity to be deleted.
     */
    public void deleteActivity(IActivity activity) {
        _activityRepository.delete(activity);
    }

    /**
     * Marks an activity as completed by its ID.
     * @param id The ID of the activity to be marked as completed.
     */
    public void completeActivity(Long id) {
        _activityRepository.findById(id).setCompleted(true);
        _activityRepository.update(_activityRepository.findById(id));
    }


}
