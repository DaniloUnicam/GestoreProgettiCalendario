package it.unicam.cs.mpgc.jtime119685.Repositories;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IReport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ReportRepository extends GenericRepository<IReport<IActivity,IProject<IActivity>>, Long> {

    @Inject
    @SuppressWarnings("unchecked")
    public ReportRepository() {
        super((Class<IReport<IActivity, IProject<IActivity>>>) (Class<?>) IReport.class);    }
}
