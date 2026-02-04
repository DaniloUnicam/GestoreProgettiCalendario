package it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities;

import it.unicam.cs.mpgc.jtime119685.Model.DateUtilities.DurationAware;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IReport;
import it.unicam.cs.mpgc.jtime119685.Persistence.HibernatePersistence;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the IReport interface.
 *
 * @param <T> the type of activity associated with the report
 */
@Entity
@Table(name = "DefaultReports")
public class DefaultReport<T extends IActivity, S extends IProject<T>> implements IReport<T,S>, DurationAware, HibernatePersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "default_report_id")
    private Long id;
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = DefaultProject.class)
    @JoinTable(name = "default_report_list_of_projects")
    private List<IProject<IActivity>> projects;
    @ManyToMany(cascade = CascadeType.ALL, targetEntity = DefaultActivity.class)
    @JoinTable(name = "default_report_list_of_activities")
    private List<IActivity> activities;

    /**
     * The default constructor for DefaultReport.
     */
    public DefaultReport() {
        this.projects = new ArrayList<>();
        this.activities = new ArrayList<>();
    }

    /**
     * The Report constructor.
     *
     * @param projects   the list of projects of the Report
     * @param activities the list of activities of the Report
     */
    public DefaultReport(List<IProject<IActivity>> projects, List<IActivity> activities) {
        this.projects = projects;
        this.activities = activities;
    }

    @Override
    public List<IProject<IActivity>> getListOfProjects() {
        return this.projects;
    }

    @Override
    public List<IActivity> getListOfActivities() {
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
