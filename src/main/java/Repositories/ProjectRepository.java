package Repositories;

import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;
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
