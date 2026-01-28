package Services;

import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;
import Repositories.GenericRepository;


public class GenericProjectService<T extends IProject<S>, S extends IActivity> {

    private final GenericRepository<T, Long> _projectRepository;

    public GenericProjectService(GenericRepository<T, Long> projectRepository) {
        _projectRepository = projectRepository;
    }

    public void addActivity(T project, S activity) {
        project.addActivity(activity);
        _projectRepository.save(project);
    }

    public void removeActivity(T project, S activity) {
        project.removeActivity(activity);
        _projectRepository.delete(project);
    }

    public IActivity getActivity(T project, int id) {
        return project.getActivity(id);
    }

}
