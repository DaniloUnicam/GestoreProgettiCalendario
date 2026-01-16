package Entities;

import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;
import Entities.Interfaces.IReport;

import java.util.ArrayList;

public class DefaultReport implements IReport {

    private ArrayList<IProject> projects;
    private ArrayList<IActivity> activities;

    public DefaultReport(ArrayList<IProject> projects, ArrayList<IActivity> activities) {
        this.projects = projects;
        this.activities = activities;
    }

    @Override
    public ArrayList<IProject> getListOfProjects() {
        return this.projects;
    }

    @Override
    public ArrayList<IActivity> getListOfActivities() {
        return this.activities;
    }



    @Override
    public int getTotalEstimatedDurationInHours() {
        return projects.stream()
                .flatMap(project -> project.getActivities().stream())
                .mapToInt(IActivity::getEstimatedDuration)
                .sum();
    }

}
