package Hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.Optional;

/**
 * Generic DAO for basic CRUD. Use type parameter for different activity/entity types.
 */
public class GenericDao<T, ID extends Serializable> {
    private final Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T save(T entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.saveOrUpdate(entity);
            tx.commit();
            return entity;
        } catch (RuntimeException e) {
            if (tx != null && tx.getStatus().canRollback()) tx.rollback();
            throw e;
        }
    }

    public Optional<T> findById(ID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(entityClass, id));
        }
    }

    public void delete(T entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(entity);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.getStatus().canRollback()) tx.rollback();
            throw e;
        }
    }
}
