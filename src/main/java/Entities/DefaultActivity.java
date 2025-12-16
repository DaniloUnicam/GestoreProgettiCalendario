package Entities;

import Entities.Interfaces.IActivity;

import java.util.Calendar;

public class DefaultActivity implements IActivity {
    private int id;
    private String description;
    private Calendar estimatedDuration;
    private Calendar actualDuration;
    private boolean status;

    /**
     * The default constructor, used for temporary Activities
     */
    public DefaultActivity() {

    }

    /**
     * The Activity constructor.
     * @param id the id of the Activity
     * @param description the description of the Activity
     * @param estimatedDuration the date and time of the Activity
     * @param status the status of the Activity, can be completed (true) or pending (false)
     */
    public DefaultActivity(int id, String description, Calendar estimatedDuration, boolean status) {
        if(!description.isEmpty()) {
            this.id = id;
            this.description = description;
        }
        if(estimatedDuration != null) {
            this.estimatedDuration = estimatedDuration;
        }
        this.status = status;
    }


    @Override
    public int getId() {
        return id++;
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
    public long getEstimatedDurationInMillis() {
        return this.estimatedDuration.getTimeInMillis();
    }

    @Override
    public void setEstimatedDurationInMillis(Calendar estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    @Override
    public long getActualDurationInMillis() {
        return this.actualDuration.getTimeInMillis();
    }

    @Override
    public boolean isCompleted() {
        return this.status;
    }


}
