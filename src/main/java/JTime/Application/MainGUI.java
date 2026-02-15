package JTime.Application;

import JTime.Persistence.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class MainGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Session session = startHibernateSession();
        primaryStage.setTitle("JTime - Project Menu");
        Parent root = loadProjectMenu();
        Scene scene = new Scene(root, 1080, 720);
        primaryStage.setScene(scene);
        primaryStage.show();

        closeSession(session);

    }

    /**
     * Loads the project menu FXML and returns the corresponding Parent node.
     *
     * @return The Parent node corresponding to the loaded project menu FXML.
     * @throws IllegalStateException If there is an error loading the project menu FXML.
     */
    private Parent loadProjectMenu() {
        try {
            return loadView("ProjectMainMenu.fxml");
        } catch (Exception ex) {
            throw new IllegalStateException("Unable to load project menu FXML", ex);
        }
    }

    /**
     * Loads an FXML file and returns the corresponding Parent node.
     *
     * @param fxmlFileName The name of the FXML file to load (without the path).
     * @return The Parent node corresponding to the loaded FXML file.
     * @throws IOException If there is an error loading the FXML file.
     */
    private Parent loadView(String fxmlFileName) throws IOException {
        try {
            return FXMLLoader.load(getClass().getResource("/View/" + fxmlFileName));
        } catch (IOException ex) {
            throw new IOException("Unable to load FXML file: " + fxmlFileName, ex);
        }
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