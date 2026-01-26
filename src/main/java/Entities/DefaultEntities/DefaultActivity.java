package Entities.DefaultEntities;

import DateUtilities.DurationAware;
import Entities.Interfaces.IActivity;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Default implementation of the IActivity interface.
 * Represents an activity with a unique ID, description, estimated duration, and completion status.
 */
public class DefaultActivity implements IActivity, DurationAware {
    // Atomic counter to generate unique IDs
    private static final AtomicInteger idCount = new AtomicInteger(0);
    // Unique identifier for the Activity
    private int id;
    // Description of the Activity
    private String description;
    // Estimated duration of the Activity in minutes
    private int estimatedDuration;
    // Completion status of the Activity
    private boolean isCompleted;

    /**
     * The Activity constructor.
     * @param description the description of the Activity
     * @param estimatedDuration the estimated duration of the Activity
     */
    public DefaultActivity(String description, int estimatedDuration) {
        if(!description.isEmpty()) {
            this.description = description;
        }
        if(estimatedDuration >= 0) {
            this.estimatedDuration = estimatedDuration;
        }
        id = idCount.incrementAndGet();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DefaultActivity that = (DefaultActivity) o;
        return id == that.id && isCompleted == that.isCompleted && Objects.equals(description, that.description) && Objects.equals(estimatedDuration, that.estimatedDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, estimatedDuration, isCompleted);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getTotalEstimatedDurationInMinutes() {
        return this.estimatedDuration;
    }

    @Override
    public boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }


}
