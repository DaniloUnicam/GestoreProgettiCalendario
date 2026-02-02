package it.unicam.cs.mpgc.jtime119685.Model.Interfaces;

import java.util.List;

/**
 * Interface representing a report containing activities and projects.
 * A report is a summary of activities and projects over a certain period or criteria.
 *
 * @param <T> the type of activity associated with the report
 */
public interface IReport<T extends IActivity, S extends IProject<T>> {

    /**
     * A list of all activities included in the report.
     *
     * @return the list of activities
     */
    List<IActivity> getListOfActivities();

    /**
     * A list of all projects included in the report.
     *
     * @return the list of projects
     */
    List<IProject<IActivity>> getListOfProjects();


}
