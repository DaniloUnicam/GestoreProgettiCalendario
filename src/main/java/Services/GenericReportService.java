package Services;

import Entities.Interfaces.IActivity;
import Entities.Interfaces.IReport;
import Repositories.ReportRepository;
import jakarta.inject.Inject;

import java.util.List;

public class GenericReportService<T extends IActivity> {
    private final ReportRepository _repo;

    @Inject
    public GenericReportService(ReportRepository repo) {
        _repo = repo;
    }

    public void saveReport(IReport<IActivity> report) {
        _repo.save(report);
    }

    public void deleteReport(IReport<IActivity> report) {
        _repo.delete(report);
    }

    public IReport getReportById(Long id) {
        return _repo.findById(id);
    }

    public List<IReport<IActivity>> getAllActivitiesReport() {
        return _repo.findAll();
    }

}
