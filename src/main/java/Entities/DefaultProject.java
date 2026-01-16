package Entities;

import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;

import java.util.ArrayList;

public class DefaultProject implements IProject {
    //A name appropriate to the current ongoing Project
    private String name;
    //A description containing information about the current ongoing Project
    private String description;
    //A list of Activities
    private ArrayList<IActivity> activities;
    //A project is closed when all activities are completed
    private boolean isClosed;

    public DefaultProject(String name, String description) {
        this.name = name;
        this.description = description;
        isClosed = false;
        this.activities = new ArrayList<>();
    }

    public DefaultProject(String name, String description, ArrayList<IActivity> activities) {
        this.name = name;
        this.description = description;
        isClosed = false;
        if(activities == null || activities.isEmpty()) {
            this.activities = new ArrayList<>();
        }
        this.activities = activities;
    }

    public void addActivity(IActivity activity)
    {
        this.activities.add(activity);
    }

    public void removeActivity(IActivity activity)
    {
        this.activities.remove(activity);
    }

    public IActivity getActivity(int id) {
        return this.activities.get(id);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public ArrayList<IActivity> getActivities() {
        return activities;
    }

    @Override
    public void setActivities(ArrayList<IActivity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public void close() {
        if(this.activities.stream().allMatch(IActivity::isCompleted)){
            isClosed = true;
        }
    }
}
