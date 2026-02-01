package it.unicam.cs.mpgc.jtime119685.Controllers;

import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultActivity;
import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultProject;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjectController {

    private final ObservableList<DefaultProject<DefaultActivity>> projects;

    public ProjectController() {
        this.projects = FXCollections.observableArrayList();
    }

    public ObservableList<DefaultProject<DefaultActivity>> getProjects() {
        return projects;
    }

    public void addProject(String name, String description) {
        projects.add(new DefaultProject<>(name, description));
    }

    public void removeProject(DefaultProject<IActivity> project) {
        projects.remove(project);
    }
}
