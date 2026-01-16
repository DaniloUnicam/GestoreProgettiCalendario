package DateUtilities;

import Entities.Interfaces.IActivity;

import java.util.Calendar;

/**
 * The `ProjectCalendar` class represents a calendar associated with a specific activity.
 * It allows scheduling of activities with specific dates and times.
 */
public class ProjectCalendar {
    private ScheduledDay scheduledDay; // The scheduled day for the activity.
    private IActivity activity; // The activity associated with the calendar.

    /**
     * Constructs a `ProjectCalendar` with the specified activity and scheduled day.
     *
     * @param activityScheduled The activity to be scheduled.
     * @param scheduledDay The day on which the activity is scheduled.
     */
    public ProjectCalendar(IActivity activityScheduled, ScheduledDay scheduledDay) {
        this.scheduledDay = scheduledDay;
        this.activity = activityScheduled;
    }

    /**
     * Constructs a `ProjectCalendar` with the specified activity, scheduled day, and hour.
     *
     * @param activityScheduled The activity to be scheduled.
     * @param scheduledDay The day on which the activity is scheduled.
     * @param hour The hour of the scheduled activity.
     */
    public ProjectCalendar(IActivity activityScheduled, ScheduledDay scheduledDay, Calendar hour) {
        this.scheduledDay = scheduledDay;
        this.activity = activityScheduled;
        this.scheduledDay.setHour(hour.get(Calendar.HOUR_OF_DAY));
    }

    /**
     * Constructs a `ProjectCalendar` with the specified activity, scheduled day, hour, and minute.
     *
     * @param activityScheduled The activity to be scheduled.
     * @param scheduledDay The day on which the activity is scheduled.
     * @param hour The hour of the scheduled activity.
     * @param minute The minute of the scheduled activity.
     */
    public ProjectCalendar(IActivity activityScheduled, ScheduledDay scheduledDay, Calendar hour, Calendar minute) {
        this.scheduledDay = scheduledDay;
        this.activity = activityScheduled;
        this.scheduledDay.setHour(hour.get(Calendar.HOUR_OF_DAY));
        this.scheduledDay.setMinute(minute.get(Calendar.MINUTE));
    }

    /**
     * Constructs a `ProjectCalendar` with the specified activity, scheduled day, hour, minute and second.
     *
     * @param activityScheduled The activity to be scheduled.
     * @param scheduledDay The day on which the activity is scheduled.
     * @param hour The hour of the scheduled activity.
     * @param minute The minute of the scheduled activity.
     * @param second The second of the scheduled activity.
     */
    public ProjectCalendar(IActivity activityScheduled, ScheduledDay scheduledDay, Calendar hour, Calendar minute, Calendar second) {
        this.scheduledDay = scheduledDay;
        this.activity = activityScheduled;
        this.scheduledDay.setHour(hour.get(Calendar.HOUR_OF_DAY));
        this.scheduledDay.setMinute(minute.get(Calendar.MINUTE));
        this.scheduledDay.setSecond(second.get(Calendar.SECOND));
    }

    public Report generateReport(scheduledDay startDate, scheduledDay endDate) {
        return new Report(this.activity, this.scheduledDay, startDate, endDate);

    }

    /**
     * Retrieves the scheduled day.
     *
     * @return The `ScheduledDay` object representing the scheduled day.
     */
    public ScheduledDay getScheduledDay() {
        return scheduledDay;
    }

    /**
     * Updates the scheduled day.
     *
     * @param scheduledDay The new `ScheduledDay` object to set.
     */
    public void setScheduledDay(ScheduledDay scheduledDay) {
        this.scheduledDay = scheduledDay;
    }

    /**
     * Retrieves the activity associated with the calendar.
     *
     * @return The `IActivity` object representing the activity.
     */
    public IActivity getActivity() {
        return activity;
    }

    /**
     * Updates the activity associated with the calendar.
     *
     * @param activity The new `IActivity` object to set.
     */
    public void setActivity(IActivity activity) {
        this.activity = activity;
    }
}
