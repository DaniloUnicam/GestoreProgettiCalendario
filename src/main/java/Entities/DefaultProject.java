package Entities;

import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;

import java.util.ArrayList;
import java.util.List;

public class DefaultProject<T extends IActivity> implements IProject<T> {
    //A name appropriate to the current ongoing Project
    private String name;
    //A description containing information about the current ongoing Project
    private String description;
    //A list of multiple Activity types
    private List<T> activities;
    //A project is closed when all activities are completed
    private boolean isClosed;

    /**
     * The Project constructor.
     * @param name the name of the Project
     * @param description the description of the Project
     */
    public DefaultProject(String name, String description) {
        this.name = name;
        this.description = description;
        isClosed = false;
        this.activities = new ArrayList<>();
    }

    /**
     * The Project constructor.
     * @param name the name of the Project
     * @param description the description of the Project
     * @param activities the list of activities of the Project
     */
    public DefaultProject(String name, String description, List<T> activities) {
        this.name = name;
        this.description = description;
        isClosed = false;
        if(activities == null || activities.isEmpty()) {
            this.activities = new ArrayList<>();
        }
        this.activities = activities;
    }

    /**
     * Adds an activity to the current Project
     * @param activity the activity to be added
     */
    public void addActivity(T activity)
    {
        this.activities.add(activity);
    }

    /**
     * Removes an activity from the current Project
     * @param activity the activity to be removed
     */
    public void removeActivity(T activity)
    {
        this.activities.remove(activity);
    }

    /**
     * Gets an activity from the current Project based on its id
     * @param id the id of the activity to be retrieved
     * @return the activity with the specified id
     */
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
    public List<T> getActivities() {
        return activities;
    }

    @Override
    public void setActivities(List<T> activities) {
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

    /**
     * Closes the current Project if all activities have been marked as completed
     */
    public void close() {
        if(this.activities.stream().allMatch(IActivity::isCompleted)){
            isClosed = true;
        }
    }
}
