package Entities.DefaultEntities;

import DateUtilities.DurationAware;
import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;
import Entities.Interfaces.IReport;
import jakarta.persistence.*;

import java.util.List;

/**
 * Default implementation of the IReport interface.
 *
 * @param <T> the type of activity associated with the report
 */
@Entity
@Table(name = "Reports")
public class DefaultReport<T extends IActivity> implements IReport<T>, DurationAware {
    // Unique identifier for the Report
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    // A list of multiple Project types
    @OneToMany(mappedBy = "projects", cascade = CascadeType.ALL)
    private List<DefaultProject<T>> projects;
    @OneToMany(mappedBy = "activities", cascade = CascadeType.ALL)
    private List<T> activities;

    /**
     * The default constructor for DefaultReport.
     */
    public DefaultReport() {
        this.projects = null;
        this.activities = null;
    }

    /**
     * The Report constructor.
     *
     * @param projects   the list of projects of the Report
     * @param activities the list of activities of the Report
     */
    public DefaultReport(List<DefaultProject<T>> projects, List<T> activities) {
        this.projects = projects;
        this.activities = activities;
    }

    @Override
    public List<DefaultProject<T>> getListOfProjects() {
        return this.projects;
    }

    @Override
    public List<T> getListOfActivities() {
        return this.activities;
    }

    @Override
    public int getTotalEstimatedDurationInHours() {
        return DurationAware.super.getTotalEstimatedDurationInHours();
    }

    @Override
    public int getTotalEstimatedDurationInMinutes() {
        return projects.stream()
                .flatMap(project -> project.getActivities().stream())
                .mapToInt(DurationAware::getTotalEstimatedDurationInMinutes)
                .sum();

    }

}
