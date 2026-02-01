package it.unicam.cs.mpgc.jtime119685.Services;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Repositories.ProjectRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProjectService extends GenericProjectService<IProject<IActivity>, IActivity> {

    @Inject
    public ProjectService(ProjectRepository projectRepository) {
        super(projectRepository);
    }


}
