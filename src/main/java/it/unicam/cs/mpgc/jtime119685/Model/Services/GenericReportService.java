package it.unicam.cs.mpgc.jtime119685.Model.Services;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IReport;
import it.unicam.cs.mpgc.jtime119685.Model.Repositories.GenericRepository;

import java.util.List;

public class GenericReportService<T extends IActivity, S extends IProject<T>> {
    private final GenericRepository<IReport<T,S>, Long> _repo;

    public GenericReportService(GenericRepository<IReport<T,S>, Long> repo) {
        _repo = repo;
    }

    public void createReport(IReport<T, S> report) {
        _repo.save(report);
    }

    public IReport<T, S> findReportById(Long id) {
        return _repo.findById(id);
    }

    public List<IReport<T, S>> getAllActivitiesReport() {
        return _repo.findAll();
    }

    public void deleteReport(IReport<T, S> report) {
        _repo.delete(report);
    }

}
