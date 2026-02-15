package JTime.Controllers;

import JTime.Controllers.Handlers.HandlerFactory;
import JTime.Model.DefaultEntities.DefaultActivity;
import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Repositories.ActivityRepository;
import JTime.Persistence.Repositories.ProjectRepository;
import JTime.Persistence.Services.ActivityService;
import JTime.Persistence.Services.ProjectService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Controller for managing activities in the UI.
 * Delegates business logic to handlers and services.
 */
public class ActivityController {

    private ActivityService _activityService;
    private ProjectService _projectService;
    private HandlerFactory _handlerFactory;
    private IProject<IActivity> _currentProject;

    @FXML
    private TableView<IActivity> activityTable;

    @FXML
    private TableColumn<IActivity, String> descColumn;

    @FXML
    private TableColumn<IActivity, String> estimatedDurationColumn;

    @FXML
    private TableColumn<IActivity, String> actualDurationColumn;

    @FXML
    private TableColumn<IActivity, String> projectColumn;

    @FXML
    private TableColumn<IActivity, String> statusColumn;

    private ObservableList<IActivity> activities = FXCollections.observableArrayList();

    public ActivityController() {
    }

    @FXML
    private void initialize() {
        ActivityRepository activityRepository = new ActivityRepository();
        ProjectRepository projectRepository = new ProjectRepository();
        this._activityService = new ActivityService(activityRepository);
        this._projectService = new ProjectService(projectRepository);
        this._handlerFactory = new HandlerFactory(_activityService, _projectService);

        descColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDescription()));
        estimatedDurationColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(String.valueOf(cell.getValue().getTotalEstimatedDurationInMinutes())));
        actualDurationColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue() instanceof DefaultActivity && ((DefaultActivity) cell.getValue()).getActualDuration() != null ? String.valueOf(((DefaultActivity) cell.getValue()).getActualDuration()) : ""));
        projectColumn.setCellValueFactory(cell -> {
            List<IProject<IActivity>> projects = _projectService.findAllProjects();
            for (IProject<IActivity> p : projects) {
                if (p.getActivities().contains(cell.getValue())) {
                    return new javafx.beans.property.SimpleStringProperty(p.getName());
                }
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });
        statusColumn.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().isCompleted() ? "Closed" : "Open"));

        activityTable.setItems(activities);
        refreshActivities();
    }

    private void refreshActivities() {
        List<IActivity> all = _activityService != null ? _activityService.findAllActivities() : List.of();
        activities.setAll(all);
    }

    @FXML
    private void onBackToProjects(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/ProjectMainMenu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCreateActivity(ActionEvent event) {
        TextInputDialog descDialog = new TextInputDialog();
        descDialog.setHeaderText("Create Activity");
        descDialog.setContentText("Description:");
        Optional<String> descOpt = descDialog.showAndWait();
        if (descOpt.isEmpty()) return;

        TextInputDialog durDialog = new TextInputDialog("0");
        durDialog.setHeaderText("Create Activity");
        durDialog.setContentText("Estimated duration (minutes):");
        Optional<String> durOpt = durDialog.showAndWait();
        if (durOpt.isEmpty()) return;

        int est;
        try {
            est = Integer.parseInt(durOpt.get());
        } catch (NumberFormatException ex) {
            showAlert("Invalid duration", "Please enter a number for duration.");
            return;
        }

        List<IProject<IActivity>> projects = _projectService.findAllProjects();
        IProject<IActivity> chosenProject = null;
        if (!projects.isEmpty()) {
            List<String> projectNames = projects.stream().map(IProject::getName).toList();
            ChoiceDialog<String> cd = new ChoiceDialog<>(projectNames.get(0), projectNames);
            cd.setHeaderText("Select project to attach (optional)");
            cd.setContentText("Project:");
            Optional<String> chosenName = cd.showAndWait();
            if (chosenName.isPresent()) {
                String name = chosenName.get();
                chosenProject = projects.stream().filter(pr -> name.equals(pr.getName())).findFirst().orElse(null);
            }
        }

        DefaultActivity newAct = new DefaultActivity(descOpt.get(), est);
        _activityService.createActivity(newAct);
        if (chosenProject != null) {
            _projectService.createActivity(chosenProject, newAct);
            _projectService.updateProject(chosenProject);
        }
        refreshActivities();
    }

    @FXML
    private void onEditActivity(ActionEvent event) {
        IActivity selected = activityTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No selection", "Select an activity to edit.");
            return;
        }
        TextInputDialog descDialog = new TextInputDialog(selected.getDescription());
        descDialog.setHeaderText("Edit Activity");
        descDialog.setContentText("Description:");
        Optional<String> descOpt = descDialog.showAndWait();
        if (descOpt.isEmpty()) return;

        TextInputDialog durDialog = new TextInputDialog(String.valueOf(selected.getTotalEstimatedDurationInMinutes()));
        durDialog.setHeaderText("Edit Activity");
        durDialog.setContentText("Estimated duration (minutes):");
        Optional<String> durOpt = durDialog.showAndWait();
        if (durOpt.isEmpty()) return;

        TextInputDialog actualDialog = new TextInputDialog(selected instanceof DefaultActivity && ((DefaultActivity) selected).getActualDuration() != null ? String.valueOf(((DefaultActivity) selected).getActualDuration()) : "");
        actualDialog.setHeaderText("Edit Activity");
        actualDialog.setContentText("Actual duration (minutes) - leave empty if not set:");
        Optional<String> actualOpt = actualDialog.showAndWait();

        int est;
        try {
            est = Integer.parseInt(durOpt.get());
        } catch (NumberFormatException ex) {
            showAlert("Invalid duration", "Please enter a number for duration.");
            return;
        }

        Integer actual = null;
        if (actualOpt.isPresent() && !actualOpt.get().isEmpty()) {
            try {
                actual = Integer.parseInt(actualOpt.get());
            } catch (NumberFormatException ex) {
                showAlert("Invalid actual duration", "Please enter a number for actual duration.");
                return;
            }
        }
        List<IProject<IActivity>> projects = _projectService.findAllProjects();
        IProject<IActivity> chosenProject = null;
        if (!projects.isEmpty()) {
            List<String> projectNames = projects.stream().map(IProject::getName).toList();
            ChoiceDialog<String> cd = new ChoiceDialog<>(projectNames.get(0), projectNames);
            cd.setHeaderText("Assign to project (optional)");
            cd.setContentText("Project:");
            Optional<String> chosenName = cd.showAndWait();
            if (chosenName.isPresent()) {
                chosenProject = projects.stream().filter(pr -> chosenName.get().equals(pr.getName())).findFirst().orElse(null);
            }
        }

        selected.setDescription(descOpt.get());
        selected.setEstimatedDuration(est);
        if (selected instanceof DefaultActivity) ((DefaultActivity) selected).setActualDuration(actual);
        _activityService.updateActivity(selected);

        if (chosenProject != null) {
            // attach to chosen project: ensure not duplicated
            if (!chosenProject.getActivities().contains(selected)) {
                chosenProject.addActivity(selected);
                _projectService.updateProject(chosenProject);
            }
        }

        refreshActivities();
    }

    @FXML
    private void onDeleteActivity(ActionEvent event) {
        IActivity selected = activityTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No selection", "Select an activity to delete.");
            return;
        }
        // If activity belongs to a project, remove it from the project as well
        _activityService.deleteActivity(selected);
        refreshActivities();
    }

    @FXML
    private void onCloseActivity(ActionEvent event) {
        IActivity selected = activityTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No selection", "Select an activity to close.");
            return;
        }
        TextInputDialog actualDialog = new TextInputDialog(selected instanceof DefaultActivity && ((DefaultActivity) selected).getActualDuration() != null ? String.valueOf(((DefaultActivity) selected).getActualDuration()) : "0");
        actualDialog.setHeaderText("Close Activity");
        actualDialog.setContentText("Actual duration in minutes:");
        Optional<String> actualOpt = actualDialog.showAndWait();
        if (actualOpt.isEmpty()) return;
        int actual;
        try {
            actual = Integer.parseInt(actualOpt.get());
        } catch (NumberFormatException ex) {
            showAlert("Invalid duration", "Please enter a number for duration.");
            return;
        }
        if (selected instanceof DefaultActivity) {
            ((DefaultActivity) selected).setActualDuration(actual);
        }
        selected.setCompleted(true);
        _activityService.updateActivity(selected);
        refreshActivities();
        showAlert("Activity closed", "Actual duration: " + actual + " minutes.");
    }

    private void showAlert(String title, String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(title);
        a.setContentText(message);
        a.showAndWait();
    }

    /**
     * Set the current project context for the Activities view.
     */
    public void setCurrentProject(IProject<IActivity> project) {
        this._currentProject = project;
    }

}
