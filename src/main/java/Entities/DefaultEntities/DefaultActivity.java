package Entities.DefaultEntities;

import DateUtilities.DurationAware;
import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Default implementation of the IActivity interface.
 * Represents an activity with a unique ID, description, estimated duration, and completion status.
 */
@Entity
@Table(name = "Activities")
public class DefaultActivity implements IActivity, DurationAware {
    // Unique identifier for the Activity
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    // Description of the Activity
    @Column(name = "activity_description", length = 100)
    private String description;
    // Estimated duration of the Activity in minutes
    @Column(name = "activity_estimated_duration")
    private int estimatedDuration;
    // Completion status of the Activity
    @Column(name = "activity_is_completed")
    private boolean isCompleted;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private DefaultProject<IActivity> project;

    /**
     * The default constructor for DefaultActivity.
     */
    public DefaultActivity() {
    }

    /**
     * The Activity constructor.
     *
     * @param description       the description of the Activity
     * @param estimatedDuration the estimated duration of the Activity
     */
    public DefaultActivity(String description, int estimatedDuration) {
        if (!description.isEmpty()) {
            this.description = description;
        }
        if (estimatedDuration >= 0) {
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
    public int getTotalEstimatedDurationInHours() {
        return IActivity.super.getTotalEstimatedDurationInHours();
    }

    @Override
    public int getTotalEstimatedDurationInMinutes() {
        return this.estimatedDuration;
    }

    @Override
    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
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
    public DefaultProject getProject() {
        return project;
    }

    @Override
    public void setProject(IProject<IActivity> project) {
        this.project = (DefaultProject<IActivity>) project;
    }

}
