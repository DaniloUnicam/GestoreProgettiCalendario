package it.unicam.cs.mpgc.jtime119685.Controllers;

import it.unicam.cs.mpgc.jtime119685.Services.ActivityService;
import it.unicam.cs.mpgc.jtime119685.Services.ProjectService;
import it.unicam.cs.mpgc.jtime119685.Services.ReportService;
import jakarta.inject.Inject;

public class MainController {

    private final ProjectService projectService;
    private final ActivityService activityService;
    private final ReportService reportService;

    @Inject
    public MainController(ProjectService projectService, ActivityService activityService, ReportService reportService) {
        this.projectService = projectService;
        this.activityService = activityService;
        this.reportService = reportService;
    }



}
