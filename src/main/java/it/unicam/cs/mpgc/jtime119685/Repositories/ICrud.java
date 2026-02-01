package it.unicam.cs.mpgc.jtime119685.Repositories;

import java.util.List;

public interface ICrud<T, ID> {
    ID save(T entity);

    T findById(ID id);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);
}
