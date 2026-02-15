package JTime.Persistence.Services;

import JTime.Model.DefaultEntities.DefaultReport;
import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Repositories.ReportRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ReportService extends GenericReportService<IActivity, IProject<IActivity>> {

    @Inject
    public ReportService(ReportRepository report) {
        super(report);
    }

    public <T extends IActivity,S extends IProject<T>> DefaultReport<T, S> createReport(List<S> projects) {
        List<T> activities = projects.stream()
                .flatMap(p -> p.getActivities().stream())
                .toList();

        return new DefaultReport(projects, activities);
    }

    public <T extends IActivity,S extends IProject<T>> DefaultReport<T, S> createReport(S project) {
        List<S> projects = List.of(project);
        List<T> activities = project.getActivities();

        return new DefaultReport(projects, activities);
    }

}
