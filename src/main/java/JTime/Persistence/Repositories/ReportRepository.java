package JTime.Persistence.Repositories;

import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Model.Interfaces.IReport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ReportRepository extends GenericRepository<IReport<IActivity,IProject<IActivity>>, Long> {

    @Inject
    @SuppressWarnings("unchecked")
    public ReportRepository() {
        super((Class<IReport<IActivity, IProject<IActivity>>>) (Class<?>) IReport.class);    }
}
