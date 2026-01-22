package DateUtilities;

/**
 * An interface for entities that can provide estimated duration information.
 */
public interface DurationAware {
    /**
     * Calculates the total estimated duration of all activities across all projects in the report.
     * @return the total estimated duration in hours
     */
    default int getTotalEstimatedDurationInHours() {
        return getTotalEstimatedDurationInMinutes() / 60;
    }

    /**
     * Calculates the total estimated duration of all activities across all projects in the report.
     * @return the total estimated duration in minutes
     */
    int getTotalEstimatedDurationInMinutes();

}
