package Entities.DefaultEntities;

import DateUtilities.DurationAware;
import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;
import Entities.Interfaces.IReport;

import java.util.List;
/**
 * Default implementation of the IReport interface.
 * @param <T> the type of activity associated with the report
 */
public class DefaultReport<T extends IActivity> implements IReport, DurationAware {
    // A list of multiple Project types
    private final List<IProject<? extends IActivity>> projects;
    // A list of multiple Activity types
    private final List<T> activities;

    /**
     * The Report constructor.
     * @param projects the list of projects of the Report
     * @param activities the list of activities of the Report
     */
    public DefaultReport(List<IProject<? extends IActivity>> projects, List<T> activities) {
        this.projects = projects;
        this.activities = activities;
    }

    @Override
    public List<IProject<? extends IActivity>> getListOfProjects() {
        return this.projects;
    }

    @Override
    public List<T> getListOfActivities() {
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
