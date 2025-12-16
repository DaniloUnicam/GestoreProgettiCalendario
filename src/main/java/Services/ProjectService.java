package Services;

import Entities.DefaultActivity;

public class ProjectService {


    public void addActivity(DefaultActivity defaultActivity) {
        activities.add(defaultActivity);
    }

    public void deleteActivity(DefaultActivity defaultActivity) {
        activities.remove(defaultActivity);
    }

    public void updateActivity(DefaultActivity old, DefaultActivity newDefaultActivity) {
        activities.remove(old);
        activities.add(newDefaultActivity);
    }

    public DefaultActivity getActivityByName(String name) {
        for(DefaultActivity defaultActivity : activities) {
            if(defaultActivity.getName().equals(name)) {
                return defaultActivity;
            }
        }
        return null;
    }
}
