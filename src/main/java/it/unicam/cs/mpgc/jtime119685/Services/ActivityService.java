package it.unicam.cs.mpgc.jtime119685.Services;

import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultActivity;
import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultProject;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Repositories.ActivityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ActivityService {
    private final ActivityRepository _activityRepository;

    @Inject
    public ActivityService(ActivityRepository activityRepository) {
        _activityRepository = activityRepository;
    }

    public IActivity getActivityById(Long id) {
        return _activityRepository.findById(id);
    }

    public void addActivity(DefaultProject<DefaultActivity> project, String description, int duration) {
        DefaultActivity activity = new DefaultActivity(description, duration);
        project.addActivity(activity);
        _activityRepository.save(activity);
    }

    public void removeActivity(IProject<IActivity> project, IActivity activity) {
        project.removeActivity(activity);
        _activityRepository.update(activity);
    }

    public void completeActivity(Long id) {
        _activityRepository.findById(id).setCompleted(true);
    }

}
