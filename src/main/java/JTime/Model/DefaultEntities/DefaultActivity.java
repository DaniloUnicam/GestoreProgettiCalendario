package JTime.Model.DefaultEntities;

import JTime.Model.DateUtilities.DurationAware;
import JTime.Model.Interfaces.IActivity;
import JTime.Persistence.HibernatePersistence;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Default implementation of the IActivity interface.
 * Represents an activity with a unique ID, description, estimated duration, actual duration and completion status.
 */
@Entity
@Table(name = "DefaultActivities")
public class DefaultActivity implements IActivity, DurationAware, HibernatePersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "default_activity_id")
    private Long id;

    @Column(name = "activity_description", length = 100)
    private String description;

    @Column(name = "activity_estimated_duration")
    private int estimatedDuration;

    @Column(name = "activity_actual_duration")
    private Integer actualDuration;

    @Column(name = "activity_completition")
    private boolean isCompleted;

    /**
     * The default constructor for DefaultActivity.
     */
    public DefaultActivity() {
    }

    /**
     * The Activity constructor.
     *
     * @param description       the description of the Activity
     * @param estimatedDuration the estimated duration of the Activity in minutes
     */
    public DefaultActivity(String description, int estimatedDuration) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
        if (estimatedDuration >= 0) {
            this.estimatedDuration = estimatedDuration;
        }
        this.actualDuration = null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DefaultActivity that = (DefaultActivity) o;
        return Objects.equals(id, that.id) && isCompleted == that.isCompleted && Objects.equals(description, that.description) && Objects.equals(estimatedDuration, that.estimatedDuration) && Objects.equals(actualDuration, that.actualDuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, estimatedDuration, actualDuration, isCompleted);
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Nonnull
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
    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    /**
     * Returns the actual duration in minutes if set (null otherwise).
     */
    public Integer getActualDuration() {
        return actualDuration;
    }

    /**
     * Set the actual duration in minutes.
     */
    public void setActualDuration(Integer actualDuration) {
        this.actualDuration = actualDuration;
    }

    @Override
    public boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    @Override
    public String toString() {
        return "DefaultActivity{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", estimatedDuration=" + estimatedDuration +
                ", actualDuration=" + actualDuration +
                ", isCompleted=" + isCompleted +
                '}';
    }

}
