package Entities;

import Entities.Interfaces.IActivity;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultActivity implements IActivity {
    private static final AtomicInteger idCount = new AtomicInteger(0);
    private int id;
    private String description;
    private int estimatedDuration;
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
    public boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }


}
