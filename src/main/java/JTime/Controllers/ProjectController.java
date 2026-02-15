package JTime.Controllers;

import JTime.Model.DefaultEntities.DefaultProject;
import JTime.Controllers.Handlers.HandlerFactory;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing projects.
 */
public class ProjectController {

    private ProjectService _projectService;
    private HandlerFactory _handlerFactory;
    private IProject<IActivity> _selectedProject;

    /**
     * No-arg constructor needed by FXMLLoader. Initialization of services
     * is performed in the {@link #initialize()} method.
     */
    public ProjectController() {
    }

    @FXML
    private Pane projectPane;

    @FXML
    private Button createProjectButton;

    @FXML
    private Button updateProjectButton;

    @FXML
    private Button deleteProjectButton;

    @FXML
    private Button closeProjectButton;

    @FXML
    private Button openActivitiesButton;

    @FXML
    private Button editProjectButton;

    @FXML
    private TableView<IProject<IActivity>> viewProjects;

    @FXML
    private TableColumn<IProject<IActivity>, String> projectName_column;

    @FXML
    private TableColumn<IProject<IActivity>, String> projectDescription_column;

    @FXML
    private TableColumn<IProject<IActivity>, String> projectStatus_column;

    private ObservableList<IProject<IActivity>> projects = FXCollections.observableArrayList();

    /**
     * Called by FXMLLoader after the FXML fields are injected.
     * Performs manual wiring of repositories, services and the handler factory.
     */
    @FXML
    private void initialize() {
        ActivityRepository activityRepository = new ActivityRepository();
        ProjectRepository projectRepository = new ProjectRepository();
        this._projectService = new ProjectService(projectRepository);
        ActivityService activityService = new ActivityService(activityRepository);
        this._handlerFactory = new HandlerFactory(activityService, _projectService);

        projectName_column.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getName()));
        projectDescription_column.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getDescription()));
        projectStatus_column.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().isClosed() ? "Closed" : "Open"));

        viewProjects.setItems(projects);
        refreshProjects();
    }

    private void refreshProjects() {
        List<IProject<IActivity>> all = _projectService != null ? _projectService.findAllProjects() : List.of();
        projects.setAll(all);
    }

    /**
     * Sets the selected project for operations.
     *
     * @param project The project to set as selected.
     */
    public void setSelectedProject(IProject<IActivity> project) {
        this._selectedProject = project;
    }

    /**
     * FXML event handler for creating a new project.
     * Delegates to CreateProjectHandler.
     */
    @FXML
    private void onCreateProject(ActionEvent event) {
        // Create using simple dialogs
        TextInputDialog dlg = new TextInputDialog();
        dlg.setHeaderText("Create Project");
        dlg.setContentText("Name:");
        var nameOpt = dlg.showAndWait();
        if (nameOpt.isEmpty()) return;
        TextInputDialog descDlg = new TextInputDialog();
        descDlg.setHeaderText("Create Project");
        descDlg.setContentText("Description:");
        var descOpt = descDlg.showAndWait();
        if (descOpt.isEmpty()) return;

        // Create project
        IProject<IActivity> p = new DefaultProject(nameOpt.get(), descOpt.get());
        _projectService.createProject(p);
        refreshProjects();
    }

    /**
     * FXML event handler for editing a project.
     * Delegates to EditProjectHandler.
     */
    @FXML
    private void onEditProject(ActionEvent event) {
        IProject<IActivity> sel = viewProjects.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        TextInputDialog nameDlg = new TextInputDialog(sel.getName());
        nameDlg.setHeaderText("Edit Project");
        nameDlg.setContentText("Name:");
        var nameOpt = nameDlg.showAndWait();
        if (nameOpt.isEmpty()) return;
        TextInputDialog descDlg = new TextInputDialog(sel.getDescription());
        descDlg.setHeaderText("Edit Project");
        descDlg.setContentText("Description:");
        var descOpt = descDlg.showAndWait();
        if (descOpt.isEmpty()) return;
        sel.setName(nameOpt.get());
        sel.setDescription(descOpt.get());

        // allow user to set Open/Closed state
        String current = sel.isClosed() ? "Closed" : "Open";
        List<String> options = List.of("Open", "Closed");
        ChoiceDialog<String> statusDialog = new ChoiceDialog<>(current, options);
        statusDialog.setHeaderText("Project status");
        statusDialog.setContentText("Select status:");
        Optional<String> statusOpt = statusDialog.showAndWait();
        if (statusOpt.isPresent()) {
            sel.setClosed("Closed".equals(statusOpt.get()));
        }

        _projectService.updateProject(sel);
        refreshProjects();
    }

    /**
     * FXML event handler for deleting a project.
     * Delegates to DeleteProjectHandler.
     */
    @FXML
    private void onDeleteProject(ActionEvent event) {
        IProject<IActivity> sel = viewProjects.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        _projectService.deleteProject(sel);
        refreshProjects();
    }

    /**
     * FXML event handler for closing a project.
     * Delegates to CloseProjectHandler.
     */
    @FXML
    private void onCloseProject(ActionEvent event) {
        IProject<IActivity> sel = viewProjects.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        if (sel.getActivities().stream().allMatch(IActivity::isCompleted)) {
            sel.setClosed(true);
            _projectService.updateProject(sel);
            refreshProjects();
        } else {
            // show dialog
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Cannot close");
            a.setContentText("Not all activities are completed.");
            a.showAndWait();
        }
    }

    /**
     * Opens the Activity scene, switching the current stage root to the activity FXML.
     */
    @FXML
    private void onOpenActivities(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ActivityMainMenu.fxml"));
            Parent root = loader.load();
            ActivityController controller = loader.getController();
            IProject<IActivity> sel = viewProjects.getSelectionModel().getSelectedItem();
            if (sel != null) controller.setCurrentProject(sel);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onOpenReports(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Reports.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
