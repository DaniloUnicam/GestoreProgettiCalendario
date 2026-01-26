package Entities.Interfaces;

import java.util.List;

/**
 * A Project is a list of Activities
 * Each Activity marks a milestone, leading to the completition of the ongoing Project
 * When each Activity is marked as completed, the Project is successfully marked as completed as well
 * @param <T> the type of activity associated with the project
 */
public interface IProject<T extends IActivity> {

    /**
     * The name of the current Project
     * @return the name of the current Project
     */
    String getName();

    /**
     * Sets the name of the current Project
     */
    void setName(String name);

    /**
     * The description of the current Project
     * @return the description of the current Project
     */
    String getDescription();

    /**
     * Sets the description of the current Project
     */
    void setDescription(String description);

    /**
     * The list of the activities listed inside the current Project
     * @return a list of activities of the current Project
     */
    List<T> getActivities();

    /**
     * Sets the list of activities for the current Project
     */
    void setActivities(List<T> activities);

    /**
     * Adds an activity to the current Project
     * @param activity the activity to be added
     */
    public void addActivity(T activity);

    /**
     * Removes an activity from the current Project
     * @param activity the activity to be removed
     */
    public void removeActivity(T activity);

    /**
     * Gets an activity from the current Project based on its id
     * @param id the id of the activity to be retrieved
     * @return the activity with the specified id
     */
    public T getActivity(int id);

    /**
     * Marks as closed the current Project, based on the status of the contained activities
     * @return true if all activities have been marked as completed
     *         false if one or more activities still haven't been completed
     */
    boolean isClosed();

    /**
     * Sets as closed the current Project, based on the following condictions:
     * the status of the contained activities have been marked as completed
     * the user wants to close the current Project
     */
    void setClosed(boolean completed);
}
