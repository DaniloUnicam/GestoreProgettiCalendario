package it.unicam.cs.mpgc.jtime119685.Services;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Repositories.ReportRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ReportService extends GenericReportService<IActivity, IProject<IActivity>> {

    @Inject
    public ReportService(ReportRepository report) {
        super(report);
    }

}
