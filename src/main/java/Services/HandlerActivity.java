package Services;

import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;

public class HandlerActivity<T extends IActivity> {
    //The activity being handled
    private T activity;

    public void addActivity(IProject project)
    {
        project.addActivity(activity);
    }

    public void  removeActivity(IProject project)
    {
        project.removeActivity(activity);
    }

    public IActivity getActivity(IProject project,int id)
    {
        return project.getActivity(id);
    }

}
