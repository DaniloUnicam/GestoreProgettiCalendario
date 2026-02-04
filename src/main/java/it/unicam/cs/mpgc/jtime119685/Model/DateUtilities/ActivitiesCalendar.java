package it.unicam.cs.mpgc.jtime119685.Model.DateUtilities;

import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultReport;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IReport;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The `ProjectCalendar` class represents a calendar associated with activities.
 * It allows scheduling of activities with specific dates and times.
 */
@Entity
public class ActivitiesCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calendar_name")
    private String name;

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ScheduledActivity> activitiesScheduled;

    /**
     * Constructs an empty `ProjectCalendar`.
     */
    public ActivitiesCalendar() {
        this.activitiesScheduled = new ArrayList<>();
    }

    /**
     * Constructs a `ProjectCalendar` with the specified activity and scheduled day.
     *
     * @param activitiesScheduled The map of scheduled days to their corresponding activities.
     */
    public ActivitiesCalendar(List<ScheduledActivity> activitiesScheduled) {
        this.activitiesScheduled = new ArrayList<>(activitiesScheduled);
    }

    /**
     * Generates a report of activities scheduled between the specified starting and ending days.
     *
     * @param startingDay The starting day of the report range.
     * @param endingDay   The ending day of the report range.
     * @return An `IReport` object containing the activities scheduled within the specified range.
     */
    public IReport<IActivity, IProject<IActivity>> generateReport(ScheduledDay startingDay, ScheduledDay endingDay) {
        return new DefaultReport<>(
                List.of(),
                activitiesScheduled.stream()
                        .filter(entry -> {
                            ScheduledDay day = entry.scheduledDay();
                            return (day.compareTo(startingDay) >= 0) && (day.compareTo(endingDay) <= 0);
                        })
                        .map(entry -> entry.activity())
                        .toList()
        );
    }

    /**
     * Retrieves the activities scheduled map.
     *
     * @return A map of scheduled days to their corresponding activities.
     */
    public List<ScheduledActivity> getActivitiesScheduled() {
        return activitiesScheduled;
    }

    /**
     * Sets the activities scheduled map.
     *
     * @param activitiesScheduled The new map of scheduled days to their corresponding activities.
     */
    public void setActivitiesScheduled(List<ScheduledActivity> activitiesScheduled) {
        this.activitiesScheduled = activitiesScheduled;
    }
}
