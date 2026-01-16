package Entities.Interfaces;

import java.util.ArrayList;

public interface IReport {

    ArrayList<IActivity> getListOfActivities();

    ArrayList<IProject> getListOfProjects();

    /**
     * Calculates the total estimated duration of all activities across all projects in the report.
     * @return the total estimated duration in hours
     */
    int getTotalEstimatedDurationInHours();

}
