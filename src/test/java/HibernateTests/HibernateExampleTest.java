package HibernateTests;

import Entities.DefaultEntities.DefaultActivity;
import Entities.DefaultEntities.DefaultProject;
import Hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HibernateExampleTest {

    @BeforeAll
    static void setup() {
        // Inizializza la SessionFactory una volta prima di tutti i test
        HibernateUtil.getSessionFactory();
    }

    @AfterAll
    static void teardown() {
        HibernateUtil.shutdown();
    }

    @Test
    void saveAndLoadDefaultActivity() {
        DefaultActivity defaultActivity = new DefaultActivity("buy food", 60);
        Long activityId;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(defaultActivity);
            tx.commit();
            activityId = defaultActivity.getId(); // Recuperiamo l'ID generato
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            DefaultActivity loaded = session.get(DefaultActivity.class, activityId);

            assertNotNull(loaded, "L'attività dovrebbe essere stata salvata");
            assertEquals("buy food", loaded.getDescription());
        }
    }

    @Test
    void saveAndLoadDefaultProject() {
        DefaultProject<DefaultActivity> p = new DefaultProject<>("My Default Project", "Todo tuesday");
        DefaultActivity a = new DefaultActivity("buy food", 60);

        p.addActivity(a);

        Long projectId;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(p); // CascadeType.ALL salverà anche l'attività
            tx.commit();
            projectId = p.getId(); // Recuperiamo l'ID del progetto
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            DefaultProject<?> loaded = session.get(DefaultProject.class, projectId);

            assertNotNull(loaded, "Il progetto dovrebbe esistere nel DB");
            assertEquals("My Default Project", loaded.getName());

            assertFalse(loaded.getActivities().isEmpty(), "Il progetto dovrebbe avere attività collegate");
            assertEquals("buy food", ((DefaultActivity) loaded.getActivities().get(0)).getDescription());
        }
    }
}