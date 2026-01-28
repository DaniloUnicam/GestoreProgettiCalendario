package Application;

import Entities.DefaultEntities.DefaultActivity;
import Entities.DefaultEntities.DefaultProject;
import Hibernate.HibernateUtil;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class MainGUI extends Application {

    private ListView<DefaultProject> projectListView;
    private TableView<DefaultActivity> activityTableView;
    private ObservableList<DefaultProject> projectsData;
    private ObservableList<DefaultActivity> activitiesData;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestore Progetti & Attività");

        // --- Layout Principale ---
        BorderPane root = new BorderPane();

        // --- 1. SINISTRA: Lista Progetti ---
        VBox leftPane = new VBox(10);
        leftPane.setPadding(new Insets(10));
        leftPane.setPrefWidth(250);

        Label lblProjects = new Label("I Tuoi Progetti");
        lblProjects.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
/*
        projectListView = new ListView<>();
        projectsData = FXCollections.observableArrayList();
        projectListView.setItems(projectsData);
        // Mostra solo il nome nella lista
        projectListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(DefaultProject item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        // Evento click su un progetto
        projectListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                loadActivitiesForProject(newVal);
            }
        });

        Button btnAddProject = new Button("Nuovo Progetto");
        Button btnDelProject = new Button("Elimina Progetto");
        btnAddProject.setMaxWidth(Double.MAX_VALUE);
        btnDelProject.setMaxWidth(Double.MAX_VALUE);

        btnAddProject.setOnAction(e -> showAddProjectDialog());
        btnDelProject.setOnAction(e -> deleteSelectedProject());

        leftPane.getChildren().addAll(lblProjects, projectListView, btnAddProject, btnDelProject);
        root.setLeft(leftPane);

        // --- 2. CENTRO: Tabella Attività ---
        VBox centerPane = new VBox(10);
        centerPane.setPadding(new Insets(10));

        Label lblActivities = new Label("Attività del Progetto");
        lblActivities.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        activityTableView = new TableView<>();
        activitiesData = FXCollections.observableArrayList();
        activityTableView.setItems(activitiesData);

        TableColumn<DefaultActivity, String> colDesc = new TableColumn<>("Descrizione");
        colDesc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));

        TableColumn<DefaultActivity, Integer> colTime = new TableColumn<>("Durata (min)");
        colTime.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getTotalEstimatedDurationInMinutes()).asObject());

        activityTableView.getColumns().addAll(colDesc, colTime);
        activityTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        HBox activityButtons = new HBox(10);
        Button btnAddActivity = new Button("Aggiungi Attività");
        Button btnDelActivity = new Button("Rimuovi Attività");
        activityButtons.getChildren().addAll(btnAddActivity, btnDelActivity);

        btnAddActivity.setOnAction(e -> showAddActivityDialog());
        btnDelActivity.setOnAction(e -> deleteSelectedActivity());

        centerPane.getChildren().addAll(lblActivities, activityTableView, activityButtons);
        root.setCenter(centerPane);

        // --- Caricamento Dati Iniziale ---
        loadProjectsFromDB();

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);

        // Chiudi Hibernate quando chiudi la finestra
        primaryStage.setOnCloseRequest(e -> HibernateUtil.shutdown());

        primaryStage.show();
    }

    // --- LOGICA DATABASE E DIALOGHI ---

    private void loadProjectsFromDB() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<DefaultProject> projects = session.createQuery("from DefaultProject", DefaultProject.class).list();
            projectsData.clear();
            projectsData.addAll(projects);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Errore DB", "Impossibile caricare i progetti.");
        }
    }

    private void loadActivitiesForProject(DefaultProject project) {
        // Ricarichiamo il progetto dalla sessione per evitare LazyInitializationException
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            DefaultProject reloaded = session.get(DefaultProject.class, project.getId());
            activitiesData.clear();
            if (reloaded != null) {
                activitiesData.addAll(reloaded.getActivities());
            }
        }
    }

    private void showAddProjectDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Nuovo Progetto");
        dialog.setHeaderText("Crea un nuovo progetto");
        dialog.setContentText("Nome del progetto:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            if (name.trim().isEmpty()) return;

            DefaultProject p = new DefaultProject(name, "Descrizione default");

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();
                session.save(p);
                tx.commit();
                projectsData.add(p);
            }
        });
    }

    private void deleteSelectedProject() {
        DefaultProject selected = projectListView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.delete(selected);
            tx.commit();
            projectsData.remove(selected);
            activitiesData.clear();
        }
    }

    private void showAddActivityDialog() {
        DefaultProject selectedProject = projectListView.getSelectionModel().getSelectedItem();
        if (selectedProject == null) {
            showAlert("Attenzione", "Seleziona prima un progetto!");
            return;
        }

        // Dialog custom semplice
        Dialog<DefaultActivity> dialog = new Dialog<>();
        dialog.setTitle("Nuova Attività");
        dialog.setHeaderText("Inserisci dettagli attività");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField descField = new TextField();
        TextField durationField = new TextField();
        durationField.setPromptText("Es. 60");

        grid.add(new Label("Descrizione:"), 0, 0);
        grid.add(descField, 1, 0);
        grid.add(new Label("Durata (min):"), 0, 1);
        grid.add(durationField, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                try {
                    DefaultActivity a = new DefaultActivity();
                    a.setDescription(descField.getText());
                    a.setEstimatedDuration(Integer.parseInt(durationField.getText()));
                    return a;
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            return null;
        });

        Optional<DefaultActivity> result = dialog.showAndWait();
        result.ifPresent(activity -> {
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();

                // Recupero il progetto persistente
                DefaultProject p = session.get(DefaultProject.class, selectedProject.getId());
                p.addActivity(activity); // Usa il metodo helper per linkare entrambi i lati

                session.update(p); // Salva a cascata
                tx.commit();

                // Aggiorna UI
                loadActivitiesForProject(p);
            }
        });
    }

    private void deleteSelectedActivity() {
        DefaultActivity selected = activityTableView.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            // Per cancellare correttamente, rimuoviamo dalla lista del padre
            DefaultActivity dbActivity = session.get(DefaultActivity.class, selected.getId()); // Supponendo che Activity abbia getId()
            // Nota: per fare delete diretto devi avere accesso all'ID o all'oggetto gestito
            session.delete(dbActivity);
            tx.commit();

            activitiesData.remove(selected);
        } catch (Exception e) {
            showAlert("Errore", "Impossibile cancellare: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

 */
    }
}