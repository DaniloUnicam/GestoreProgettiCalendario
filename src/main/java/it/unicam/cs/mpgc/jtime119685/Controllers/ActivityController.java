package it.unicam.cs.mpgc.jtime119685.Controllers;

import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Model.Services.ActivityService;
import jakarta.inject.Inject;

public class ActivityController {

    private final ActivityService _activityService;

    @Inject
    public ActivityController(ActivityService activityService) {
        this._activityService = activityService;
    }

    public void createActivity(IProject<IActivity> project, String description, int duration) {
        DefaultActivity activity = new DefaultActivity(description, duration);
        project.createActivity(activity);
        _activityService.createActivity(activity);
    }

    public IActivity findActivityById(IProject<IActivity> project, Long id) {
        return project.getActivity(id);
    }

    public void updateActivity(IActivity activity) {
        _activityService.updateActivity(activity);
    }

    public void deleteActivity(IProject<IActivity> project, IActivity activity) {
        project.deleteActivity(activity);
        _activityService.deleteActivity(activity);
    }

    public void deleteActivityById(IProject<IActivity> project, Long id) {
        IActivity activity = project.getActivity(id);
        project.deleteActivity(activity);
        _activityService.deleteActivity(activity);
    }

    public void setActivityAsCompletedById(Long id) {
        _activityService.findById(id).setCompleted(true);
    }
}

