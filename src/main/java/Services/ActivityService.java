package Services;

import Entities.Interfaces.IActivity;
import Repositories.ActivityRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ActivityService {
    private final ActivityRepository _activityRepository;

    @Inject
    public ActivityService(ActivityRepository activityRepository) {
        _activityRepository = activityRepository;
    }

    public void saveActivity(IActivity activity) {
        _activityRepository.save(activity);
    }

    public void deleteActivity(IActivity activity) {
        _activityRepository.delete(activity);
    }

    public IActivity getActivityById(Long id) {
        return _activityRepository.findById(id);
    }

}
