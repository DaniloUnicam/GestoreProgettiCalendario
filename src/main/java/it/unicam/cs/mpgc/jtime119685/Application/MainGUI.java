package it.unicam.cs.mpgc.jtime119685.Application;

import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultActivity;
import it.unicam.cs.mpgc.jtime119685.Persistence.HibernateUtil;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MainGUI extends Application {

    private ListView<String> projectListView;
    private TableView<DefaultActivity> activityTableView;
    private ObservableList<String> projectsData;
    private ObservableList<DefaultActivity> activitiesData;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        configureHibernateAndBoot();
        primaryStage.setTitle("Gestore Progetti & Attività (Test GUI)");

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static void configureHibernateAndBoot() {
        SessionFactory factory = new HibernateUtil().getSessionFactory();
        Session session = factory.openSession();
    }
}