package JTime.Persistence.Test;

import JTime.Persistence.HibernateUtil;
import org.hibernate.Session;

/**
 * Test class to verify the configuration of Hibernate
 */
public class HibernateTest {
    public static void main(String[] args) {
        try {
            var sessionFactory = HibernateUtil.getSessionFactory();
            System.out.println("Hibernate inizializzato correttamente!");

            // Facoltativo: prova a aprire una sessione
            try (Session session = sessionFactory.openSession()) {
                System.out.println("Sessione aperta con successo!");
            }

            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
