package Services;

import Entities.Interfaces.IActivity;
import Repositories.ReportRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ReportService extends GenericReportService<IActivity> {

    @Inject
    public ReportService(ReportRepository report) {
        super(report);
    }

}
