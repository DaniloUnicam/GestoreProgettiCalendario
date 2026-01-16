package Utilities;

import Entities.Interfaces.IActivity;

import java.util.Calendar;

public class ProjectCalendar {
    private ScheduledDay scheduledDay;
    private IActivity activity;

    public ProjectCalendar (IActivity activityScheduled, ScheduledDay scheduledDay) {
        this.scheduledDay = scheduledDay;
        this.activity = activityScheduled;
    }

    public ProjectCalendar (IActivity activityScheduled, ScheduledDay scheduledDay, Calendar hour) {
        this.scheduledDay = scheduledDay;
        this.activity = activityScheduled;
        this.scheduledDay.setHour(hour.get(Calendar.HOUR_OF_DAY));
    }

    public ProjectCalendar (IActivity activityScheduled, ScheduledDay scheduledDay, Calendar hour, Calendar minute) {
        this.scheduledDay = scheduledDay;
        this.activity = activityScheduled;
        this.scheduledDay.setHour(hour.get(Calendar.HOUR_OF_DAY));
        this.scheduledDay.setMinute(minute.get(Calendar.MINUTE));
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
