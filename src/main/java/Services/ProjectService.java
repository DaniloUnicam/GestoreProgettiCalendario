package Services;

import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;
import Repositories.ProjectRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ProjectService extends GenericProjectService<IProject<IActivity>, IActivity> {

    @Inject
    public ProjectService(ProjectRepository projectRepository) {
        super(projectRepository);
    }


}
