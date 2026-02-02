package it.unicam.cs.mpgc.jtime119685.Controllers;

import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultActivity;
import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultReport;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Services.ProjectService;


import java.util.List;

public class ReportController {

    private final ProjectService projectService;

    public ReportController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public <T extends IActivity,S extends IProject<T>> DefaultReport<T, S> createReport(List<S> projects) {
        List<T> activities = projects.stream()
                .flatMap(p -> p.getActivities().stream())
                .toList();

        return new DefaultReport(projects, activities);
    }

}

