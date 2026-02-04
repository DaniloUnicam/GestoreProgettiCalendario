package it.unicam.cs.mpgc.jtime119685.Model.Services;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Model.Repositories.GenericRepository;


public class GenericProjectService<T extends IProject<S>, S extends IActivity> {

    private final GenericRepository<T, Long> _projectRepository;

    public GenericProjectService(GenericRepository<T, Long> projectRepository) {
        _projectRepository = projectRepository;
    }

    public void save(T project) {
        _projectRepository.save(project);
    }

    public void createActivity(T project, S activity) {
        project.createActivity(activity);
        this.updateProject(project);
    }

    public IActivity getActivity(T project, Long id) {
        return project.getActivity(id);
    }

    public void updateProject(T project) {
        _projectRepository.update(project);
    }

    public void deleteActivity(T project, S activity) {
        project.deleteActivity(activity);
        this.updateProject(project);
    }

    public void deleteProject(T project) {
        _projectRepository.delete(project);
    }

}
