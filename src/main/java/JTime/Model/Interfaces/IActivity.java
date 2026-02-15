package JTime.Model.Interfaces;

import JTime.Model.DateUtilities.DurationAware;

/**
 * Interface representing an Activity with duration awareness.
 * An Activity is a specific task or action that has a defined duration and can be tracked.
 * Once an Activity is completed, it can be marked as such.
 */
public interface IActivity extends DurationAware {

    /**
     * The id of the current Activity
     *
     * @return the id of the current Activity
     */
    Long getId();

    /**
     * Changes the id of the current Activity with another one
     *
     * @param id the new id of the Activity
     */
    void setId(Long id);

    /**
     * Returns the description of the current Activity
     *
     * @return the description of the current Activity
     */
    String getDescription();

    /**
     * Changes the description of the current Activity
     *
     * @param description the new description to update the current Activity
     */
    void setDescription(String description);

    /**
     * Returns the status of the scheduled Activity.
     *
     * @return true if the Activity is marked as complete
     * otherwise false if the Activity is still pending
     */
    boolean isCompleted();

    /**
     * Sets the status of a scheduled Activity.
     *
     * @param completed
     * @return
     */
    void setCompleted(boolean completed);

    /**
     * Changes the estimated duration of the current Activity
     *
     * @param estimatedDuration the new estimated duration to update the current Activity
     */
    void setEstimatedDuration(int estimatedDuration);

}
