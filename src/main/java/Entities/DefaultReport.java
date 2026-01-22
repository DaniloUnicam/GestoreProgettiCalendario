package Entities;

import DateUtilities.DurationAware;
import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;
import Entities.Interfaces.IReport;

import java.util.List;

public class DefaultReport implements IReport, DurationAware {

    private final List<IProject<? extends IActivity>> projects;
    private final List<IActivity> activities;

    public DefaultReport(List<IProject<? extends IActivity>> projects, List<IActivity> activities) {
        this.projects = projects;
        this.activities = activities;
    }

    @Override
    public List<IProject<? extends IActivity>> getListOfProjects() {
        return this.projects;
    }

    @Override
    public List<IActivity> getListOfActivities() {
        return this.activities;
    }

    @Override
    public int getTotalEstimatedDurationInMinutes() {
        return projects.stream()
                .flatMap(project -> project.getActivities().stream())
                .mapToInt(DurationAware::getTotalEstimatedDurationInMinutes)
                .sum();

    }
}
