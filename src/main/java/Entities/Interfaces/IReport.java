package Entities.Interfaces;

import java.util.List;

/**
 * Interface representing a report containing activities and projects.
 */
public interface IReport {

    /**
     * A list of all activities included in the report.
     * @return the list of activities
     */
    List<IActivity> getListOfActivities();

    /**
     * A list of all projects included in the report.
     * @return the list of projects
     */
    List<IProject<? extends IActivity>> getListOfProjects();

}
