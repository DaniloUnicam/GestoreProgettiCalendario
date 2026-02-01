package it.unicam.cs.mpgc.jtime119685.Services;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Repositories.GenericRepository;


public class GenericProjectService<T extends IProject<S>, S extends IActivity> {

    private final GenericRepository<T, Long> _projectRepository;

    public GenericProjectService(GenericRepository<T, Long> projectRepository) {
        _projectRepository = projectRepository;
    }

    public void addActivity(T project, S activity) {
        project.addActivity(activity);
        this.update(project);
    }

    public void removeActivity(T project, S activity) {
        project.removeActivity(activity);
        this.update(project);
    }

    public void update(T project) {
        _projectRepository.update(project);
    }

    public IActivity getActivity(T project, Long id) {
        return project.getActivity(id);
    }

}
