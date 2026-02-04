package it.unicam.cs.mpgc.jtime119685.Controllers;

import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultReport;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Model.Services.ReportService;
import jakarta.inject.Inject;

import java.util.List;

public class ReportController {

    private final ReportService _reportService;

    @Inject
    public ReportController(ReportService reportService) {
        this._reportService = reportService;
    }

    /**
     * Creates a report for a list of projects.
     *
     * @param projects The list of projects for which the report is to be created.
     * @return A DefaultReport instance containing the report details for the specified projects.
     */
    public <T extends IActivity,S extends IProject<T>> DefaultReport<T, S> createReport(List<S> projects) {
        return _reportService.createReport(projects);
    }

    /**
     * Creates a report for a specific project.
     *
     * @param project The project for which the report is to be created.
     * @return A DefaultReport instance containing the report details for the specified project.
     */
    public <T extends IActivity,S extends IProject<T>> DefaultReport<T, S> createReport(S project) {
        return _reportService.createReport(project);
    }

}

