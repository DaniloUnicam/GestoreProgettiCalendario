package Services;

import Entities.DefaultActivity;
import Math.CalculateActualDuration;

import java.util.Calendar;

public class ActivityService extends DefaultActivity implements CalculateActualDuration {



    @Override
    public long calculateActualDurationInMillis(Calendar estimatedDuration, Calendar actualDuration) {
        return estimatedDuration.getTimeInMillis() - actualDuration.getTimeInMillis();
    }

}
