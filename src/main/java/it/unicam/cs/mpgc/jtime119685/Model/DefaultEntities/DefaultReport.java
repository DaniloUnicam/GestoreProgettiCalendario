package it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities;

import it.unicam.cs.mpgc.jtime119685.Model.DateUtilities.DurationAware;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IReport;
import jakarta.persistence.*;

import java.util.List;

/**
 * Default implementation of the IReport interface.
 *
 * @param <T> the type of activity associated with the report
 */
@Entity
@Table(name = "Reports")
public class DefaultReport<T extends IActivity, S extends IProject<T>> implements IReport<T,S>, DurationAware {
    // Unique identifier for the Report
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // A list of multiple Project types
    @ManyToMany(cascade = CascadeType.ALL)
    private List<S> projects;
    @ManyToMany(cascade = CascadeType.ALL)
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
    public DefaultReport(List<S> projects, List<T> activities) {
        this.projects = projects;
        this.activities = activities;
    }

    @Override
    public List<S> getListOfProjects() {
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
