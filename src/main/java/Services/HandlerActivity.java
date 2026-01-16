package Services;

import Entities.DefaultProject;
import Entities.Interfaces.IActivity;

public class HandlerActivity {
    private IActivity activity;

    public void addActivity(DefaultProject project)
    {
        project.addActivity(activity);
    }

    public void  removeActivity(DefaultProject project)
    {
        project.removeActivity(activity);
    }

    public IActivity getActivity(DefaultProject project,int id)
    {
        return project.getActivity(id);
    }

}
