package JTime.Controllers;

import JTime.Controllers.Reports.ActivityReportRow;
import JTime.Model.DefaultEntities.DefaultActivity;
import JTime.Model.Interfaces.IActivity;
import JTime.Model.Interfaces.IProject;
import JTime.Persistence.Repositories.ActivityRepository;
import JTime.Persistence.Repositories.ProjectRepository;
import JTime.Persistence.Services.ActivityService;
import JTime.Persistence.Services.ProjectService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller for the Reports view: shows activities and total time.
 * Cleaned up to use a small DTO class and a project selector to filter results.
 */
public class ReportController {

    private ProjectService projectService;
    private ActivityService activityService;

    @FXML
    private TableView<ActivityReportRow> reportTable;

    @FXML
    private TableColumn<ActivityReportRow, String> actNameColumn;

    @FXML
    private TableColumn<ActivityReportRow, Integer> durationColumn;

    @FXML
    private TableColumn<ActivityReportRow, String> projectNameColumn;

    @FXML
    private TableColumn<ActivityReportRow, String> statusColumn;

    @FXML
    private Label totalLabel;

    private final ObservableList<ActivityReportRow> rows = FXCollections.observableArrayList();

    private IProject<IActivity> selectedProject;

    public ReportController() {
    }

    /**
     * Initializes the controller by setting up services, configuring table columns, and loading initial data.
     * This method is called automatically by the JavaFX framework after the FXML fields have been injected.
     */
    @FXML
    private void initialize() {
        ActivityRepository activityRepository = new ActivityRepository();
        ProjectRepository projectRepository = new ProjectRepository();
        this.activityService = new ActivityService(activityRepository);
        this.projectService = new ProjectService(projectRepository);

        actNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().name()));
        durationColumn.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().duration()).asObject());
        projectNameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().projectName()));
        statusColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().status()));

        reportTable.setItems(rows);
        refreshReport();
    }

    /**
     * Refreshes the report data by fetching activities from the project service and updating the table and total label.
     * If a project is selected, only activities from that project are shown; otherwise, activities from all projects are included.
     */
    private void refreshReport() {
        List<IProject<IActivity>> projects = projectService.findAllProjects();

        List<ActivityReportRow> collected;
        if (selectedProject == null) {
            // all projects
            collected = projects.stream()
                    .flatMap(p -> p.getActivities().stream().map(a -> buildRow(a, p)))
                    .collect(Collectors.toList());
        } else {
            collected = selectedProject.getActivities().stream().map(a -> buildRow(a, selectedProject)).collect(Collectors.toList());
        }

        rows.setAll(collected);
        int total = collected.stream().mapToInt(ActivityReportRow::duration).sum();
        totalLabel.setText("Total time of Current Project: " + total + " minutes");
    }

    /**
     * Builds a report row from an activity and its project.
     * Calculates duration based on actual duration if available, otherwise uses estimated duration.
     * Status is "Closed" if activity is completed, "Open" otherwise.
     * @param a the activity to build the row from
     * @param p the project the activity belongs to (used for project name)
     * @return an ActivityReportRow representing the activity for the report
     */
    private ActivityReportRow buildRow(IActivity a, IProject<IActivity> p) {
        int duration = (a instanceof DefaultActivity && ((DefaultActivity) a).getActualDuration() != null)
                ? ((DefaultActivity) a).getActualDuration()
                : a.getTotalEstimatedDurationInMinutes();
        return new ActivityReportRow(a.getDescription(), duration, p.getName(), a.isCompleted() ? "Closed" : "Open");
    }

    /**
     * Navigates back to the main project menu.
     * @param event the action event triggered by the button click
     */
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

    /**
     * Refreshes the report data.
     * @param event the action event triggered by the button click
     */
    @FXML
    private void onRefresh(ActionEvent event) {
        refreshReport();
    }

    /**
     * Shows a dialog to select a project to filter the report. Cancel = all projects.
     * @param event the action event triggered by the button click
     */
    @FXML
    private void onSelectProject(ActionEvent event) {
        List<IProject<IActivity>> projects = projectService.findAllProjects();
        List<String> names = projects.stream().map(IProject::getName).collect(Collectors.toList());
        ChoiceDialog<String> cd = new ChoiceDialog<>(selectedProject != null ? selectedProject.getName() : (names.isEmpty() ? null : names.get(0)), names);
        cd.setTitle("Select Project");
        cd.setHeaderText("Select a project to filter the report (Cancel = all projects)");
        Optional<String> optName = cd.showAndWait();
        if (optName.isPresent()) {
            String chosenName = optName.get();
            // find the project with the same name (first match)
            Optional<IProject<IActivity>> proj = projects.stream().filter(p -> chosenName.equals(p.getName())).findFirst();
            selectedProject = proj.orElse(null);
        } else {
            // user cancelled -> show all projects
            selectedProject = null;
        }
        refreshReport();
    }
}
