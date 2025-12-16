package Math;

import java.util.Calendar;

public interface CalculateActualDuration {

    /**
     * Calculates the actual duration of the Activity, subtracting the estimated duration minus the actual duration
     * @return the estimated duration of the Activity in milliseconds
     */
    long calculateActualDurationInMillis(Calendar estimatedDuration, Calendar actualDuration);

}
