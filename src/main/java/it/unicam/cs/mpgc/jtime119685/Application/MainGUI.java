package it.unicam.cs.mpgc.jtime119685.Application;

import it.unicam.cs.mpgc.jtime119685.Persistence.HibernateUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Session session = startHibernateSession();
        primaryStage.setTitle("Gestore Progetti & Attività (Test GUI)");

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        closeSession(session);

    }

    /**
     * Starts a new Hibernate session
     * @return A new Hibernate session
     */
    private static Session startHibernateSession() {
        SessionFactory factory = new HibernateUtil().getSessionFactory();
        Session session = factory.openSession();
        return session;
    }

    /**
     * @param session The Hibernate session to close
     */
    private static void closeSession(Session session) {
        session.close();
    }
}