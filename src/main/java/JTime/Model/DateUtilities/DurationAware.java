package JTime.Model.DateUtilities;

/**
 * An interface for entities that can provide estimated duration information.
 * Entities implementing this interface should be able to calculate the total estimated duration
 * of their activities in minutes.
 */
public interface DurationAware {

    /**
     * Calculates the total estimated duration of all activities across all projects in the report.
     *
     * @return the total estimated duration in minutes
     */
    int getTotalEstimatedDurationInMinutes();

}
