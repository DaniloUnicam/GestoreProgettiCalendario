package Entities.Interfaces;

import java.util.Calendar;

public interface IActivity {

    /**
     * The id of the current Activity
     * @return the id of the current Activity
     */
    int getId();

    /**
     * Changes the id of the current Activity with another one
     * @param id the new id of the Activity
     */
    void setId(int id);

    /**
     * Returns the description of the current Activity
     * @return the description of the current Activity
     */
    String getDescription();

    /**
     * Changes the description of the current Activity
     * @param description the new description to update the current Activity
     */
    void setDescription(String description);

    /**
     * Estimated duration of the current Activity
     * @return the estimated duration of the Activity in millis
     */
    long getEstimatedDurationInMillis();

    /**
     * Changes the estimated duration of the current Activity
     * @param estimatedDuration the estimated duration of the Activity in millis
     */
    void setEstimatedDurationInMillis(Calendar estimatedDuration);

    /**
     * Returns the actual duration of the current Activity
     * @return the actual duration of the current Activity
     */
    long getActualDurationInMillis();

    /**
     * Returns the status of the scheduled Activity.
     * @return true if the Activity is marked as complete
     *              otherwise false if the Activity is still pending
     */
    boolean isCompleted();
}
