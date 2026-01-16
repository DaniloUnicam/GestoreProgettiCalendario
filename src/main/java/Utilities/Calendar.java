package Utilities;

import Entities.Interfaces.IActivity;

public class Calendar {
    private ScheduledDay scheduledDay;
    private IActivity activity;

    public Calendar (ScheduledDay scheduledDay) {
        this.scheduledDay = scheduledDay;
    }

    public Calendar (ScheduledDay scheduledDay, IActivity activity) {
        this.scheduledDay = scheduledDay;
        this.activity = activity;
    }

    public ScheduledDay getScheduledDay() {
        return scheduledDay;
    }

    public void setScheduledDay(ScheduledDay scheduledDay) {
        this.scheduledDay = scheduledDay;
    }


    public IActivity getActivity() {
        return activity;
    }

    public void setActivity(IActivity activity) {
        this.activity = activity;
    }
}
