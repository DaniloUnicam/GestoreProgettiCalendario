package it.unicam.cs.mpgc.jtime119685.Model.Repositories;

import it.unicam.cs.mpgc.jtime119685.Persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class GenericRepository<T, ID extends Serializable> implements ICrud<T, ID> {
    protected final Class<T> clazz;

    public GenericRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public ID save(T entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Object id = session.save(entity);
            tx.commit();
            return (ID) id;
        }
    }

    @Override
    public T findById(ID id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(clazz, id);
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from " + clazz.getName(), clazz).list();
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(entity);
            tx.commit();
        }
    }
}
