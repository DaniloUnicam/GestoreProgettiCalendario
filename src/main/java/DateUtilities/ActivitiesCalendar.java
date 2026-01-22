package DateUtilities;

import Entities.DefaultReport;
import Entities.Interfaces.IActivity;
import Entities.Interfaces.IReport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The `ProjectCalendar` class represents a calendar associated with activities.
 * It allows scheduling of activities with specific dates and times.
 */
public class ActivitiesCalendar {
    private ScheduledDay scheduledDay; // The scheduled day for the activity.
    private Map<ScheduledDay, List<IActivity>> activitiesScheduled; // The activity associated with the calendar.

    /**
     * Constructs an empty `ProjectCalendar`.
     */
    public ActivitiesCalendar() {
        this.activitiesScheduled = new HashMap<>();
    }

    /**
     * Constructs a `ProjectCalendar` with the specified activity and scheduled day.
     * @param activitiesScheduled The map of scheduled days to their corresponding activities.
     */
    public ActivitiesCalendar(Map<ScheduledDay, List<IActivity>> activitiesScheduled) {
        this.activitiesScheduled = new HashMap<>(activitiesScheduled);
    }

    public IReport generateReport(ScheduledDay startingDay, ScheduledDay endingDay) {
        return new DefaultReport(
                List.of(),
                activitiesScheduled.entrySet().stream()
                        .filter(entry -> {
                            ScheduledDay day = entry.getKey();
                            return (day.compareTo(startingDay) >= 0) && (day.compareTo(endingDay) <= 0);})
                        .flatMap(entry -> entry.getValue().stream())
                        .toList()
        );

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
     * Retrieves the activities scheduled map.
     *
     * @return A map of scheduled days to their corresponding activities.
     */
    public Map<ScheduledDay, List<IActivity>> getActivitiesScheduled() {
        return activitiesScheduled;
    }

    /**
     * Sets the activities scheduled map.
     *
     * @param activitiesScheduled The new map of scheduled days to their corresponding activities.
     */
    public void setActivitiesScheduled(Map<ScheduledDay, List<IActivity>> activitiesScheduled) {
        this.activitiesScheduled = activitiesScheduled;
    }
}
