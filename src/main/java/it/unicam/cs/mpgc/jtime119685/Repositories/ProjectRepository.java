package it.unicam.cs.mpgc.jtime119685.Repositories;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProjectRepository extends GenericRepository<IProject<IActivity>, Long> {

    @Inject
    @SuppressWarnings("unchecked")
    public ProjectRepository() {
        super((Class<IProject<IActivity>>) (Class<?>) IProject.class);
    }


}
