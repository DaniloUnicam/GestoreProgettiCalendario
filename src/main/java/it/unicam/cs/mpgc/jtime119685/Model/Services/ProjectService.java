package it.unicam.cs.mpgc.jtime119685.Model.Services;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Model.Repositories.ProjectRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProjectService extends GenericProjectService<IProject<IActivity>, IActivity> {

    @Inject
    public ProjectService(ProjectRepository projectRepository) {
        super(projectRepository);
    }

    public void createActivity(IProject<IActivity> project, IActivity activity) {
        super.createActivity(project, activity);
    }

    public void createProject(IProject<IActivity> project) {
        super.save(project);
    }

    public void updateProject(IProject<IActivity> project) {
        super.updateProject(project);
    }

    public void deleteActivity(IProject<IActivity> project, IActivity activity) {
        super.deleteActivity(project, activity);
    }

    public void deleteProject(IProject<IActivity> project) {
        super.deleteProject(project);
    }


}
