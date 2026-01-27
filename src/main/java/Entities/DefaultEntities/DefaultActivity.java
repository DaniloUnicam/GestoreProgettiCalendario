package Entities.DefaultEntities;

import DateUtilities.DurationAware;
import Entities.Interfaces.IActivity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Table;

import java.util.Objects;
/**
 * Default implementation of the IActivity interface.
 * Represents an activity with a unique ID, description, estimated duration, and completion status.
 */
@Entity
@Table(appliesTo = "DefaultActivity")
public class DefaultActivity implements IActivity, DurationAware {
    // Unique identifier for the Activity
    @Id
    private Long id;
    // Description of the Activity
    private String description;
    // Estimated duration of the Activity in minutes
    private int estimatedDuration;
    // Completion status of the Activity
    private boolean isCompleted;

    /**
     * The default constructor for DefaultActivity.
     */
    public DefaultActivity() {}

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
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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
