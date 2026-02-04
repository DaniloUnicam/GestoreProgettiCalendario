package it.unicam.cs.mpgc.jtime119685.View;

import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IActivity;
import it.unicam.cs.mpgc.jtime119685.Model.Interfaces.IProject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.util.List;

public class ViewProjectSceneController {

    @FXML
    private Pane projectPane;

    @FXML
    private Button createProjectButton;

    @FXML
    private Button deleteProjectButton;

    @FXML
    private TextField projectName;

    @FXML
    private TextField projectDescription;

    @FXML
    private Button addActivityButton;

    @FXML
    private Button deleteActivityButton;

    @FXML
    private TextField activityDescrption;

    @FXML
    private TextField hours;

    @FXML
    private TextField minutes;

    @FXML
    private Button seconds;

    @FXML
    private TableView<IProject<IActivity>> viewProjects;

    @FXML
    private TableColumn<IProject<IActivity>,String> projectName_column;

    @FXML
    private TableColumn<IProject<IActivity>,String> projectDescription_column;

    @FXML
    private TableColumn<IProject<IActivity>,Boolean> projectStatus_column;

    @FXML
    private TableColumn<IProject<IActivity>, List<IActivity>> activities;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker datePicker;



}
