package it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import it.unicam.cs.mpgc.jtime119685.Persistence.HibernatePersistence;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the IProject interface.
 */
@Entity
@Table(name = "DefaultProjects")
public class DefaultProject implements IProject<IActivity>, HibernatePersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "default_project_id")
    private Long id;

    @Column(name = "default_project_name", length = 100)
    private String name;

    @Column(name = "default_project_description", length = 100)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, targetEntity = DefaultActivity.class)
    @JoinColumn(name = "default_project_list_of_activities")
    private List<IActivity> activities = new ArrayList<>();

    @Column(name = "default_project_status", length = 100)
    private boolean isClosed;

    /**
     * The default constructor for DefaultProject.
     */
    public DefaultProject() {
    }

    /**
     * The Project constructor.
     *
     * @param name        the name of the Project
     * @param description the description of the Project
     */
    public DefaultProject(String name, String description) {
        this.name = name;
        this.description = description;
        isClosed = false;
    }

    /**
     * The Project constructor.
     *
     * @param name        the name of the Project
     * @param description the description of the Project
     * @param activities  the list of activities of the Project
     */
    public DefaultProject(String name, String description, List<IActivity> activities) {
        this.name = name;
        this.description = description;
        isClosed = false;
        this.activities = activities;
    }

    @Override
    public void createActivity(IActivity activity) {
        this.activities.add(activity);
    }

    @Nonnull
    @Override
    public List<IActivity> getActivities() {
        return activities;
    }

    @Nonnull
    @Override
    public IActivity getActivity(Long id) {
        return this.activities.get(id.intValue());
    }

    @Override
    public void deleteActivity(IActivity activity) {
        this.activities.remove(activity);
    }

    @Override
    public void deleteActivity(Long id) {this.activities.remove(id);}

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Nonnull
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setActivities(List<IActivity> activities) {
        this.activities = activities;
    }

    @Override
    public boolean isClosed() {
        return isClosed;
    }

    @Override
    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    /**
     * Closes the current Project if all activities have been marked as completed
     */
    public void close() {
        if (this.activities.stream().allMatch(IActivity::isCompleted)) {
            isClosed = true;
        }
    }
}
