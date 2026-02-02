package it.unicam.cs.mpgc.jtime119685;

import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultActivity;
import it.unicam.cs.mpgc.jtime119685.Model.DefaultEntities.DefaultProject;
import it.unicam.cs.mpgc.jtime119685.Persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

    /**
     * Test to save and load one DefaultActivity
     */

    @Test
    void saveAndLoadDefaultActivity() {
        DefaultActivity defaultActivity = new DefaultActivity("buy food", 60);
        Long activityId;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(defaultActivity);
            tx.commit();
            activityId = defaultActivity.getId(); // Recuperiamo l'ID generato
            Assertions.assertNotNull(activityId);
            Assertions.assertEquals(activityId, defaultActivity.getId());
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            DefaultActivity loaded = session.get(DefaultActivity.class, activityId);

            Assertions.assertNotNull(loaded, "The Activity saved should be found in DB");
            Assertions.assertEquals("buy food", loaded.getDescription());
        }
    }

    /**
     * Test to save and load one DefaultProject with one DefaultActivity with description and duration
     */

    @Test
    void saveAndLoadDefaultProject() {
        DefaultProject p = new DefaultProject("My Default Project", "Todo tuesday");
        DefaultActivity a = new DefaultActivity("buy food", 60);

        p.addActivity(a);

        Long projectId;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(p);
            tx.commit();
            // Project ID from the persisted project
            projectId = p.getId();
            Assertions.assertNotNull(projectId);
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            DefaultProject loaded = session.get(DefaultProject.class, projectId);

            Assertions.assertNotNull(loaded, "The Project saved should be found in DB");
            Assertions.assertEquals("My Default Project", loaded.getName());

            Assertions.assertFalse(loaded.getActivities().isEmpty(), "The Project should have activities");
            Assertions.assertEquals("buy food", ((DefaultActivity) loaded.getActivities().get(0)).getDescription());
        }
    }
}