package Repositories;

import Entities.Interfaces.IActivity;
import Entities.Interfaces.IReport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ReportRepository extends GenericRepository<IReport<IActivity>, Long> {

    @Inject
    @SuppressWarnings("unchecked")
    public ReportRepository() {
        super((Class<IReport<IActivity>>) (Class<?>) IReport.class);    }
}
