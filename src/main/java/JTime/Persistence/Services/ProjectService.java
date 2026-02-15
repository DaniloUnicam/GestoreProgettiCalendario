package JTime.Persistence.Services;

import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Repositories.ProjectRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ProjectService extends GenericProjectService<IProject<IActivity>, IActivity> {

    private final ProjectRepository _projectRepository;

    @Inject
    public ProjectService(ProjectRepository projectRepository) {
        super(projectRepository);
        this._projectRepository = projectRepository;
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

    public List<IProject<IActivity>> findAllProjects() {
        return _projectRepository.findAll();
    }

}
