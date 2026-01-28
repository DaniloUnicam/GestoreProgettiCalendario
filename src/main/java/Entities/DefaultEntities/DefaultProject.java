package Entities.DefaultEntities;

import Entities.Interfaces.IActivity;
import Entities.Interfaces.IProject;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of the IProject interface.
 *
 * @param <T> the type of activity associated with the project
 */
@Entity
@Table(name = "Projects")
public class DefaultProject<T extends IActivity> implements IProject<T> {
    // Unique identifier for the Project
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    //A name appropriate to the current ongoing Project
    @Column(name = "project_name", length = 100)
    private String name;
    //A description containing information about the current ongoing Project
    @Column(name = "project_description", length = 100)
    private String description;
    //A list of multiple Activity types
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = DefaultActivity.class)
    private List<T> activities;
    //A project is closed when all activities are completed
    @Column(name = "project_closure_status", length = 100)
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
        this.activities = new ArrayList<>();
    }

    /**
     * The Project constructor.
     *
     * @param name        the name of the Project
     * @param description the description of the Project
     * @param activities  the list of activities of the Project
     */
    public DefaultProject(String name, String description, List<T> activities) {
        this.name = name;
        this.description = description;
        isClosed = false;
        this.activities = activities;
    }

    @Override
    public void addActivity(T activity) {
        this.activities.add(activity);
    }

    @Override
    public void removeActivity(T activity) {
        this.activities.remove(activity);
    }

    @Override
    public T getActivity(int id) {
        return this.activities.get(id);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<T> getActivities() {
        return activities;
    }

    @Override
    public void setActivities(List<T> activities) {
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
